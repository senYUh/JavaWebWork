package com.senyang.boot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.senyang.boot.constant.MyConst;
import com.senyang.boot.entity.*;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.PwdQuestionService;
import com.senyang.boot.service.UserMibaoService;
import com.senyang.boot.service.UserService;
import com.senyang.boot.utils.Secret;
import com.senyang.boot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@Controller
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMibaoService userMibaoService;
    @Autowired
    PwdQuestionService pwdQuestionService;
    @Autowired
    MyLog myLog;

    @PostMapping("/judge")
    @ResponseBody
    public RespEntity judge(HttpServletRequest request,HttpSession session){
        Cookie[] cookies = request.getCookies();
        Cookie cookie1 = null;
        String userId = null;
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(MyConst.COOKIE_VALUE.equals(cookie.getValue())){
                    cookie1 = cookie;
                    continue;
                }
                if("userId".equals(cookie.getName())){
                    userId = cookie.getValue();
                }
                if(userId != null && cookie1 != null){
                    break;
                }
            }
        }
        if(cookie1 != null && session.getAttribute(userId) != null){
            return new RespEntity(RespCode.SUCCESS,null);
        }else{
            return new RespEntity(RespCode.LOGIN_WARN,null);
        }
    }

    @PostMapping({"/","/login"})
    @ResponseBody
    public RespEntity loginDo(HttpServletResponse response , HttpSession session, @RequestBody Map<String,String> userMes){
//        log.info("userName:{},userPwd:{}",userMes.get("userName"),userMes.get("userPwd"));
        User user = userService.login(userMes.get("userName"),userMes.get("userPwd"));
        if(user != null){
            Cookie cookie = new Cookie("loginInfo", MyConst.COOKIE_VALUE);
            cookie.setMaxAge(MyConst.MAX_COOKIE_TIME);
            Cookie cookie1 = new Cookie("userId",String.valueOf(user.getUserId()));
            cookie1.setMaxAge(MyConst.MAX_COOKIE_TIME);
            response.addCookie(cookie);
            response.addCookie(cookie1);
            session.setAttribute(String.valueOf(user.getUserId()),user);
            myLog.append("用户["+user.getUserName()+"],id:"+user.getUserId()+"登录成功!");
            return new RespEntity(RespCode.SUCCESS,user.toJsonUser());
        }
        return new RespEntity(RespCode.FAIL,null);
    }
    @PostMapping("/logout")
    @ResponseBody
    public RespEntity logout(HttpServletRequest request,HttpSession session){
        String userId = String.valueOf(WebUtils.getUserId(request));
        session.removeAttribute(userId);
        return new RespEntity(RespCode.SUCCESS,null);
    }

    @PostMapping("/register")
    @ResponseBody
    public RespEntity register(@RequestBody Map<String,Object> userInfo){
        User user = new User();
        UserMibao question = new UserMibao();
        init(user,question,userInfo);
        boolean flag = false;
        if(userService.addUser(user)==1){
            question.setUserId(user.getUserId());
            if(userMibaoService.save(question)){
                flag = true;
            }
        }
        if(flag){
            return new RespEntity(RespCode.SUCCESS,null);
        }else {
            return new RespEntity(RespCode.REGISTER_ERROR,null);
        }
    }
    @PostMapping("/PwdQuestion")
    @ResponseBody
    public RespEntity getAllPwdQuestion(){
        List<PwdQuestion> list;
        list = pwdQuestionService.list();
        if(!list.isEmpty()){
            return new RespEntity(RespCode.SUCCESS,list);
        }else {
            return new RespEntity(RespCode.GET_INFO_ERROR,null);
        }
    }

    @PostMapping("/getUserPwdQues")
    @ResponseBody
    public RespEntity getUserPwdQuestion(@RequestBody Map<String,String> userInfo){
        String userName = userInfo.get("userName");
        User user;
        UserMibao mibao = null;
        PwdQuestion question;
        List<Object> list = new ArrayList<>();
        user = userService.getByName(userName);
        System.out.println(user);
        if(user != null){
            list.add(user.getUserId());
             mibao = userMibaoService.getById(user.getUserId());
        }
        if(mibao != null){
            question = pwdQuestionService.getById(mibao.getQuestionId());
            if(question!=null){
                list.add(question);
            }
            return new RespEntity(RespCode.SUCCESS,list);
        }else {
            return new RespEntity(RespCode.FAIL,null);
        }
    }

    @PostMapping("/retrieve")
    @ResponseBody
    public RespEntity retrievePassword(HttpServletResponse response, @RequestBody Map<String, String> mibaoInfo){
        Integer id = Integer.parseInt(mibaoInfo.get("userId"));
        String answer = mibaoInfo.get("questionAnswer");
        UserMibao mibao;
        mibao = userMibaoService.getById(id);
        if(mibao!=null){
            if(mibao.getQuestionAnswer().equals(answer)){
                Cookie cookie = new Cookie("updateP",String.valueOf(Secret.doSecret(id)));
                cookie.setMaxAge(MyConst.MAX_COOKIE_TIME);
                response.addCookie(cookie);
                return new RespEntity(RespCode.SUCCESS,null);
            }
        }
        return new RespEntity(RespCode.FAIL,null);
    }
    @PostMapping("/updateUser")
    @ResponseBody
    public RespEntity updateUserInfo(@RequestBody Map<String,String> userInfo,HttpServletRequest request){
        String newPwd = userInfo.get("userPwd");
        String userId = userInfo.get("userId");
        String saveId = "";
        boolean res = false;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if("updateP".equals(cookie.getName())){
                saveId = cookie.getValue();
            }
        }
        User user = userService.getById(Integer.parseInt(userId));
        if(String.valueOf(Secret.unSecret(Integer.valueOf(saveId))).equals(userId)){
            user.setUserPwd(newPwd);
             res = userService.updateById(user);
        }
        if(res){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("getAUI")
    @ResponseBody
    public RespEntity getAllUserInfo(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        int pageNum = Integer.parseInt(info.get("pageNum"));
        Page<User> page = new Page<>(pageNum,MyConst.MY_PAGE_NUM_OF_USER);
        Page<User> res = userService.page(page);
        List<User> users = res.getRecords();
        List<Object> list = new ArrayList<>();
        if(users != null){
            int countUser = userService.count();
            list.add(countUser);
            list.add(users);
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }


    @PostMapping("upBMU")
    @ResponseBody
    public RespEntity updateUserByManger(HttpServletRequest request , @RequestBody User user){
        if(WebUtils.isNotManger(request,userService)){
            if(WebUtils.isNotUser(request,user.getUserId())){
                return new RespEntity(RespCode.FAIL,null);
            }
        }
        int res = userService.updateUserByManger(user);
        if(res == 0){
            return new RespEntity(RespCode.SUCCESS,user);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("getMI")
    @ResponseBody
    public RespEntity getMyInfo(HttpServletRequest request){
        Integer userId = WebUtils.getUserId(request);
        User user = userService.getById(userId);
        if(user != null){
            return new RespEntity(RespCode.SUCCESS,user);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("upIMG")
    @ResponseBody
    public RespEntity updateUserImgUrl(HttpServletRequest request,@RequestBody Map<String,String> info){
        String url =info.get("url");
        int userId = WebUtils.getUserId(request);
        int res = userService.updateUserImgUrl(url,userId);
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }


    static void init(User user, UserMibao question, Map<String,Object>map){
        user.setUserName((String)map.get("userName"));
        if(map.get("userImgUrl")!=""){
            user.setUserImgUrl((String)map.get("userImgUrl"));
        }
        user.setUserPwd((String)map.get("userPwd"));
        question.setQuestionId((Integer)map.get("questionId"));
        question.setQuestionAnswer((String)map.get("questionAnswer"));
    }

}

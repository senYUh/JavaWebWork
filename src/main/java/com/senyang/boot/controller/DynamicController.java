package com.senyang.boot.controller;


import com.senyang.boot.constant.MyConst;
import com.senyang.boot.entity.*;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.DynamicDoService;
import com.senyang.boot.service.FollowService;
import com.senyang.boot.service.UserDynamicService;
import com.senyang.boot.service.UserService;
import com.senyang.boot.utils.StringUtils;
import com.senyang.boot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class DynamicController {
    @Autowired
    DynamicDoService dynamicDoService;
    @Autowired
    UserDynamicService dynamicService;
    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;

    @PostMapping("/uploadDy")
    @ResponseBody
    public RespEntity uploadDynamic(@RequestBody Map<String,String> dynamicInfo, HttpServletRequest request){
        UserDynamic dynamic = new UserDynamic();
        dynamic.setDynamicTitle(dynamicInfo.get("dynamicTitle"));
        dynamic.setDynamicText(StringUtils.replaceDouble(dynamicInfo.get("dynamicText")));
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if("userId".equals(cookie.getName())){
                dynamic.setUserId(Integer.parseInt(cookie.getValue()));
                break;
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dynamic.setDynamicReleaseDate(dateFormat.format(date));
        int res = dynamicService.upLoadDynamic(dynamic);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/getDyById")
    @ResponseBody
    public RespEntity getDyById(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId;
        if(info.get("userId") != null){
            userId = Integer.parseInt(info.get("userId"));
        }else {
            userId  = WebUtils.getUserId(request);
        }
        List<Object> list;
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<ShowDynamic> dynamics = dynamicService.getUserAllDynamic(userId,
                (pageNum-1) * MyConst.PAGE_MAX_NUM_OF_DYNAMIC);
        if(dynamics != null){
            list = getList(dynamics,dynamicDoService,userId,dynamicService,true,false);
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/getMCD")
    @ResponseBody
    public RespEntity getMyCollectedDynamic(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId;
        if(info.get("userId") != null){
            userId = Integer.parseInt(info.get("userId"));
        }else {
            userId = WebUtils.getUserId(request);
        }
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<ShowDynamic> dynamics = dynamicService.getMyCollectedDynamic(userId,
                (pageNum-1) * MyConst.PAGE_MAX_NUM_OF_DYNAMIC);
        int count = 0 ;
        if(dynamics != null){
            List<Object> list = getList(dynamics,dynamicDoService,userId,dynamicService,false,false);
            if(dynamics.size() != 0){
                count = dynamicService.countMyCollectedDynamic(userId);
            }
            list.add(count);
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }
    @PostMapping("/getAllDy")
    @ResponseBody
    public RespEntity getAllDynamic(@RequestBody Map<String,String> info,HttpServletRequest request){
        Integer userId = WebUtils.getUserId(request);
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<Object> list;
        List<ShowDynamic> dynamics = dynamicService.getAllByList((pageNum-1)* MyConst.PAGE_MAX_NUM_OF_DYNAMIC);
        if(dynamics != null){
            list = getList(dynamics,dynamicDoService,userId,dynamicService,true,true);
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/delDy")
    @ResponseBody
    public RespEntity deDyMi( HttpServletRequest request,@RequestBody Map<String,String> info){
        int dynamicId = Integer.parseInt(info.get("dynamicId"));
        Integer userId = WebUtils.getUserId(request);
        boolean flag = false;
        int res = 0;
        if(!WebUtils.isNotManger(request,userService)){
            flag = true;
        }else {
            UserDynamic dynamic = dynamicService.getById(dynamicId);
            if(dynamic.getUserId().equals(userId)){
                flag = true;
            }
        }
        if(flag){
            res = dynamicService.deleteDynamic(dynamicId,userId);
        }
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/updateDy")
    @ResponseBody
    public RespEntity updateDy(@RequestBody Map<String,String> info){
        UserDynamic dynamic = new UserDynamic();
        dynamic.setDynamicText(info.get("dynamicText"));
        int res = dynamicService.updateDynamic(dynamic);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/thumbsUp")
    @ResponseBody
    public RespEntity doThumbsUp(HttpServletRequest request, @RequestBody Map<String, String> info){
        int dynamicId = Integer.parseInt(info.get("dynamicId"));
        Integer userId = WebUtils.getUserId(request);
        int res;
        DynamicDo dynamicDoTemp = dynamicDoService.getDynamicDo(userId,dynamicId);
        if(dynamicDoTemp == null){
            dynamicDoTemp = new DynamicDo();
            dynamicDoTemp.setUserId(userId);
            dynamicDoTemp.setDynamicId(dynamicId);
            dynamicDoTemp.setThumbsUp(true);
            res = dynamicDoService.addDynamicMes(dynamicDoTemp);
            if(res != 0){
                dynamicService.thumbDynamic(dynamicId);
            }
        }else {
            dynamicDoTemp.setThumbsUp(!dynamicDoTemp.getThumbsUp());
            res = dynamicDoService.updateDynamicMes(dynamicDoTemp);
            if(dynamicDoTemp.getThumbsUp()){
                int t = dynamicService.thumbDynamic(dynamicId);
                if(t <= 0){
                    return new RespEntity(RespCode.REGISTER_ERROR,null);
                }
            }
            if(!dynamicDoTemp.getThumbsUp()&&res != 0){
                int t = dynamicService.unThumbDynamic(dynamicId);
                if(t <= 0){
                    return new RespEntity(RespCode.REGISTER_ERROR,null);
                }
            }
        }
        if(res != 0){
            judgeAndSafeDelDynamicDo(dynamicDoService,dynamicDoTemp);
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/collection")
    @ResponseBody
    public RespEntity doCollection(HttpServletRequest request, @RequestBody Map<String, String> info){
        int userId = WebUtils.getUserId(request);
        int dynamicId = Integer.parseInt(info.get("dynamicId"));
        int res;
        DynamicDo dynamicDoTemp = dynamicDoService.getDynamicDo(userId,dynamicId);
        if(dynamicDoTemp == null){
            dynamicDoTemp = new DynamicDo();
            dynamicDoTemp.setUserId(userId);
            dynamicDoTemp.setDynamicId(dynamicId);
            dynamicDoTemp.setCollection(true);
            res = dynamicDoService.addDynamicMes(dynamicDoTemp);
            if(res != 0){
                int t = dynamicService.collectionDynamic(dynamicId);
                if(t <= 0){
                    return new RespEntity(RespCode.REGISTER_ERROR,null);
                }
            }
        }else {
            dynamicDoTemp.setCollection(!dynamicDoTemp.getCollection());
            res = dynamicDoService.updateDynamicMes(dynamicDoTemp);
            if(dynamicDoTemp.getCollection() && res != 0){
                int t = dynamicService.collectionDynamic(dynamicId);
                if(t <= 0){
                    return new RespEntity(RespCode.REGISTER_ERROR,null);
                }
            }
            if(!dynamicDoTemp.getCollection() && res != 0){
                int t = dynamicService.unCollectionDynamic(dynamicId);
                if(t <= 0){
                    return new RespEntity(RespCode.REGISTER_ERROR,null);
                }
            }
        }
        if(res != 0){
            judgeAndSafeDelDynamicDo(dynamicDoService,dynamicDoTemp);
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/getDBT")
    @ResponseBody
    public RespEntity getDynamicByThumb(@RequestBody Map<String,String> info){
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<ShowDynamic> list = dynamicService.getDynamicByThumb((pageNum-1)* MyConst.PAGE_MAX_NUM_OF_DYNAMIC);
        if(list != null){
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/getMFD")
    @ResponseBody
    public RespEntity getMyFollowedDynamic(@RequestBody Map<String,String> info,HttpServletRequest request){
        int userId ;
        if(info.get("userId") != null){
            userId = Integer.parseInt("userId");
        }else {
            userId = WebUtils.getUserId(request);
        }
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<Follow> list = followService.getFollowed(userId);
        List<ShowDynamic> list1 = new ArrayList<>();
        int count = 0;
        if(list.size()!=0){
            list1 = dynamicService.getMyFollowedDynamic(list,(pageNum-1)* MyConst.PAGE_MAX_NUM_OF_DYNAMIC);
        }
        if(list1 != null){
            List<Object> list2 = getList(list1,dynamicDoService,userId,dynamicService,false,false);
            if(list.size() != 0){
                count = dynamicService.countMyFollowedDyN(list);
            }
            list2.add(count);
            return new RespEntity(RespCode.SUCCESS,list2);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);

    }

    @PostMapping("/getDTS")
    @ResponseBody
    public RespEntity getDynamicToShow(HttpServletRequest request ,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Integer dynamicId = Integer.parseInt(info.get("dynamicId"));
        ShowDynamic dynamic = dynamicService.getDynamicToShow(dynamicId);
        List<ShowDynamic> dynamics = new ArrayList<>();
        dynamics.add(dynamic);
        List<Object> list = null;
        if(dynamic != null){
            list = getList(dynamics,dynamicDoService,userId,dynamicService,false,false);
        }
        if(list != null){
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }


    static void judgeAndSafeDelDynamicDo(DynamicDoService dynamicDoService, DynamicDo dynamicDo){
        if(!(dynamicDo.getThumbsUp()||dynamicDo.getCollection())){
            while (dynamicDoService.deleteDynamicMes(dynamicDo)!=0){
                dynamicDoService.deleteDynamicMes(dynamicDo);
            }
        }
    }


    static List<Object> getList(List<ShowDynamic> dynamics,DynamicDoService dynamicDoService,
                                Integer userId,UserDynamicService dynamicService,boolean requireCount,boolean countAll){
        List<Object> list = new ArrayList<>();
        int count = 0;
        Map<Integer,Boolean[]> map = new HashMap<>();
        if(dynamics.size() != 0){
            for(ShowDynamic dynamic : dynamics){
                DynamicDo dynamicDo = dynamicDoService.getDynamicDo(userId,dynamic.getDynamicId());
                Boolean[] booleans;
                if(dynamicDo!=null){
                    booleans = new Boolean[]{dynamicDo.getThumbsUp(), dynamicDo.getCollection()};
                }else {
                    booleans = new Boolean[]{false,false};
                }
                map.put(dynamic.getDynamicId(),booleans);
            }
            if(countAll){
                userId = -1945003;
            }
            count = dynamicService.countById(userId);
        }
        if(requireCount){
            list.add(count);
        }
        list.add(dynamics);
        list.add(map);
        return list;
    }

}

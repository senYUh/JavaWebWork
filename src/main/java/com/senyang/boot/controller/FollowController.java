package com.senyang.boot.controller;

import com.senyang.boot.constant.MyConst;
import com.senyang.boot.entity.Follow;
import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.entity.User;
import com.senyang.boot.entity.UserLogTemp;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.FollowService;
import com.senyang.boot.service.UserDynamicService;
import com.senyang.boot.service.UserService;
import com.senyang.boot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class FollowController {

    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;
    @Autowired
    UserDynamicService dynamicService;

    @PostMapping("/getSUM")
    @ResponseBody
    public RespEntity getShowUserMes(@RequestBody Map<String,String> info, HttpServletRequest request){
        int userId = Integer.parseInt(info.get("userId"));
        if(userId == MyConst.MY_USERID_LOG_ID){
            userId = WebUtils.getUserId(request);
        }
        UserLogTemp userLogTemp = toShowUser(userId,followService,dynamicService,userService);
        if(userLogTemp != null){
            return new RespEntity(RespCode.SUCCESS,userLogTemp);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/follow")
    @ResponseBody
    public RespEntity followed(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Integer followedId = Integer.parseInt(info.get("userId"));
        Follow follow = new Follow(userId,followedId);
        int res = followService.follow(follow);
        if(res == 1){
            UserLogTemp userLogTemp = toShowUser(followedId,followService,dynamicService,userService);
            if(userLogTemp != null){
                return new RespEntity(RespCode.SUCCESS,userLogTemp);
            }
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/unfollow")
    @ResponseBody
    public RespEntity unFollowUser(HttpServletRequest request,@RequestBody Map<String,String> info){
        int userId = WebUtils.getUserId(request);
        int followId = Integer.parseInt(info.get("userId"));
        Follow follow = new Follow(userId,followId);
        int res = followService.unFollow(follow);
        if(res == 1){
            UserLogTemp userLogTemp = toShowUser(followId,followService,dynamicService,userService);
            if(userLogTemp != null){
                return new RespEntity(RespCode.SUCCESS,userLogTemp);
            }
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/isFollow")
    @ResponseBody
    public RespEntity judgeIsFollow(HttpServletRequest request ,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Integer followId = Integer.parseInt(info.get("userId"));
        Follow follow = new Follow(userId,followId);
        int res = followService.isFollow(follow);
        return new RespEntity(RespCode.SUCCESS,res);
    }

    @PostMapping("/getFans")
    @ResponseBody
    public RespEntity getFansById(HttpServletRequest request , @RequestBody Map<String,String> info){
        Integer userId;
        userId = info.get("userId")==null?WebUtils.getUserId(request):Integer.parseInt(info.get("userId"));
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<UserLogTemp> userLogTemps = followService.getFollowers(userId,
                (pageNum-1)*MyConst.MY_PAGE_NUM_OF_FANS_AND_FOLLOWS);
        if(userLogTemps != null){
            List<Object> list = new ArrayList<>();
            int count = followService.countFollowers(userId);
            list.add(count);
            list.add(userLogTemps);
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }


    @PostMapping("/getFOL")
    @ResponseBody
    public RespEntity getMyFollowedUser(HttpServletRequest request ,@RequestBody Map<String,String> info){
        Integer userId = info.get("userId")==null?WebUtils.getUserId(request):Integer.parseInt(info.get("userId"));
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<UserLogTemp> userLogTemps = followService.getFollowedUser(userId,
                (pageNum-1)*MyConst.MY_PAGE_NUM_OF_FANS_AND_FOLLOWS);
        if(userLogTemps != null){
            List<Object> list = new ArrayList<>();
            int count = followService.countFollowed(userId);
            list.add(count);
            list.add(userLogTemps);
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    static UserLogTemp toShowUser(Integer userId,FollowService followService,UserDynamicService dynamicService,
                                  UserService userService){
        User user = userService.getById(userId);
        UserLogTemp userLogTemp = null;
        if(user != null){
            userLogTemp = user.toJsonUser();
            userLogTemp.setFollowedNum(followService.countFollowed(userId));
            userLogTemp.setFansNum(followService.countFollowers(userId));
            userLogTemp.setUserDynamicNum(dynamicService.countById(userId));
        }
        return userLogTemp;
    }
}

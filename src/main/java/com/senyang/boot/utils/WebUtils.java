package com.senyang.boot.utils;

import com.senyang.boot.entity.User;
import com.senyang.boot.service.DynamicDoService;
import com.senyang.boot.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    public  static Integer getUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        int id = -1;
        for(Cookie cookie:cookies){
            if("userId".equals(cookie.getName())){
                id = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        return id;
    }
    public static boolean isNotManger(HttpServletRequest request, UserService userService){
        int userId = WebUtils.getUserId(request);
        User user = userService.getById(userId);
        return '1' != user.getUserType();
    }
    public static boolean isNotUser(HttpServletRequest request,Integer userId){
        int user_id = WebUtils.getUserId(request);
        return user_id != userId;
    }
}

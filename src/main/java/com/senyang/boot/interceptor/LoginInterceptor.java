package com.senyang.boot.interceptor;

import com.senyang.boot.constant.MyConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class LoginInterceptor implements HandlerInterceptor {




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(request.getRequestURI().contains("updateUser")){
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie:cookies){
                if("updateP".equals(cookie.getName())){
                    return true;
                }
            }
        }
        Cookie cookie1 = null;
        String userId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(MyConst.COOKIE_VALUE.equals(cookie.getValue())){
                    cookie1 = cookie;
                }
                if("userId".equals(cookie.getName())){
                    userId = cookie.getValue();
                }
            }
        }
        if(cookie1!=null && userId != null){
            return session.getAttribute(userId) != null && MyConst.COOKIE_VALUE.equals(cookie1.getValue());
        }
//        request.setAttribute("msg","请先登录");
//        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

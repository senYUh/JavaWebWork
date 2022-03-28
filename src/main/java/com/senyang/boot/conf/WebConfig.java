package com.senyang.boot.conf;

import com.senyang.boot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/img/**","/json.do","/favicon.ico","/upLoadImg",
                        "/register","/PwdQuestion","/getUserPwdQues","/retrieve","/file/**","/judge");
    }

}

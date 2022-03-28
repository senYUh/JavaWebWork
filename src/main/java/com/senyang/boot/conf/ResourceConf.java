package com.senyang.boot.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConf implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\";
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+path);
        path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\file\\";
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+path);
    }
}

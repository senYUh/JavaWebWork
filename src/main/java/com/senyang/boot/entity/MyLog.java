package com.senyang.boot.entity;


import org.springframework.stereotype.Component;

@Component
public class MyLog {
    StringBuilder log = new StringBuilder("");
    public void append(String str){
        log.append(str);
    }

    public String getLog() {
        return String.valueOf(log);
    }
}

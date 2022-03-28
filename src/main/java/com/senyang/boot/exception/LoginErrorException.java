package com.senyang.boot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "登录信息错误")
public class LoginErrorException extends RuntimeException {
    private static final long serialVersionUID = 4761334351908483089L;

    public LoginErrorException(){};
    public LoginErrorException(String s){
        super(s);
    }
}

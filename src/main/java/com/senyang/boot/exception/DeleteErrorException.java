package com.senyang.boot.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "删除信息错误")
public class DeleteErrorException extends RuntimeException{
    private static final long serialVersionUID = -2021084859483530325L;

    public DeleteErrorException(){};
    public DeleteErrorException(String s){
        super(s);
    }
}

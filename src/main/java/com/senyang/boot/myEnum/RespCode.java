package com.senyang.boot.myEnum;

public enum RespCode {
    SUCCESS(0, "请求成功"),
    FAIL(-1,"用户信息错误"),
    LOGIN_WARN(-2, "未登录"),
    REGISTER_ERROR(-3,"保存失败"),
    GET_INFO_ERROR(-5,"获取失败"),
    FILE_UPLOAD_ERROR(-6,"文件上传失败"),
    HEADER_IMG_ERROR(-4,"头像上传失败");


    private final int code;
    private final String msg;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}

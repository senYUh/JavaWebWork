package com.senyang.boot.utils;

public class StringUtils {
    public static String getSuffix(String str){
        String suffix = "";
        int num = str.lastIndexOf(".");
        suffix = str.substring(num);
        return suffix;
    }
    public static String replaceDouble(String str){
        return str.replace("\"", "'");
    }
}

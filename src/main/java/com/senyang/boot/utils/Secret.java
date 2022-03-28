package com.senyang.boot.utils;

import com.senyang.boot.constant.MyConst;

public class Secret {
    public static int doSecret(Integer num){
        num -= MyConst.utilNum;
        return (num+1756)*17;
    }
    public static int unSecret(Integer num){
        return (num/17)-1756+MyConst.utilNum;
    }
}

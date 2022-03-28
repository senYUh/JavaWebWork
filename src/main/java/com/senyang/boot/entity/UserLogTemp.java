package com.senyang.boot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogTemp {
    private String userName;
    private Integer userId;
    private String userAddress;
    private char userSex;
    private Date userBirth;
    private String userImgUrl;
    private int fansNum;
    private int followedNum;
    private int userDynamicNum;
    public UserLogTemp(String userName,Integer userId,String userAddress,char userSex,Date userBirth,String userImgUrl){
        this.userName = userName;
        this.userAddress = userAddress;
        this.userId = userId;
        this.userSex = userSex;
        this.userBirth = userBirth;
        this.userImgUrl = userImgUrl;
    }
}

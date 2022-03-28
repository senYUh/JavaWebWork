package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("user_base_info")
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    private String userAddress;
    private String userName;
    private char userSex;
    private String userPwd;
    private char userType;
    private Date userBirth;
    private String userImgUrl;
    public UserLogTemp toJsonUser(){
        return new UserLogTemp(userName,userId,userAddress,userSex,userBirth,userImgUrl);
    }
}

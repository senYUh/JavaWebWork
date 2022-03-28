package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserComment {

    private Integer commentId;
    private Integer userId;
    private Integer dynamicId;
    private String commentContent;
    private String commentDate;
    private String userImgUrl;
    private String userName;

    public UserComment(Integer userId,Integer dynamicId, String commentContent,String commentDate){
        this.userId = userId;
        this.dynamicId = dynamicId;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }
}

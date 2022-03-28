package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("user_mibao")
public class UserMibao {
    @TableId("user_id")
    private Integer userId;
    private Integer questionId;
    private String questionAnswer;
}

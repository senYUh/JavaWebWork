package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("pwd_question")
public class PwdQuestion {
    @TableId("pwd_question_id")
    private Integer pwdQuestionId;
    private String pwdQuestion;
}

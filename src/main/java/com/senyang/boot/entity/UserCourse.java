package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("user_course")
public class UserCourse {
    private Integer userId;
    private String courseName;
    private Integer courseInWeek;
    private Integer courseInDay;
}

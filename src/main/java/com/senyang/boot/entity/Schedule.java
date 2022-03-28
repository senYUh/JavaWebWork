package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("schedule")
public class Schedule {
    @TableId(value = "schedule_id",type = IdType.AUTO)
    private Integer scheduleId;
    private String date;
    private String content;
}

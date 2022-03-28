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
@TableName("matter")
public class Matter {
    @TableId(value = "matter_id",type = IdType.AUTO)
    private int matterId;
    private int userId;
    private boolean matterType;
    private int matterPriority;
    private String matterTitle;
    private boolean matterState;
}

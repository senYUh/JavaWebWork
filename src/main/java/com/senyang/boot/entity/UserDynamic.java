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
@TableName("user_dynamic")
public class UserDynamic {
    @TableId(value = "dynamic_id",type = IdType.AUTO)
    private Integer dynamicId;
    private Integer userId;
    private String dynamicTitle;
    private String dynamicText;
    private String dynamicReleaseDate;
    private Integer dynamicThumbNums;
    private Integer dynamicCollectionNums;
}

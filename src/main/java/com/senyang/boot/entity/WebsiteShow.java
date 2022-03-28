package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("website")
public class WebsiteShow {


    @TableId(value = "website_id",type = IdType.AUTO)
    private Integer websiteId;
    private String websiteName;
    private String websiteUrl;
    private Integer websiteType;
}

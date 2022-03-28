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
@TableName("website_category")
public class WebsiteCategory {

    @TableId(value = "category_id",type = IdType.AUTO)
    private Integer categoryId;
    private String categoryName;
    private String categoryType;
}

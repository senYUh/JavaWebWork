package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("home_page_img")
public class HomePageImg {
    @TableId("home_page_img_id")
    private Integer homePageImgId;
    private String imgUrl;
    private boolean selected;
}

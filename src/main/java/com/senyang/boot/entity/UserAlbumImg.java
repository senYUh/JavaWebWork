package com.senyang.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("user_album_img")
public class UserAlbumImg {
    @TableId("img_id")
    private int imgId;
    private int userId;
    private int albumId;
    private String imgCreateDate;
    private String imgUrl;
}

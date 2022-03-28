package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("user_album")
public class UserAlbum {
    @TableId("album_id")
    private int albumId;
    private String albumName;
    private int albumUserId;
    private String albumCreateDate;
    private String albumDescription;
    private boolean albumAuthority;

    public boolean getAlbumAuthority(){
        return this.albumAuthority;
    }
}

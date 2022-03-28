package com.senyang.boot.entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserAlbumImgShow {
    private String albumName;
    private String imgUrl;
    private Boolean selected;
}

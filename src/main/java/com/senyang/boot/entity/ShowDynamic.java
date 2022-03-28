package com.senyang.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShowDynamic {
    private Integer dynamicId;
    private Integer userId;
    private String dynamicTitle;
    private String dynamicText;
    private String dynamicReleaseDate;
    private String userImgUrl;
    private String userName;
    private Integer dynamicThumbNums;
    private Integer dynamicCollectionNums;
}

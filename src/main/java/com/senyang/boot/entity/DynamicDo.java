package com.senyang.boot.entity;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DynamicDo {
    private int userId;
    private int dynamicId;
    private boolean thumbsUp;
    private boolean collection;

    public Boolean getThumbsUp(){
        return thumbsUp;
    }
    public Boolean getCollection(){
        return collection;
    }
}

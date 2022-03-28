package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.UserAlbumImg;
import com.senyang.boot.entity.UserAlbumImgShow;

import java.util.List;

public interface UserAlbumImgService extends IService<UserAlbumImg> {
    int addUserImg(UserAlbumImg img);
    int countImg(Integer albumId);
    int deleteImg(Integer imgId);
    List<UserAlbumImgShow> getAllImg(List<Integer> albumId, Integer pageNum);
    List<UserAlbumImg> getAllByAlbumId(Integer albumId);
    UserAlbumImg getEarly(Integer albumId);
}

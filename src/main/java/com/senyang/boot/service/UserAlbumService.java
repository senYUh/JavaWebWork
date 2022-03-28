package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.UserAlbum;

import java.util.List;

public interface UserAlbumService extends IService<UserAlbum> {
    int addAlbum(UserAlbum album);
    int deleteAlbumByAlbumId(Integer albumId);
    int updateAlbum(UserAlbum album);
    List<Integer> getAllOutId();
    List<UserAlbum> getAlbumByUserId(Integer userId);
    List<UserAlbum> getAllAlbumToMan(Integer pageNum);
}

package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.UserAlbum;
import com.senyang.boot.mapper.UserAlbumImgMapper;
import com.senyang.boot.mapper.UserAlbumMapper;
import com.senyang.boot.service.UserAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserAlbumServiceImpl extends ServiceImpl<UserAlbumMapper, UserAlbum> implements UserAlbumService {

    @Autowired
    UserAlbumMapper userAlbumMapper;
    @Autowired
    UserAlbumImgMapper albumImgMapper;


    @Override
    public int addAlbum(UserAlbum album) {
        return userAlbumMapper.insertAlbum(album);
    }

    @Override
    public int deleteAlbumByAlbumId(Integer albumId) {
        albumImgMapper.deleteImgByAlbumId(albumId);
        return userAlbumMapper.deleteAlbumByAlbumId(albumId);
    }

    @Override
    public int updateAlbum(UserAlbum album) {
        return userAlbumMapper.updateAlbum(album);
    }

    @Override
    public List<Integer> getAllOutId() {
        return userAlbumMapper.getOutId();
    }

    @Override
    public List<UserAlbum> getAlbumByUserId(Integer userId) {
        return userAlbumMapper.getAllAlbumByUserId(userId);
    }

    @Override
    public List<UserAlbum> getAllAlbumToMan(Integer pageNum) {
        return userAlbumMapper.getAllAlbumToMan(pageNum);
    }
}

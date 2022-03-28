package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.UserAlbumImg;
import com.senyang.boot.entity.UserAlbumImgShow;
import com.senyang.boot.mapper.UserAlbumImgMapper;
import com.senyang.boot.service.UserAlbumImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAlbumImgServiceImpl extends ServiceImpl<UserAlbumImgMapper, UserAlbumImg>
        implements UserAlbumImgService {

    @Autowired
    UserAlbumImgMapper userAlbumImgMapper;

    @Override
    public int addUserImg(UserAlbumImg img) {
        return userAlbumImgMapper.insert(img);
    }

    @Override
    public int countImg(Integer albumId) {
        return userAlbumImgMapper.countByAlbumId(albumId);
    }

    @Override
    public int deleteImg(Integer imgId) {
        return userAlbumImgMapper.deleteById(imgId);
    }

    @Override
    public List<UserAlbumImgShow> getAllImg(List<Integer> albumId, Integer pageNum) {
        return userAlbumImgMapper.getAllImg(albumId,pageNum);
    }

    @Override
    public List<UserAlbumImg> getAllByAlbumId(Integer albumId) {
        return userAlbumImgMapper.getAllByAlbumId(albumId);
    }

    @Override
    public UserAlbumImg getEarly(Integer albumId) {
        return userAlbumImgMapper.getEarlyImg(albumId);
    }
}

package com.senyang.boot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.UserAlbum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAlbumMapper extends BaseMapper<UserAlbum> {
    int insertAlbum(UserAlbum album);
    int updateAlbum(UserAlbum album);
    int deleteAlbumByAlbumId(Integer albumId);
    List<UserAlbum> getAllAlbumByUserId(Integer userId);
    List<Integer> getOutId();
    List<UserAlbum> getAllAlbumToMan(Integer pageNum);
}

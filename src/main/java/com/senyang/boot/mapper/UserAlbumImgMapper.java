package com.senyang.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.UserAlbumImg;
import com.senyang.boot.entity.UserAlbumImgShow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAlbumImgMapper extends BaseMapper<UserAlbumImg> {
    int insert(UserAlbumImg img);
    int countByAlbumId(Integer albumId);
    int deleteImgByAlbumId(Integer albumId);
    List<UserAlbumImg> getAllByAlbumId(Integer albumId);
    List<UserAlbumImgShow> getAllImg(List<Integer> albumId, Integer pageNum);
    UserAlbumImg getEarlyImg(Integer albumId);
}

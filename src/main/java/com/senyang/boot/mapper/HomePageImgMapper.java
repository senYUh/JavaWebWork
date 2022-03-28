package com.senyang.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.HomePageImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomePageImgMapper extends BaseMapper<HomePageImg> {
    boolean isSelected(String imgUrl);
    HomePageImg getImgByUrl(String url);
    int insertImg(HomePageImg img);
    int updateImg(HomePageImg img);
    List<HomePageImg> getImgToShow(Integer start);
    int deleteImg(Integer id);
}

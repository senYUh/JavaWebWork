package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.HomePageImg;

import java.util.List;

public interface HomePageImgService extends IService<HomePageImg> {
    boolean judgeSelect(String imgUrl);
    int updateImg(HomePageImg img);
    HomePageImg getImgByUrl(String url);
    int deleteImg(Integer homePageImgId);
    int addImgToShow(HomePageImg img);
    List<HomePageImg> getImgToShow(int start);

}

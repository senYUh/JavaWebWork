package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.HomePageImg;
import com.senyang.boot.mapper.HomePageImgMapper;
import com.senyang.boot.service.HomePageImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HomePageImgServiceImpl extends ServiceImpl<HomePageImgMapper, HomePageImg> implements HomePageImgService {

    @Autowired
    HomePageImgMapper homePageImgMapper;

    @Override
    public boolean judgeSelect(String imgUrl) {
        return homePageImgMapper.isSelected(imgUrl);
    }

    @Override
    public int updateImg(HomePageImg img) {
        return homePageImgMapper.updateImg(img);
    }

    @Override
    public HomePageImg getImgByUrl(String url) {
        return homePageImgMapper.getImgByUrl(url);
    }

    @Override
    public int addImgToShow(HomePageImg img) {
        return homePageImgMapper.insertImg(img);
    }

    @Override
    public List<HomePageImg> getImgToShow(int start) {
        return homePageImgMapper.getImgToShow(start);
    }

    @Override
    public int deleteImg(Integer homePageImgId) {
        return homePageImgMapper.deleteImg(homePageImgId);
    }
}

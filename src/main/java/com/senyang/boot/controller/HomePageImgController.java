package com.senyang.boot.controller;

import com.senyang.boot.entity.HomePageImg;
import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.HomePageImgService;
import com.senyang.boot.service.UserService;
import com.senyang.boot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class HomePageImgController {

    @Autowired
    HomePageImgService homePageImgService;
    @Autowired
    UserService userService;

    @PostMapping("upHPG")
    @ResponseBody
    public RespEntity updateHomePageImg(HttpServletRequest request , @RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        String url = info.get("imgUrl");
        if(url == null){
            return new RespEntity(RespCode.GET_INFO_ERROR,null);
        }
        HomePageImg img = homePageImgService.getImgByUrl(url);
        if (img == null) {
            img = new HomePageImg();
            img.setImgUrl(info.get("url"));
        }
        img.setSelected(Integer.parseInt(info.get("selected"))==1);
        int res = homePageImgService.updateImg(img);
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,img);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("delHPG")
    @ResponseBody
    public RespEntity deleteHomePage(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("getIMGTS")
    @ResponseBody
    public RespEntity getIMG(){
        List<HomePageImg> imgs = homePageImgService.list();
        if(imgs != null){
            return new RespEntity(RespCode.SUCCESS,imgs);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }
}

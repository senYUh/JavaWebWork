package com.senyang.boot.controller;


import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.entity.WebsiteCategory;
import com.senyang.boot.entity.WebsiteShow;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.UserService;
import com.senyang.boot.service.WebsiteService;
import com.senyang.boot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class WebsiteController {

    @Autowired
    WebsiteService websiteService;
    @Autowired
    UserService userService;

    @PostMapping("/addCATE")
    @ResponseBody
    public RespEntity addCate(HttpServletRequest request, @RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request, userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        String cateName =info.get("tag");
        String cateType =info.get("type");
        WebsiteCategory websiteCategory = new WebsiteCategory();
        websiteCategory.setCategoryName(cateName);
        websiteCategory.setCategoryType(cateType);
        int res = websiteService.addCategory(websiteCategory);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,websiteCategory);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/addWB")
    @ResponseBody
    public RespEntity addWeb(@RequestBody Map<String,String> info){
        String websiteName = info.get("name");
        String websiteUrl = info.get("address");
        Integer websiteType = Integer.parseInt(info.get("classify"));
        WebsiteShow websiteShow = new WebsiteShow();
        websiteShow.setWebsiteName(websiteName);
        websiteShow.setWebsiteUrl(websiteUrl);
        websiteShow.setWebsiteType(websiteType);
        int res = websiteService.addWebsite(websiteShow);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,websiteShow);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/rmCA")
    @ResponseBody
    public RespEntity delCate(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request, userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        int cateId = Integer.parseInt(info.get("cateId"));
        int res = websiteService.rmCategory(cateId);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/rmWB")
    @ResponseBody
    public RespEntity delWeb(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request, userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        int webId = Integer.parseInt(info.get("webId"));
        int res = websiteService.rmWebsite(webId);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/upWB")
    @ResponseBody
    public RespEntity updateWeb(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        WebsiteShow websiteShow = new WebsiteShow();
        websiteShow.setWebsiteId(Integer.parseInt(info.get("id")));
        websiteShow.setWebsiteName(info.get("name"));
        websiteShow.setWebsiteUrl(info.get("url"));
        int res = websiteService.updateWebsite(websiteShow);
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,websiteShow);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/getACA")
    @ResponseBody
    public RespEntity getAllCate(){
        List<WebsiteCategory> categories = websiteService.getAllWebsiteCategory();
        if(categories != null){
            return  new RespEntity(RespCode.SUCCESS,categories);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/getWBT")
    @ResponseBody
    public RespEntity getWebByTy(@RequestBody Map<String,String> info){
        int type = Integer.parseInt(info.get("type"));
        List<WebsiteShow> websiteShows = websiteService.getWebsiteByType(type);
        if(websiteShows != null){
            return new RespEntity(RespCode.SUCCESS,websiteShows);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }
    @PostMapping("/getAWB")
    @ResponseBody
    public RespEntity getAllWeb(){
        List<WebsiteShow> websiteShows = websiteService.getAllWebsite();
        if(websiteShows != null){
            return new RespEntity(RespCode.SUCCESS,websiteShows);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }



}

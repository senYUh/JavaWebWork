package com.senyang.boot.controller;


import com.senyang.boot.constant.MyConst;
import com.senyang.boot.entity.Matter;
import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.MatterService;
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
public class MatterController {

    @Autowired
    MatterService service;

    @PostMapping("/getBTE")
    @ResponseBody
    public RespEntity getByType(HttpServletRequest request, @RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        boolean type = Integer.parseInt(info.get("type"))==1;
        List<Matter> list = service.getByType(type,userId);
        if(list != null){
            return  new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/insMA")
    @ResponseBody
    public RespEntity insertMatter(HttpServletRequest request,@RequestBody Map<String,String> info){
        boolean type = Integer.parseInt(info.get("type"))==1;
        int matterPriority = Integer.parseInt(info.get("sort"));
        String title = info.get("title");
        boolean state = Integer.parseInt(info.get("state"))==1;
        Matter matter = new Matter();
        matter.setMatterType(type);
        matter.setMatterPriority(matterPriority);
        matter.setMatterTitle(title);
        matter.setMatterState(state);
        if(type){
            matter.setUserId(WebUtils.getUserId(request));
        }else {
            matter.setUserId(MyConst.MY_USERID_IN_MATTER);
        }
        int res = service.addMatter(matter);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,matter);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/upMA")
    @ResponseBody
    public RespEntity updateMatter(HttpServletRequest request,@RequestBody Map<String,String> info){
        Matter matter = service.getById(Integer.parseInt(info.get("id")));
        Integer userId = WebUtils.getUserId(request);
        int res = 0;
        if(matter != null){
            if(userId != matter.getUserId() && matter.getUserId() != MyConst.MY_USERID_IN_MATTER){
                return new RespEntity(RespCode.FAIL,null);
            }
            if(info.get("state") != null){
                matter.setMatterState(Integer.parseInt(info.get("state"))==1);
            }else if (info.get("title") != null){
                matter.setMatterTitle(info.get("title"));
            }else if(info.get("sort") != null){
                matter.setMatterPriority(Integer.parseInt(info.get("sort")));
            }
            res = service.updateMatter(matter);
        }
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,matter);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }
    @PostMapping("/delMA")
    @ResponseBody
    public RespEntity deleteMatter(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Matter matter = service.getById(Integer.parseInt(info.get("id")));
        int res = 0;
        if(matter != null){
            if(matter.getUserId() != MyConst.MY_USERID_IN_MATTER && userId != matter.getUserId()){
                return new RespEntity(RespCode.FAIL,null);
            }
            res = service.removeMatter(matter.getMatterId(),matter.getUserId());
        }
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }
}

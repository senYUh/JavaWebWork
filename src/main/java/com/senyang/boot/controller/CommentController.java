package com.senyang.boot.controller;


import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.entity.UserComment;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.UserCommentService;
import com.senyang.boot.service.UserService;
import com.senyang.boot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class CommentController {

    @Autowired
    UserCommentService userCommentService;
    @Autowired
    UserService userService;


    @PostMapping("/getCBDI")
    @ResponseBody
    public RespEntity getCommentByDynamicId(@RequestBody Map<String,String> info){
        Integer dynamicId = Integer.parseInt(info.get("dynamicId"));
        List<UserComment> comments = userCommentService.getCommentByDynamicId(dynamicId);
        if(comments != null){
            return new RespEntity(RespCode.SUCCESS,comments);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }


    @PostMapping("/comment")
    @ResponseBody
    public RespEntity commentDynamic(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Integer dynamicId = Integer.parseInt(info.get("dynamicId"));
        String content = info.get("commentContent");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        UserComment comment = new UserComment(userId,dynamicId,content,date);
        int res = userCommentService.commentDynamic(comment);
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,comment);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/delCBI")
    @ResponseBody
    public RespEntity deleteCommentByCommentId(HttpServletRequest request,@RequestBody Map<String,String> info){
        boolean jurisdiction = false;
        Integer commentId = Integer.parseInt(info.get("commentId"));
        if(!WebUtils.isNotManger(request,userService)){
            jurisdiction = true;
        }else {
            UserComment comment = userCommentService.getOne(commentId);
            if(comment != null && comment.getUserId().equals(WebUtils.getUserId(request))){
                jurisdiction = true;
            }
        }
        if(jurisdiction){
            int res = userCommentService.deleteCommentByCommentId(commentId);
            if(res == 1){
                return new RespEntity(RespCode.SUCCESS,null);
            }
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }
}

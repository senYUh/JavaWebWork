package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.UserComment;

import java.util.List;

public interface UserCommentService extends IService<UserComment> {
    List<UserComment> getCommentByDynamicId(Integer dynamicId);
    int commentDynamic(UserComment comment);
    int deleteCommentByCommentId(Integer commentId);
    UserComment getOne(Integer commentId);
    int deleteByDynamicId(Integer dynamicId);
}

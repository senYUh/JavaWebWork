package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.UserComment;
import com.senyang.boot.mapper.UserCommentMapper;
import com.senyang.boot.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserCommentServiceImpl extends ServiceImpl<UserCommentMapper, UserComment> implements UserCommentService {

    @Autowired
    UserCommentMapper userCommentMapper;


    @Override
    public List<UserComment> getCommentByDynamicId(Integer dynamicId) {
        return userCommentMapper.getCommentByDynamicId(dynamicId);
    }

    @Override
    public int commentDynamic(UserComment comment) {
        return userCommentMapper.insertComment(comment);
    }

    @Override
    public int deleteCommentByCommentId(Integer commentId) {
        return userCommentMapper.deleteComment(commentId);
    }

    @Override
    public UserComment getOne(Integer commentId) {
        return userCommentMapper.getOne(commentId);
    }

    @Override
    public int deleteByDynamicId(Integer dynamicId) {
        return userCommentMapper.deleteByDynamicId(dynamicId);
    }
}

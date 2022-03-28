package com.senyang.boot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.UserComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCommentMapper extends BaseMapper<UserComment> {
    List<UserComment> getCommentByDynamicId(Integer commentId);
    int insertComment(UserComment comment);
    int deleteComment(Integer commentId);
    UserComment getOne(Integer commentId);
    int deleteByDynamicId(Integer dynamicId);
}

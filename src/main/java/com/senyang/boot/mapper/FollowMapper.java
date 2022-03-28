package com.senyang.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.Follow;
import com.senyang.boot.entity.UserLogTemp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper extends BaseMapper<Follow> {
    int countFollowers(Integer userId);
    int countMyFollowed(Integer userId);
    int followUser(Follow follow);
    int unFollow(Follow follow);
    int isFollow(Follow follow);
    List<Follow> getMyFollowed(Integer userId);
    List<UserLogTemp> getFollowersToLog(Integer userId,Integer pageNum);
    List<UserLogTemp> getFollowedUserLog(Integer userId,Integer pageNum);
}

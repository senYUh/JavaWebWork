package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.Follow;
import com.senyang.boot.entity.UserLogTemp;
import com.senyang.boot.mapper.FollowMapper;
import com.senyang.boot.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Autowired
    FollowMapper followMapper;

    @Override
    public int countFollowers(Integer userId) {
        return followMapper.countFollowers(userId);
    }

    @Override
    public int countFollowed(Integer userId) {
        return followMapper.countMyFollowed(userId);
    }

    @Override
    public int follow(Follow follow) {
        return followMapper.followUser(follow);
    }

    @Override
    public int unFollow(Follow follow) {
        return followMapper.unFollow(follow);
    }

    @Override
    public int isFollow(Follow follow) {
        return followMapper.isFollow(follow);
    }

    @Override
    public List<UserLogTemp> getFollowers(Integer userId, Integer pageNum) {
        return followMapper.getFollowersToLog(userId,pageNum);
    }

    @Override
    public List<UserLogTemp> getFollowedUser(Integer userId, Integer pageNum) {
        return followMapper.getFollowedUserLog(userId,pageNum);
    }

    @Override
    public List<Follow> getFollowed(Integer userId) {
        return followMapper.getMyFollowed(userId);
    }
}

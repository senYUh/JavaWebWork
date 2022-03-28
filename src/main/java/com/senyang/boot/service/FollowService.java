package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.Follow;
import com.senyang.boot.entity.UserLogTemp;

import java.util.List;

public interface FollowService extends IService<Follow> {
    int countFollowers(Integer userId);
    int countFollowed(Integer userId);
    int follow(Follow follow);
    int unFollow(Follow follow);
    int isFollow(Follow follow);
    List<UserLogTemp> getFollowers(Integer userId,Integer pageNum);
    List<UserLogTemp> getFollowedUser(Integer userId,Integer pageNum);
    List<Follow> getFollowed(Integer userId);
}

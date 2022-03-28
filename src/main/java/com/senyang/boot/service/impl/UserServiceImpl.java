package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.User;
import com.senyang.boot.mapper.UserMapper;
import com.senyang.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User login(String userName, String userPwd) {
        return userMapper.login(userName,userPwd);
    }

    @Override
    public int addUser(User user) {
        if(user.getUserImgUrl() == null){
            return userMapper.insertUserWithoutImg(user);
        }
        return userMapper.insertUser(user);
    }

    @Override
    public User getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public int updateUserByManger(User uSer) {
        return userMapper.updateUserByM(uSer);
    }

    @Override
    public int updateUserImgUrl(String url, Integer userId) {
        return userMapper.updateUserImgUrl(url,userId);
    }

}

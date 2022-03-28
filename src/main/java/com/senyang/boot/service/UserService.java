package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.User;

public interface UserService extends IService<User> {
     User login(String userName,String userPwd);
     int addUser(User user);
     User getByName(String name);
     int updateUserByManger(User uSer);
     int updateUserImgUrl(String url,Integer userId);
}

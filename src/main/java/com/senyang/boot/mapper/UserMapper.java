package com.senyang.boot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    User login(String userName,String userPwd);
    int insertUser(User user);
    User getByName(String userName);
    int insertUserWithoutImg(User user);
    int updateUserByM(User user);
    int updateUserImgUrl(String url,Integer userId);
}

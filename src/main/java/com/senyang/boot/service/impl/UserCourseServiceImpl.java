package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.UserCourse;
import com.senyang.boot.mapper.UserCourseMapper;
import com.senyang.boot.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse> implements UserCourseService {

    @Autowired
    UserCourseMapper userCourseMapper;


    @Override
    public int addCourse(UserCourse course) {
        return userCourseMapper.addCourse(course);
    }

    @Override
    public int deleteCourse(UserCourse course) {
        return userCourseMapper.deleteCourse(course);
    }

    @Override
    public UserCourse getOne(Integer userId, Integer courseInWeek, Integer courseInDay) {
        return userCourseMapper.getOne(userId,courseInWeek,courseInDay);
    }


    @Override
    public int updateCourse(UserCourse course,UserCourse courseT) {
        userCourseMapper.deleteOne(course);
        return userCourseMapper.addCourse(courseT);
    }

    @Override
    public List<UserCourse> getCourses(Integer userId) {
        return userCourseMapper.getAllCourseByUserId(userId);
    }
}

package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.UserCourse;

import java.util.List;

public interface UserCourseService extends IService<UserCourse> {
    int addCourse(UserCourse course);
    int deleteCourse(UserCourse course);
    UserCourse getOne(Integer userId,Integer courseInWeek,Integer courseInDay);
    int updateCourse(UserCourse course,UserCourse courseT);
    List<UserCourse> getCourses(Integer userId);
}

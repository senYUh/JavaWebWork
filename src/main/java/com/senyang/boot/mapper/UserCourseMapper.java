package com.senyang.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.UserCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCourseMapper extends BaseMapper<UserCourse> {
    int addCourse(UserCourse course);
    int deleteCourse(UserCourse course);
    int deleteOne(UserCourse course);
    UserCourse getOne(Integer userId,Integer courseInWeek,Integer courseInDay);
    List<UserCourse> getAllCourseByUserId(Integer userId);
}

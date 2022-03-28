package com.senyang.boot.controller;


import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.entity.UserCourse;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.UserCourseService;
import com.senyang.boot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {


    @Autowired
    UserCourseService userCourseService;


    @PostMapping("/addCO")
    @ResponseBody
    public RespEntity addCourse(HttpServletRequest request, @RequestBody Map<String,String> info){
        UserCourse courseT = loadUserCourse(request,info);
        Integer userId = WebUtils.getUserId(request);
        UserCourse course = userCourseService.getOne(userId,courseT.getCourseInWeek(),courseT.getCourseInDay());
        int res;
        if(course != null){
            res = userCourseService.updateCourse(course,courseT);
        }else {
            res = userCourseService.addCourse(courseT);
        }
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,course);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/delete")
    @ResponseBody
    public RespEntity deleteCourse(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        String name = info.get("courseName");
        UserCourse course = new UserCourse();
        course.setUserId(userId);
        course.setCourseName(name);
        int res = userCourseService.deleteCourse(course);
        if(res > 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/getMyAllCourse")
    @ResponseBody
    public RespEntity getAllCourse(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId = info.get("userId") == null ? WebUtils.getUserId(request):Integer.parseInt(info.get("userId"));
        List<UserCourse> courses = userCourseService.getCourses(userId);
        if(courses != null){
            return new RespEntity(RespCode.SUCCESS,courses);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }


    static UserCourse loadUserCourse(HttpServletRequest request,Map<String,String> info){
        int userId = WebUtils.getUserId(request);
        String courseName = info.get("courseName");
        Integer courseWeek = Integer.parseInt(info.get("courseInWeek"));
        Integer courseDay = Integer.parseInt(info.get("courseInDay"));
        return new UserCourse(userId,courseName,courseWeek,courseDay);
    }
}

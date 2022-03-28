package com.senyang.boot;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.senyang.boot.entity.*;
import com.senyang.boot.service.*;
import com.senyang.boot.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BootApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    UserDynamicService dynamicService;
    @Autowired
    DynamicDoService dynamicDoService;
    @Autowired
    MatterService matterService;
    @Autowired
    FollowService followService;
    @Autowired
    UserCourseService courseService;
    @Autowired
    UserCommentService userCommentService;


    @Test
    void contextLoads() {
        System.out.println(StringUtils.getSuffix("rea.txt"));
    }
    @Test
    void userTest(){
        User user = new User();
        user.setUserName("test");
        user.setUserPwd("123@228.com");
        user.setUserImgUrl("aaaaaaa");
        userService.addUser(user);
        System.out.println(user);
    }
    @Test
    void dynamicTest(){
        Page<UserDynamic> page = new Page<>(1,5);
        Page<UserDynamic> page1 = dynamicService.page(page,null);
        List<UserDynamic> dynamics = page1.getRecords();
        for(UserDynamic dynamic:dynamics){
            System.out.println(dynamic.getDynamicText());
        }
    }
    @Test
    void getDynamicTest(){
        List<ShowDynamic> list = dynamicService.getAllByList(1);
        for(ShowDynamic dynamic : list){
            System.out.println(dynamic);
        }
        System.out.println(dynamicService.count());
    }
    @Test
    void dynamicDoTest(){
       DynamicDo dynamicDo = dynamicDoService.getDynamicDo(1,2);
        System.out.println(dynamicDo.toString());
    }
    @Test
    void testMatter(){
        Matter matter = new Matter();
        matter.setMatterId(6);
        matter.setMatterType(true);
        matter.setMatterState(true);
        matter.setMatterTitle("test222");
        matter.setMatterPriority(1);
        matter.setUserId(-98761234);
//      matterService.addMatter(matter);

//        matterService.removeMatter(3,1009);
//        matterService.getByType(true,1009);
//          matterService.updateMatter(matter);
    }
    @Test
    void testFollow(){
//        int res = followService.countFollowed(1007);
//        System.out.println(res);
//        res = followService.countFollowed(1009);
//        System.out.println(res);
//        res = followService.countFollowers(1009);
//        System.out.println(res);
//        Follow follow = new Follow(1007,1008);
//        int res = followService.follow(follow);
//        System.out.println(res);
//        List<UserLogTemp> userLogTemps = followService.getFollowers(1002,0);
//        for (UserLogTemp u: userLogTemps){
//            System.out.println(u.getUserName());
//        }
        List<UserLogTemp> logTemps = followService.getFollowedUser(1007,0);
        for (UserLogTemp u: logTemps){
            System.out.println(u.getUserName());
        }
    }
    @Test
    void testCourse(){
//        UserCourse course = new UserCourse(1009,"高数",1,2);
        UserCourse course2 = new UserCourse(1009,"高数",2,2);
//        courseService.updateCourse(course,course2);
        courseService.deleteCourse(course2);
//        List<UserCourse> courses = courseService.getCourses(1009);
//        for(UserCourse course : courses){
//            System.out.println(course.getCourseName());
        }


    @Test
    void testComment(){
//        for(int i = 1007 ; i <= 1015 ; i++){
//            UserComment comment = new UserComment(i,6,"test","2001-01-01 12:23:35");
//            userCommentService.commentDynamic(comment);
//        }
//        userCommentService.deleteCommentByCommentId(2);
        List<UserComment> list = userCommentService.getCommentByDynamicId(6);
        for(UserComment comment:list){
            System.out.println(comment.getUserId());
        }
    }
}

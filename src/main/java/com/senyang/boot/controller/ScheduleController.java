package com.senyang.boot.controller;


import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.entity.Schedule;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;


    @PostMapping("/addOrUpSC")
    @ResponseBody
    public RespEntity addSchedule(@RequestBody Map<String,String> info){
        String content = info.get("content");
        String date = info.get("date");
        Schedule schedule = scheduleService.getByDate(date);
        int res;
        if(schedule != null){
             schedule.setContent(content);
             res = scheduleService.upSchedule(schedule);
        }else {
            schedule = new Schedule();
            schedule.setDate(date);
            schedule.setContent(content);
            res = scheduleService.insContent(schedule);
        }
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,schedule);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }
    @PostMapping("/getAC")
    @ResponseBody
    public RespEntity getAllSchedule(){
        List<Schedule> list;
        list = scheduleService.list();
        if(list != null){
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }
//    @PostMapping("upSC")
//    @ResponseBody
//    public RespEntity updateSchedule(@RequestBody Map<String,String> info){
//        String date = info.get("date");
//        String content = info.get("content");
//        Schedule schedule = new Schedule();
//        schedule.setDate(date);
//        schedule.setContent(content);
//        int res = scheduleService.upSchedule(schedule);
//        if(res != 0){
//            return new RespEntity(RespCode.SUCCESS,null);
//        }
//        return new RespEntity(RespCode.GET_INFO_ERROR,null);
//    }
    @PostMapping("/delSC")
    @ResponseBody
    public RespEntity deleteSchedule(@RequestBody Map<String,String> info){
        Schedule schedule = new Schedule();
        Integer scheduleId = Integer.parseInt(info.get("id"));
        schedule.setScheduleId(scheduleId);
        int res = scheduleService.delSchedule(schedule);
        if(res != 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }
}

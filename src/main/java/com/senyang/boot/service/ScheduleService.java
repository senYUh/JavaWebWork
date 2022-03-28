package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.Schedule;

public interface ScheduleService extends IService<Schedule> {
    int insContent(Schedule schedule);
    Schedule getByDate(String date);
    int upSchedule(Schedule schedule);
    int delSchedule(Schedule schedule);
}

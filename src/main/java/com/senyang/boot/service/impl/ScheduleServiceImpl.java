package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.Schedule;
import com.senyang.boot.mapper.ScheduleMapper;
import com.senyang.boot.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public int insContent(Schedule schedule) {
        return scheduleMapper.addContent(schedule);
    }

    @Override
    public Schedule getByDate(String date) {
        return scheduleMapper.getByDate(date);
    }

    @Override
    public int upSchedule(Schedule schedule) {
        return scheduleMapper.updateSchedule(schedule);
    }

    @Override
    public int delSchedule(Schedule schedule) {
        return scheduleMapper.deleteSchedule(schedule);
    }
}

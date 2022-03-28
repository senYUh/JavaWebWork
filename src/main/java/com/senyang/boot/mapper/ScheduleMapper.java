package com.senyang.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    int addContent(Schedule schedule);
    Schedule getByDate(String date);
    int updateSchedule(Schedule schedule);
    int deleteSchedule(Schedule schedule);
}

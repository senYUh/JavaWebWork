package com.senyang.boot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.DynamicDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DynamicDoMapper extends BaseMapper<DynamicDo> {
    DynamicDo getDynamicDoInfoById(Integer userId,Integer dynamicId);
    int insertDynamicDoMes(DynamicDo dynamicDo);
    int updateDynamicDoMes(DynamicDo dynamicDo);
    int deleteDynamicDoMes(DynamicDo dynamicDo);
    int judge(Integer userId,Integer dynamicId);
    int deleteByDynamicId(Integer dynamicId);
}

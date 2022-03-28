package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.DynamicDo;


public interface DynamicDoService extends IService<DynamicDo> {
    DynamicDo getDynamicDo(Integer userId,Integer dynamicId);
    int addDynamicMes(DynamicDo dynamicDo);
    int deleteDynamicMes(DynamicDo dynamicDo);
    int updateDynamicMes(DynamicDo dynamicDo);
    int deleteByDynamicId(Integer dynamicId);
    int judge(Integer userId,Integer dynamicId);
}

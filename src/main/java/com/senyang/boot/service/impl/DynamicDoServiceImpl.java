package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.DynamicDo;
import com.senyang.boot.mapper.DynamicDoMapper;
import com.senyang.boot.service.DynamicDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DynamicDoServiceImpl extends ServiceImpl<DynamicDoMapper, DynamicDo> implements DynamicDoService {

    @Autowired
    DynamicDoMapper mapper;

    @Override
    public DynamicDo getDynamicDo(Integer userId, Integer dynamicId) {
        return mapper.getDynamicDoInfoById(userId,dynamicId);
    }

    @Override
    public int addDynamicMes(DynamicDo dynamicDo) {
        return mapper.insertDynamicDoMes(dynamicDo);
    }

    @Override
    public int deleteDynamicMes(DynamicDo dynamicDo) {
        return mapper.deleteDynamicDoMes(dynamicDo);
    }

    @Override
    public int updateDynamicMes(DynamicDo dynamicDo) {
        return mapper.updateDynamicDoMes(dynamicDo);
    }

    @Override
    public int deleteByDynamicId(Integer dynamicId) {
        return mapper.deleteByDynamicId(dynamicId);
    }

    @Override
    public int judge(Integer userId, Integer dynamicId) {
        return mapper.judge(userId,dynamicId);
    }

}

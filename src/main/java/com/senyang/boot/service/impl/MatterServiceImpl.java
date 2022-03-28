package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.Matter;
import com.senyang.boot.mapper.MatterMapper;
import com.senyang.boot.service.MatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatterServiceImpl extends ServiceImpl<MatterMapper, Matter> implements MatterService {


    @Autowired
    MatterMapper matterMapper;

    @Override
    public int addMatter(Matter matter) {
        return matterMapper.insertMatter(matter);
    }

    @Override
    public int removeMatter(Integer matterId, Integer userId) {
        return matterMapper.deleteMatterById(matterId,userId);
    }

    @Override
    public int updateMatter(Matter matter) {
        return matterMapper.updateMatter(matter);
    }

    @Override
    public List<Matter> getByType(boolean type, Integer userId) {
        return matterMapper.getAllByType(type,userId);
    }
}

package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.Matter;

import java.util.List;

public interface MatterService extends IService<Matter> {
    int addMatter(Matter matter);
    int removeMatter(Integer matterId,Integer userId);
    int updateMatter(Matter matter);
    List<Matter> getByType(boolean type,Integer userId);
}

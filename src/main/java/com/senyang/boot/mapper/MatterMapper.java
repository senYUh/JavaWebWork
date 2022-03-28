package com.senyang.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.Matter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MatterMapper extends BaseMapper<Matter> {
    int insertMatter(Matter matter);
    int deleteMatterById(Integer matterId,Integer userId);
    int updateMatter(Matter matter);
    List<Matter> getAllByType(boolean type,Integer userId);
}

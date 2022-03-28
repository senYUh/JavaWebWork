package com.senyang.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.Follow;
import com.senyang.boot.entity.ShowDynamic;
import com.senyang.boot.entity.UserDynamic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDynamicMapper extends BaseMapper<UserDynamic> {
    int uploadDynamic(UserDynamic dynamic);
    List<ShowDynamic> getUserAllDynamicById(Integer userId,Integer pageNum);
    List<ShowDynamic> getAllByList(Integer pageNum);
    int updateDynamic(UserDynamic dynamic);
    int deleteDynamic(Integer dynamicId);
    int thumbDynamic(Integer dynamicId);
    int collectionDynamic(Integer dynamicId);
    int unThumbDynamic(Integer dynamicId);
    int unCollectionDynamic(Integer dynamicId);
    int countDynamicById(Integer userId);
    List<ShowDynamic> getDynamicByThumb(Integer pageNum);
    List<ShowDynamic> getMyFollowedDynamic(List<Follow> list,Integer pageNum);
    List<ShowDynamic> getMyCollectedDynamic(Integer userId,Integer pageNum);
    ShowDynamic getDynamicToShow(Integer dynamicId);
    int countMyFollowedDyNum(List<Follow> list);
    int countMyCollectedDynamic(Integer userId);
}

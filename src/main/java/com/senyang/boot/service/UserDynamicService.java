package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.Follow;
import com.senyang.boot.entity.ShowDynamic;
import com.senyang.boot.entity.UserDynamic;

import java.util.List;

public interface UserDynamicService extends IService<UserDynamic> {
    int upLoadDynamic(UserDynamic dynamic);
    int deleteDynamic(Integer dynamicId,Integer userId);
    int updateDynamic(UserDynamic dynamic);
    int countMyFollowedDyN(List<Follow> list);
    int countMyCollectedDynamic(Integer userId);
    int thumbDynamic(Integer dynamicId);
    int collectionDynamic(Integer dynamicId);
    int unThumbDynamic(Integer dynamicId);
    int unCollectionDynamic(Integer dynamicId);
    int countById(Integer userId);
    ShowDynamic getDynamicToShow(Integer dynamicId);
    List<ShowDynamic> getDynamicByThumb(Integer pageNum);
    List<ShowDynamic> getMyFollowedDynamic(List<Follow> list,Integer pageNum);
    List<ShowDynamic> getMyCollectedDynamic(Integer userId,Integer pageNum);
    List<ShowDynamic> getUserAllDynamic(Integer userId,Integer pageNum);
    List<ShowDynamic> getAllByList(Integer pageNum);
}

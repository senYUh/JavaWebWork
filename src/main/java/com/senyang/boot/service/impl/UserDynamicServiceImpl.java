package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.Follow;
import com.senyang.boot.entity.ShowDynamic;
import com.senyang.boot.entity.UserDynamic;
import com.senyang.boot.exception.DeleteErrorException;
import com.senyang.boot.mapper.UserDynamicMapper;
import com.senyang.boot.service.DynamicDoService;
import com.senyang.boot.service.UserCommentService;
import com.senyang.boot.service.UserDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserDynamicServiceImpl extends ServiceImpl<UserDynamicMapper, UserDynamic> implements UserDynamicService {

    @Autowired
    UserDynamicMapper userDynamicMapper;
    @Autowired
    DynamicDoService dynamicDoService;
    @Autowired
    UserCommentService commentService;

    @Override
    public int upLoadDynamic(UserDynamic dynamic) {
        return userDynamicMapper.insert(dynamic);
    }

    @Override
    public int deleteDynamic(Integer dynamicId, Integer userId) {
        int res1 = dynamicDoService.deleteByDynamicId(dynamicId);
        int res2 = commentService.deleteByDynamicId(dynamicId);
        if(res1<0||res2<0){
            throw new DeleteErrorException();
        }
        return userDynamicMapper.deleteDynamic(dynamicId);
    }

    @Override
    public int updateDynamic(UserDynamic dynamic) {
        return userDynamicMapper.updateDynamic(dynamic);
    }

    @Override
    public List<ShowDynamic> getUserAllDynamic(Integer userId,Integer pageNum) {
        return userDynamicMapper.getUserAllDynamicById(userId,pageNum);
    }

    @Override
    public List<ShowDynamic> getAllByList(Integer pageNum) {
        return userDynamicMapper.getAllByList(pageNum);
    }

    @Override
    public int thumbDynamic(Integer dynamicId) {
        return userDynamicMapper.thumbDynamic(dynamicId);
    }

    @Override
    public int collectionDynamic(Integer dynamicId) {
        return userDynamicMapper.collectionDynamic(dynamicId);
    }

    @Override
    public int unThumbDynamic(Integer dynamicId) {
        return userDynamicMapper.unThumbDynamic(dynamicId);
    }

    @Override
    public int unCollectionDynamic(Integer dynamicId) {
        return userDynamicMapper.unCollectionDynamic(dynamicId);
    }

    @Override
    public int countById(Integer userId) {
        return userDynamicMapper.countDynamicById(userId);
    }

    @Override
    public ShowDynamic getDynamicToShow(Integer dynamicId) {
        return userDynamicMapper.getDynamicToShow(dynamicId);
    }

    @Override
    public List<ShowDynamic> getDynamicByThumb(Integer pageNum) {
        return userDynamicMapper.getDynamicByThumb(pageNum);
    }

    @Override
    public List<ShowDynamic> getMyFollowedDynamic(List<Follow> list,Integer pageNum) {
        return userDynamicMapper.getMyFollowedDynamic(list,pageNum);
    }

    @Override
    public List<ShowDynamic> getMyCollectedDynamic(Integer userId, Integer pageNum) {
        return userDynamicMapper.getMyCollectedDynamic(userId,pageNum);
    }

    @Override
    public int countMyFollowedDyN(List<Follow> list) {
        return userDynamicMapper.countMyFollowedDyNum(list);
    }

    @Override
    public int countMyCollectedDynamic(Integer userId) {
        return userDynamicMapper.countMyCollectedDynamic(userId);
    }
}

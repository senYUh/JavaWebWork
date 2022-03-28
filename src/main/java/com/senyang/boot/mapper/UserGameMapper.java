package com.senyang.boot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.senyang.boot.entity.UserGame;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGameMapper extends BaseMapper<UserGame> {
    List<UserGame> getAllGameToUser();
    List<UserGame> getAllGameToMan();
    int deleteGame(Integer gameId);
    int updateGame(UserGame game);
    int insertGame(UserGame game);
}

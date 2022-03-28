package com.senyang.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senyang.boot.entity.UserGame;
import com.senyang.boot.mapper.UserGameMapper;
import com.senyang.boot.service.UserGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserGameServiceImpl extends ServiceImpl<UserGameMapper,UserGame> implements UserGameService {

    @Autowired
    UserGameMapper userGameMapper;

    @Override
    public List<UserGame> getAllGameToPlay() {
        return userGameMapper.getAllGameToUser();
    }

    @Override
    public List<UserGame> getAllGameToMan() {
        return userGameMapper.getAllGameToMan();
    }

    @Override
    public int removeGame(Integer gameId) {
        return userGameMapper.deleteGame(gameId);
    }

    @Override
    public int addGame(UserGame game) {
        return userGameMapper.insertGame(game);
    }

    @Override
    public int updateGame(UserGame game) {
        return userGameMapper.updateGame(game);
    }
}

package com.senyang.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.senyang.boot.entity.UserGame;

import java.util.List;

public interface UserGameService extends IService<UserGame> {
    List<UserGame> getAllGameToPlay();
    List<UserGame> getAllGameToMan();
    int removeGame(Integer gameId);
    int addGame(UserGame game);
    int updateGame(UserGame game);
}

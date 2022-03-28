package com.senyang.boot.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_game")
public class UserGame {
    @TableId("game_id")
    private int gameId;
    private String gameName;
    private boolean gameState;
    private String gameUrl;
    private String gameImgUrl;
}

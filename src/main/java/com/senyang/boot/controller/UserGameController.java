package com.senyang.boot.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.entity.UserGame;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.UserGameService;
import com.senyang.boot.service.UserService;
import com.senyang.boot.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserGameController {

    @Autowired
    UserService userService;
    @Autowired
    UserGameService gameService;

    @PostMapping("/getAGTS")
    @ResponseBody
    public RespEntity getAllGameToUser(){
        List<UserGame> games = gameService.getAllGameToPlay();
        if(games != null){
            return new RespEntity(RespCode.SUCCESS,games);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/getAGTM")
    @ResponseBody
    public RespEntity getAllGameToMan(HttpServletRequest request){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        List<UserGame> games = gameService.getAllGameToMan();
        if(games != null){
            return new RespEntity(RespCode.SUCCESS,games);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/delGBM")
    @ResponseBody
    public RespEntity deltetGameByMan(HttpServletRequest request, @RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        Integer gameId = Integer.parseInt(info.get("gameId"));
        int res = gameService.removeGame(gameId);
        if(res > 0){
            return new RespEntity(RespCode.SUCCESS,null);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/examine")
    @ResponseBody
    public RespEntity updateGame(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        UserGame game = init(info);
        int res = gameService.updateGame(game);
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,game);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/addGame")
    @ResponseBody
    public RespEntity addGame(@RequestBody Map<String,String> info){
        UserGame game = init(info);
        int res = gameService.addGame(game);
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,game);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    static UserGame init(Map<String,String> info ){
        UserGame game = new UserGame();
        game.setGameName(info.get("gameName"));
        if(info.get("gameId")!=null){
            game.setGameId(Integer.parseInt(info.get("gameId")));
        }
        game.setGameImgUrl(info.get("gameImgUrl"));
        game.setGameUrl(info.get("gameUrl"));
        if(info.get("gameState") == null){
            game.setGameState(false);
        }else {
            game.setGameState(Boolean.parseBoolean(info.get("gameState")));
        }
        return game;
    }
}

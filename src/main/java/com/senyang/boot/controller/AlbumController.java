package com.senyang.boot.controller;


import com.senyang.boot.constant.MyConst;
import com.senyang.boot.entity.*;
import com.senyang.boot.myEnum.RespCode;
import com.senyang.boot.service.HomePageImgService;
import com.senyang.boot.service.UserAlbumImgService;
import com.senyang.boot.service.UserAlbumService;
import com.senyang.boot.service.UserService;
import com.senyang.boot.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class AlbumController {

    @Autowired
    UserAlbumService userAlbumService;
    @Autowired
    UserAlbumImgService userAlbumImgService;
    @Autowired
    UserService userService;
    @Autowired
    HomePageImgService homePageImgService;

    @PostMapping("/getAUM")
    @ResponseBody
    public RespEntity getAlbumByUserId(HttpServletRequest request){
        Integer userId = WebUtils.getUserId(request);
        List<UserAlbum> albums = userAlbumService.getAlbumByUserId(userId);
        List<Object> list= toList(albums,userAlbumImgService,userAlbumService,false);
        if(list != null){
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/getAllAum")
    @ResponseBody
    public RespEntity getAllAumToMan(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        int page = Integer.parseInt(info.get("pageNum"));
        List<UserAlbum> albums = userAlbumService.getAllAlbumToMan((page-1)*MyConst.MY_ALBUM_MANAGE_PAGE_NUM);
        List<Object> list = toList(albums,userAlbumImgService,userAlbumService,true);
        if(list != null){
            return new RespEntity(RespCode.SUCCESS,list);
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }



    @PostMapping("/addAUM")
    @ResponseBody
    public RespEntity addAlbum(HttpServletRequest request, @RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        UserAlbum album = new UserAlbum();
        album.setAlbumUserId(userId);
        album.setAlbumCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        album.setAlbumDescription(info.get("description"));
        album.setAlbumName(info.get("name"));
        album.setAlbumAuthority(Integer.parseInt(info.get("authority"))==1);
        int res = userAlbumService.addAlbum(album);
        if(res == 1){
            return new RespEntity(RespCode.SUCCESS,album);
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/delAUM")
    @ResponseBody
    public RespEntity deleteAlbum(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Integer albumId = Integer.parseInt(info.get("albumId"));
        boolean man = doToAlbum(userId,albumId,request,userService,userAlbumService);
        if(man){
            int res = userAlbumService.deleteAlbumByAlbumId(albumId);
            if(res == 1){
                return new RespEntity(RespCode.SUCCESS,null);
            }
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/updateAUM")
    @ResponseBody
    public RespEntity updateAUM(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Integer albumId = Integer.parseInt(info.get("albumId"));
        boolean man = doToAlbum(userId,albumId,request,userService,userAlbumService);
        if(man){
            UserAlbum album = userAlbumService.getById(albumId);
            if(album != null){
                album.setAlbumName(info.get("name"));
                album.setAlbumDescription(info.get("description"));
                album.setAlbumAuthority(Integer.parseInt(info.get("authority")) == 1);
                int res = userAlbumService.updateAlbum(album);
                if(res == 1){
                    return new RespEntity(RespCode.SUCCESS,album);
                }
            }
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/getAUAI")
    @ResponseBody
    public RespEntity getAllUserAlbumImg(HttpServletRequest request,@RequestBody Map<String,String> info){
        Integer albumId = Integer.valueOf(info.get("albumId"));
        UserAlbum album = userAlbumService.getById(albumId);
        boolean man;
        if(album == null){
            return new RespEntity(RespCode.GET_INFO_ERROR,null);
        }
       man = doToAlbumImg(album,request,userService);
        if(man){
            List<Object> list = new ArrayList<>();
            User user = userService.getById(album.getAlbumUserId());
            int count = userAlbumImgService.countImg(albumId);
            List<UserAlbumImg> imgs = userAlbumImgService.getAllByAlbumId(albumId);
            if(imgs != null){
                list.add(count);
                list.add(user.getUserName());
                list.add(album);
                list.add(imgs);
                return new RespEntity(RespCode.SUCCESS,list);
            }
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("addUAI")
    @ResponseBody
    public RespEntity addUserAlbumImg(HttpServletRequest request,@RequestBody Map<String,Object> info){
        boolean man;
        int albumId = (Integer) info.get("albumId");
        UserAlbum album = userAlbumService.getById(albumId);
        man = doToAlbumImg(album,request,userService);
        if(man){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<String> imgUrls = (List<String>) info.get("photos");
            UserAlbumImg img = new UserAlbumImg();
            img.setUserId(WebUtils.getUserId(request));
            img.setAlbumId(albumId);
            boolean flag = true;
            for (String imgUrl : imgUrls) {
                img.setImgUrl(imgUrl);
                img.setImgCreateDate(simpleDateFormat.format(new Date()));
                int res = userAlbumImgService.addUserImg(img);
                if(res == 0){
                    flag = false;
                }
            }
            if(flag){
                return new RespEntity(RespCode.SUCCESS,null);
            }
        }
        return new RespEntity(RespCode.REGISTER_ERROR,null);
    }

    @PostMapping("/getAIBM")
    @ResponseBody
    public RespEntity getAllUserAlbumImgByMan(HttpServletRequest request,@RequestBody Map<String,String> info){
        if(WebUtils.isNotManger(request,userService)){
            return new RespEntity(RespCode.FAIL,null);
        }
        int pageNum = Integer.parseInt(info.get("pageNum"));
        List<Integer> albumIds = userAlbumService.getAllOutId();
        if(albumIds != null){
            List<UserAlbumImgShow> imgShows = userAlbumImgService.getAllImg(albumIds,
                    (pageNum-1)* MyConst.MY_PAGE_NUM_OF_USER);
            if(imgShows != null){
                for(UserAlbumImgShow show:imgShows){
                    show.setSelected(homePageImgService.judgeSelect(show.getImgUrl()));
                }
                return new RespEntity(RespCode.SUCCESS,imgShows);
            }
        }
        return new RespEntity(RespCode.GET_INFO_ERROR,null);
    }

    @PostMapping("/delIMG")
    @ResponseBody
    public RespEntity deleteUserAlbumImg(HttpServletRequest request , @RequestBody Map<String,String> info){
        Integer userId = WebUtils.getUserId(request);
        Integer imgId = Integer.valueOf(info.get("imgId"));
        UserAlbumImg img = userAlbumImgService.getById(imgId);
        UserAlbum album;
        if(img == null){
            return new RespEntity(RespCode.GET_INFO_ERROR,null);
        }
        album = userAlbumService.getById(img.getAlbumId());
        boolean man = false;
        if((!WebUtils.isNotManger(request,userService))
                || (img.getUserId() == userId)
                || (album.getAlbumUserId() == userId)){
            man = true;
        }
        if(man){
            int res = userAlbumImgService.deleteImg(imgId);
            if(res == 1){
                return new RespEntity(RespCode.SUCCESS,null);
            }
        }
        return new RespEntity(RespCode.FAIL);
    }

    static List<Object> toList(List<UserAlbum> userAlbums,UserAlbumImgService service,
                               UserAlbumService userAlbumService,Boolean requireCount){
        List<Object> list = new ArrayList<>();
        if(requireCount){
            int count = userAlbumService.count();
            list.add(count);
        }
        if(userAlbums == null){
            return null;
        }
        for(UserAlbum userAlbum:userAlbums){
            List<Object> list1 = new ArrayList<>();
            int count = service.countImg(userAlbum.getAlbumId());
            UserAlbumImg img = service.getEarly(userAlbum.getAlbumId());
            if(img == null){
                img = new UserAlbumImg();
                img.setImgUrl("http://wsjwqq.top:2280/img/default_user_img_head.png");
            }
            list1.add(count);
            list1.add(userAlbum);
            list1.add(img);
            list.add(list1);
        }
        return list;
    }

    static boolean doToAlbum(Integer userId,Integer albumId,HttpServletRequest request,UserService userService,UserAlbumService userAlbumService){
        boolean man = false;
        if(!WebUtils.isNotManger(request,userService)){
            man = true;
        }else {
            UserAlbum album = userAlbumService.getById(albumId);
            if(album.getAlbumUserId() == userId){
                man = true;
            }
        }
        return man;
    }

    static boolean doToAlbumImg(UserAlbum album,HttpServletRequest request,UserService userService){
        boolean man = false;
        if(!WebUtils.isNotManger(request,userService)){
            man = true;
        }else {
            if(!album.getAlbumAuthority() || album.getAlbumUserId() == WebUtils.getUserId(request)){
                man = true;
            }
        }
        return man;
    }
}

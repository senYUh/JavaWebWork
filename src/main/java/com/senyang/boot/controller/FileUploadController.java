package com.senyang.boot.controller;

import com.senyang.boot.utils.StringUtils;
import com.senyang.boot.entity.RespEntity;
import com.senyang.boot.myEnum.RespCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Controller
public class FileUploadController {
    @PostMapping("/upLoadImg")
    @ResponseBody
    public RespEntity imgUpload(@RequestPart("file") MultipartFile headerImg) throws IOException {
        String url = "";
        int random = new Random().nextInt(1000000);
        if(!headerImg.isEmpty()){
            String imgName ="img" + random + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) +
                    StringUtils.getSuffix(Objects.requireNonNull(headerImg.getOriginalFilename()));
            url = "img/" + imgName;
            headerImg.transferTo(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\img\\"
                    +imgName));
        }
        if(!"".equals(url)){
            return new RespEntity(RespCode.SUCCESS,url);
        }else {
            return new RespEntity(RespCode.HEADER_IMG_ERROR,null);
        }
    }

    @PostMapping("/upAlbumImg")
    @ResponseBody
    public RespEntity fileUpLoad(@RequestPart("file") MultipartFile file) throws IOException {
        String url = "";
        int random = new Random().nextInt(158664);
        if(!file.isEmpty()){
            String fileName = "file"+random+new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                    + StringUtils.getSuffix(Objects.requireNonNull(file.getOriginalFilename()));
            url = "file/userImg/"+fileName;
            file.transferTo(new File(System.getProperty("user.dir")+
                    "\\src\\main\\resources\\static\\file\\userImg\\"
            + fileName));
        }
        if(!"".equals(url)){
            return new RespEntity(RespCode.SUCCESS,url);
        }else {
            return new RespEntity(RespCode.FILE_UPLOAD_ERROR,null);
        }
    }
}

package com.senyang.boot.utils;

import com.senyang.boot.entity.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

@Component
public class MyTimer extends TimerTask {
    @Autowired
    MyLog log;

    @Override
    public void run() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        String text = sdf2.format(new Date())+".txt";
        boolean res = false;
        File file = new File("F:\\IDEA\\log\\"+text);
        if(!file.exists()){
            try {
                while (!res){
                    res = file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWritter = new FileWriter(file, false);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(log.getLog());
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sdf.format(new Date())+":日志记录成功");
    }
}

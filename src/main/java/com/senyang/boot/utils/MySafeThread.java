package com.senyang.boot.utils;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class MySafeThread extends Thread{
    @SneakyThrows
    @Override
    public void run() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
        Date first=sdf.parse("2021年-12月-28日 00:00:00");
        Timer timer=new Timer();
        timer.schedule(new MyTimer(),first,1000*60*60*24);//一天执行一次目标任务，从first开始
    }
}

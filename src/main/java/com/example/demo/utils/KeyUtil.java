package com.example.demo.utils;

import java.util.Random;

public class KeyUtil
{
    public static synchronized String genKey()
    {
        Random random=new Random();
        //整数转成
        return System.currentTimeMillis()+String.valueOf(random.nextInt(900000)+100000);
    }
}

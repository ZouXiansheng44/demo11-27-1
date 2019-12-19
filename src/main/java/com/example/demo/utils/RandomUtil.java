package com.example.demo.utils;

import java.util.Random;

public class RandomUtil {

    public static synchronized String genKey()
    {
        Random random=new Random();
        int arr[] = new int[10];
        for (int i = 0; i < arr.length; i++)
        {
            int index = (int)(Math.random() * 20 + 1);
            arr[i] = index;
            for (int j = 0; j < i; j++)
            {
                if (arr[j] == arr[i])
                {
                    i--;
                    break;
                }
            }
        }

        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }

        //整数转成
        return System.currentTimeMillis()+String.valueOf(random.nextInt(900000)+100000);
    }
}

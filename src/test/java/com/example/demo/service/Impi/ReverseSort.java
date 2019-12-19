package com.example.demo.service.Impi;

import com.example.demo.dataobject.UserInfo;

public class ReverseSort {
//调用方式
 //   UserInfo userInfo=new UserInfo();
    public static void main(String[] args) {
        int [] array={10,20,30,40};
        ReverseSort sorter=new ReverseSort();
      //  sorter.sort(userInfo.getUserId());
    }


//排序方法
    public void sort(int[] array)
    {
        System.out.println("数组原有内容");
        ShowArray(array);
        int temp;
        int len=array.length;
        for (int i=0;i<len/2;i++)
        {
            temp=array[i];
            array[i]=array[len-1-i];
            array[len-1-i]=temp;
        }
       // return array;
        System.out.println("数组反转内容");
        ShowArray(array);
    }

    //输出方法
    public void ShowArray(int [] array){
        for (int i:array){
            System.out.print("\t"+i);
        }
        System.out.println();//换行
    }
}

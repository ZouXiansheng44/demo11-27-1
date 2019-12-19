package com.example.demo.service.Impi;

import com.example.demo.dataobject.UserInfo;
//import org.hibernate.annotations.Sort;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserInfoServiceImpiTest {
    @Autowired
    private UserInfoServiceImpi userInfoServiceImpi;
    @Test
    public void findOne()
    {
        UserInfo userInfo=userInfoServiceImpi.findByUserId(1);
        System.out.println(userInfo);
    }

    @Test
    public void save()
    {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("sssss222");
        userInfo.setUserPassword("5201314");
        userInfo.setUserPhone("33333");
        userInfo.setUserEmail("frddddd");
        UserInfo result=userInfoServiceImpi.save(userInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void save2(){
        int arr[] = new int[10];//10个数的数组
        for (int i = 0; i < arr.length; i++) {
            //生产一个1-20的随机数
            int index = (int)(Math.random() * 20 + 1);
            arr[i] = index;//把随机数赋值给下标为数组下标为i的值
            //（遍历数组中储存进去的值，i中有几个值则循环几次）
            for (int j = 0; j < i; j++)
            {
                //把储存在数组中的值j 和 随机出的值i 做比较
                if (arr[j] == arr[i])
                {
                    i--; //数组的值下标-1，i的循环次数回到上次
                    break;
                }
            }
        }
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }
//    @Test
//    void random(){
//        finder = new Finder("")
//                .append(" FROM Event21 ORDER BY RAND() ");
//        int size = find(finder).size();
//        Random r = new Random();
//        finder.setMaxResults(100);
//        finder.setFirstResult(r.nextInt(size-100)+1);
//        return find(finder);
//
//    }
    @Test
    void random2()
    {
        String[] str = { "1",  "2", "3", "4", "5", "6","7" ,"8"};
        for (int i = 0; i < 5; i++)
        {
            int random = (int) ( Math.random () * 5);
            for (int j = 0; j < i; j++)
            {
                //把储存在数组中的值j 和 随机出的值i 做比较
                if (str[j] == str[random])
                {
                    i--; //数组的值下标-1，i的循环次数回到上次
                    break;
                }
            }
            System.out.println (str[random]);
        }
    }

}
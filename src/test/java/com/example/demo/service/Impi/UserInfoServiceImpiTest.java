package com.example.demo.service.Impi;

import com.example.demo.dataobject.UserInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
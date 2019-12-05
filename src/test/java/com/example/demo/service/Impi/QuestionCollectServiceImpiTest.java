package com.example.demo.service.Impi;

import com.example.demo.dataobject.QuestionCollect;
import com.example.demo.dataobject.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionCollectServiceImpiTest {
    @Autowired
    private QuestionCollectServiceImpi questionCollectServiceImpi;
    @Test
    public void findOne()
    {
        QuestionCollect questionCollect=questionCollectServiceImpi.findByCollectId(1);
        System.out.println(questionCollect);
    }
}
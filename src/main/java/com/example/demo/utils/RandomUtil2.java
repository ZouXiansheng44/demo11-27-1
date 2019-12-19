package com.example.demo.utils;

import com.example.demo.dataobject.QuestionBank;

import java.util.Random;

public class RandomUtil2 {
    QuestionBank questionBank=new QuestionBank();
    Random random=new Random(questionBank.getQuestionId());
   // int randomNumber =  random.nextInt(max)%(max-min+1) + min;

}

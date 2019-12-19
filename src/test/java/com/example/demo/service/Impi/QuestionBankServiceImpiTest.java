package com.example.demo.service.Impi;

import com.example.demo.dataobject.QuestionBank;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionBankServiceImpiTest {
    @Autowired
    private QuestionBankServiceImpi questionBankServiceImpi;

    @Test
    void findAll() {
//        String s ="a,s,d,f";
//        System.out.println(s);
//        char[] chars = s.toCharArray();
//        System.out.println("/////////////////");
//        System.out.println(chars);
//        String[] splitOptionw=chars.split(",");
//       System.out.println(splitOptionw);
        List<QuestionBank> questionBank=questionBankServiceImpi.findAll();
        //第一重循环用于将questionBank这个list中的所有值一组一组遍历出来
        //    QuestionBank questionBank2=new QuestionBank();
      //  String sss=null;
        for(QuestionBank questionBank1:questionBank)
        {
            //将选项这个字段按逗号“，”为间隔，一个个存入字符串数组
            String[] splitOption=questionBank1.getQuestionOption().split(",");
            //将每组数据存到string里
       //     String string=(questionBank1+" ");
           // System.out.println(string);
            System.out.println(questionBank1);
        //    String sss=null;//用来接第二重循环里的ss变量
            for (String ss :splitOption)
            {
             //  System.out.println(ss);
            //   System.out.println("///////////////////");
             //   sss=ss;
                System.out.println("text:"+ss);
            }

            System.out.println("*************************************************************");
           // String string=(questionBank1+","+"text:"+splitOption.toString());
           // questionBank2=questionBank1;
        }
        //如果先在循环定义一个空变量a去衔接循环内的变量b，那么在循环外a最后只能把b的最后一组遍历出来
    //    System.out.println(questionBank2);
    }

    @Test
    void findAll1() {
        List<QuestionBank> questionBank=questionBankServiceImpi.findAll();
        Random random=new Random();

        List<String> strings=new ArrayList<>();
        for (QuestionBank questionBank1:questionBank) {
            questionBank1.getQuestionId();
            System.out.println(questionBank1+"///////////");
        }
//        System.out.println(questionBank+"**************");
    }

    @Test
    void findByQuestionId() {

    }

    @Test
    void findAll2() {
        //数据表对象，可直接实现findAll方法
        List<QuestionBank> questionBank=questionBankServiceImpi.findAll();
        //字符串数组，用于接下来的字符串切割
        String[] splitOption=null;
        //双重LIst（动态数组），可用于将对象与数组一起添加
        List<List> b=new ArrayList<>();
        //第一重循环，用于将题库表对象的每一组数组单独遍历取出
        for(QuestionBank questionBank1:questionBank)
        {
            //单重List<String>（动态数组【字符串型】），用于将for循环内的元素变量取出
            List<String> a=new ArrayList<>();
            //字符串数组里是split方法可用于切割字符串并转为字符串数组，这里面的分割间隔是逗号“，”
            splitOption=questionBank1.getQuestionOption().split(",");
            System.out.println(questionBank1);
            //List<Object> （动态数组【对象型】），用于将题库表对象添加进去
            List<Object> c=new ArrayList<>();
            //其实如果只是单独的ListList<QuestionBank> questionBank，这里面的questionBank是取不到.getQuestionContent()
            //但是QuestionBank questionBank1，此时questionBank1是可以取到get跟set方法的
            //也就是说整体的List<QuestionBank>取不到具体的，但单独get跟set方法的QuestionBank是可以取到get跟set方法的
            questionBank1.getQuestionContent();
            c.add(questionBank1);
            //splitOption是遍历对象，ss是元素变量
            for (String ss :splitOption)
            {
                //这个就是List<String>（动态数组）特有的添加方法
                a.add(ss);
            System.out.println(a);
            System.out.println("*************************************************************");
            b.add(c);
            b.add(a);
        }
        System.out.println(b);
    }
    }
    @Test
    void Random1(){
        List<QuestionBank> questionBankList=questionBankServiceImpi.findAll();
        for (QuestionBank questionBank: questionBankList)
        {
            Random random=new Random(questionBank.getQuestionId());

        }

        }
        QuestionBank questionBank=new QuestionBank();


}
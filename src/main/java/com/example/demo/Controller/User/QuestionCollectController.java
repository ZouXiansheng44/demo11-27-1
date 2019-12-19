package com.example.demo.Controller.User;

import com.example.demo.dataobject.QuestionBank;
import com.example.demo.dataobject.QuestionCollect;
import com.example.demo.dataobject.UserInfo;
import com.example.demo.exception.QuestionException;
import com.example.demo.service.Impi.QuestionBankServiceImpi;
import com.example.demo.service.Impi.QuestionCollectServiceImpi;
import com.example.demo.service.Impi.UserInfoServiceImpi;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import jdk.nashorn.internal.objects.annotations.Property;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/question/collect")
@Slf4j
@Api(description = "收藏")
public class QuestionCollectController {
    //每一个ServiceImpi都要加上@Autowired自动装配
    @Autowired
    private QuestionBankServiceImpi questionBankServiceImpi;
    @Autowired
    private QuestionCollectServiceImpi questionCollectServiceImpi;
    @Autowired
    private UserInfoServiceImpi userInfoServiceImpi;

    @GetMapping("/list")
    public Msg list(){
       List<QuestionCollect> questionCollect=questionCollectServiceImpi.findAll();
        return Msg.success().add("questionCollect",questionCollect);
    }

    @GetMapping("/findCollectByQuestionId")
    //根据收藏Id查找题库中对应的题目以及对应收藏这个题目的用户
    public Msg findCollectByQuestionId(@RequestParam("collectId") Integer collectId, Map<String, Object> map){
        QuestionCollect questionCollect=questionCollectServiceImpi.findByCollectId(collectId);
        QuestionBank questionBank=questionBankServiceImpi.findByQuestionId(questionCollect.getQuestionId());
        UserInfo userInfo=userInfoServiceImpi.findByUserId(questionCollect.getQuestionId());
    //    return Msg.success().add("questionBank",questionBank).add("questionCollect",questionCollect);
        map.put("questionCollect",questionCollect); //这个map不一定需要
        return Msg.success().add("questionBank",questionBank).add("userInfo",userInfo).add("questionCollect",questionCollect);
       // return Msg.success().add("questionCollect",questionCollect);
    }

    @PostMapping("/save")
    //Map<String,Object>中string是它的键，存储的类型为String
    //object是它的值，object为所有数据类型的父类，就是说可以存储任何类型的数据，调用时，可以进行转型
    //save 方法名
    //定义一个map，通过put进行赋值，最后再return map返回
    public Msg save(@RequestParam("userId") Integer userId,
                    @RequestParam("questionId") Integer questionId)
    {
        QuestionCollect questionCollect=new QuestionCollect();
        questionCollect.setUserId(userId);
        questionCollect.setQuestionId(questionId);
        //此判断是为了禁止重复收藏
        QuestionCollect questionCollect2=questionCollectServiceImpi.findByQuestionIdAndUserIdAndCollectStatus( questionId,userId,0);
        if (questionCollect2==null){
            questionCollectServiceImpi.save(questionCollect);
            return Msg.success();
        }
        return Msg.collect();
    }

    @GetMapping("/list2")
    public Msg list2(@RequestParam("userId") Integer userId){
        List<QuestionCollect> questionCollectList=questionCollectServiceImpi.findByUserId(userId);
        List<QuestionCollect> questionCollectList2=questionCollectServiceImpi.findByCollectStatus(0);
        return Msg.success().add("questionCollectList",questionCollectList).add("questionCollectList2",questionCollectList2);
    }

   // @RequestMapping("/OnCollect")
    @GetMapping("/OnCollect")
    public Msg OnCollect(@RequestParam("questionId") Integer questionId,
                         @RequestParam("userId") Integer userId){
        QuestionCollect questionCollect=questionCollectServiceImpi.findByQuestionIdAndUserId(questionId,userId);
        questionCollectServiceImpi.OnCollect(questionCollect.getQuestionId());
        return Msg.success();
    }

  //  @RequestMapping("/OffCollect")
    @GetMapping("/OffCollect")
    public Msg OffCollect(@RequestParam("questionId") Integer questionId,
                          @RequestParam("userId") Integer userId){
        QuestionCollect questionCollect=questionCollectServiceImpi.findByQuestionIdAndUserId(questionId,userId);
        questionCollectServiceImpi.OffCollect(questionCollect.getQuestionId());
        return Msg.success();
    }

    @DeleteMapping("/delete2")
    public Msg delete2(@RequestParam("collectId") Integer collectId){
        questionCollectServiceImpi.deleteByCollectId(collectId);
        return Msg.success();
    }

}

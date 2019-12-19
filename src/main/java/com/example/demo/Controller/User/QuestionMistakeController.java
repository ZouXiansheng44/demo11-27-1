package com.example.demo.Controller.User;


import com.example.demo.dataobject.QuestionCollect;
import com.example.demo.dataobject.QuestionMistake;
import com.example.demo.service.Impi.QuestionMistakeServiceImpi;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/question/mistake")
@Slf4j
@Api(description = "错题")
public class QuestionMistakeController {
    @Autowired
    private QuestionMistakeServiceImpi questionMistakeServiceImpi;
    @PostMapping("/save")
    public Msg save(@RequestParam("userId") Integer userId,
                    @RequestParam("questionId") Integer questionId)
    {
        QuestionMistake questionMistake=new QuestionMistake();
        questionMistake.setUserId(userId);
        questionMistake.setQuestionId(questionId);
        QuestionMistake questionMistake2=questionMistakeServiceImpi.findByQuestionIdAndUserId(questionId,userId);
        //判断数据库里有没有这个用户要收藏的错题，如果没有则正常执行接下来的Sava方法，否则返回“已收藏”
        if(questionMistake2==null){
            questionMistakeServiceImpi.save(questionMistake);
            return Msg.success();
        }else {
            return Msg.collect();
        }
    }

    @GetMapping("/OnCollect")
    public Msg OnCollect(@RequestParam("questionId") Integer questionId,
                         @RequestParam("userId") Integer userId){
        QuestionMistake questionMistake=questionMistakeServiceImpi.findByQuestionIdAndUserId(questionId,userId);
        questionMistakeServiceImpi.OnMistake(questionMistake.getQuestionId(),questionMistake.getUserId());
        return Msg.success();
    }

    @GetMapping("/OffCollect")
    public Msg OffCollect(@RequestParam("questionId") Integer questionId,
                          @RequestParam("userId") Integer userId){
        QuestionMistake questionMistake=questionMistakeServiceImpi.findByQuestionIdAndUserId(questionId,userId);
        questionMistakeServiceImpi.OffMistake(questionMistake.getQuestionId(),questionMistake.getUserId());
        return Msg.success();
    }

    @GetMapping("/list")
    public Msg list(){
       List<QuestionMistake> questionMistakeList=questionMistakeServiceImpi.findByMistakeStatus(0);
       return Msg.success().add("questionMistakeList",questionMistakeList);
    }
}

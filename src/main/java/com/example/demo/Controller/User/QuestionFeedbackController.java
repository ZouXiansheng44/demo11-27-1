package com.example.demo.Controller.User;


import com.example.demo.dataobject.QuestionFeedback;
import com.example.demo.service.Impi.QuestionFeedbackServiceImpi;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/question/feedback")
@Slf4j
@Api(description = "评论")
public class QuestionFeedbackController {

    @Autowired
    private QuestionFeedbackServiceImpi questionFeedbackServiceImpi;
    @PostMapping("/save")
    public Msg save(@RequestParam("userId") Integer userId,
                    @RequestParam("questionId") Integer questionId,
                    @RequestParam("feedbackContent") String feedbackContent){
        QuestionFeedback questionFeedback= new QuestionFeedback();
        questionFeedback.setUserId(userId);
        questionFeedback.setQuestionId(questionId);
        questionFeedback.setFeedbackContent(feedbackContent);
        QuestionFeedback questionFeedback2=questionFeedbackServiceImpi.findByUserIdAndQuestionId(userId,questionId);
        if(questionFeedback2==null){
            questionFeedbackServiceImpi.save(questionFeedback);
            return Msg.success();
        }else {
            return Msg.comment();
        }

    }

    @PostMapping("/update")
    public Msg update(@RequestParam("userId") Integer userId,
                    @RequestParam("questionId") Integer questionId,
                    @RequestParam("feedbackContent") String feedbackContent){
        QuestionFeedback questionFeedback= new QuestionFeedback();
        questionFeedback.setUserId(userId);
        questionFeedback.setQuestionId(questionId);
        questionFeedback.setFeedbackContent(feedbackContent);
        QuestionFeedback questionFeedback2=questionFeedbackServiceImpi.findByUserIdAndQuestionId(userId,questionId);
        if(questionFeedback2==null){
            questionFeedbackServiceImpi.save(questionFeedback);
            return Msg.success();
        }else {
            questionFeedback=questionFeedbackServiceImpi.deleteByUserIdAndQuestionId(userId,questionId);
            questionFeedbackServiceImpi.save(questionFeedback);
            return Msg.comment();
        }
    }

    @PostMapping("/delete")
    public Msg delete(@RequestParam("questionId") Integer questionId,
                      @RequestParam("userId") Integer userId){
        questionFeedbackServiceImpi.deleteByUserIdAndQuestionId(userId,questionId);
        return Msg.success();
    }

    @PostMapping("/update1")
    public Msg update1(@RequestParam("questionId") Integer questionId,
                       @RequestParam("userId") Integer userId,
                       @RequestParam("feedbackContent" )String feedbackContent){
        questionFeedbackServiceImpi.update1(questionId,userId);
        return Msg.success();
    }

    @GetMapping("/list1")
    public Msg list1(@RequestParam ("questionId") Integer questionId){
        List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionId);
        return Msg.success().add("questionFeedbackList",questionFeedbackList);
    }

}

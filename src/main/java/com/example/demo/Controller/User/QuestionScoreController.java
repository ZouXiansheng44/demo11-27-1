package com.example.demo.Controller.User;


import com.example.demo.dataobject.QuestionBank;
import com.example.demo.dataobject.QuestionScore;
import com.example.demo.service.Impi.QuestionBankServiceImpi;
import com.example.demo.service.Impi.QuestionScoreServiceImpi;
import com.example.demo.utils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/question/score")
@Slf4j

public class QuestionScoreController {
    @Autowired
    private QuestionScoreServiceImpi questionScoreServiceImpi;
    @Autowired
    private QuestionBankServiceImpi questionBankServiceImpi;
    @GetMapping("/type")
    public Msg score(@RequestParam("questionType") String questionType)

    {
        QuestionScore questionScore=new QuestionScore();
        List<Object> questionBankList1=new ArrayList<>();
        List<QuestionScore> questionScoreList=questionScoreServiceImpi.findByQuestionType(questionType);
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionType(questionScore.getQuestionType());
        for (QuestionBank questionBank:questionBankList){
            questionBankList1.add(questionBank);
        }
        return Msg.success().add("questionScoreList",questionScoreList).add("questionBankList1",questionBankList1);
    }

}

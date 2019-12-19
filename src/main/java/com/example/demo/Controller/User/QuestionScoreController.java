package com.example.demo.Controller.User;


import com.example.demo.dataobject.QuestionBank;
import com.example.demo.dataobject.QuestionScore;
import com.example.demo.service.Impi.QuestionBankServiceImpi;
import com.example.demo.service.Impi.QuestionScoreServiceImpi;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "题目分数")
public class QuestionScoreController {
    @Autowired
    private QuestionScoreServiceImpi questionScoreServiceImpi;
    @Autowired
    private QuestionBankServiceImpi questionBankServiceImpi;
    @GetMapping("/type")
    @ApiOperation(value = "",notes = "根据题目类型显示对应的题目列表")
    public Msg score(@RequestParam("questionType") String questionType)

    {
       // List<Object> questionBankList1=new ArrayList<>();
        //由于题目类型对应的分数是一种类型，对应一个分数，这样设计的好处可以减少数据冗余
        //因此不用list，只用单个对象即可
        QuestionScore questionScore=questionScoreServiceImpi.findByQuestionType(questionType);
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionType(questionScore.getQuestionType());
//        for (QuestionBank questionBank:questionBankList){
//            questionBankList1.add(questionBank);
//        }
        return Msg.success().add("questionScore",questionScore).add("questionBankList",questionBankList);
    }

}

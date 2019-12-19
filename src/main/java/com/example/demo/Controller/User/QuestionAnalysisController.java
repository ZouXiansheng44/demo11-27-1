package com.example.demo.Controller.User;


import com.example.demo.dataobject.AnswerAnalysis;
import com.example.demo.service.Impi.QuestionAnalysisServiceImpi;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/question/analysis")
@Slf4j
@Api(description = "题目分析")
public class QuestionAnalysisController
{

    @Autowired
    private QuestionAnalysisServiceImpi questionAnalysisServiceImpi;
    @GetMapping("/list3")//需要
    public Msg list3()
    {
          List<AnswerAnalysis> answerAnalysisList =questionAnalysisServiceImpi.findAll();
          return Msg.success().add("answerAnalysisList", answerAnalysisList);
    }

}

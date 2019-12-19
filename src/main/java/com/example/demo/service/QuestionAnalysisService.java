package com.example.demo.service;

import com.example.demo.dataobject.AnswerAnalysis;

import java.util.List;

public interface QuestionAnalysisService {
   List<AnswerAnalysis> findAll();
   public AnswerAnalysis findByQuestionId(Integer questionId );
}

package com.example.demo.service;

import com.example.demo.dataobject.QuestionScore;

import java.util.List;

public interface QuestionScoreService {
   public List<QuestionScore> findByQuestionType(String questionType);
}

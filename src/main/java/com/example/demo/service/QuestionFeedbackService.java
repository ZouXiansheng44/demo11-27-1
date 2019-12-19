package com.example.demo.service;

import com.example.demo.dataobject.QuestionFeedback;

import java.util.List;

public interface QuestionFeedbackService {

    public QuestionFeedback save(QuestionFeedback questionFeedback);
    public List<QuestionFeedback> findByQuestionId(Integer questionId);
    public QuestionFeedback findByUserIdAndQuestionId(Integer userId,Integer questionId);
    public QuestionFeedback deleteByUserIdAndQuestionId(Integer userId,Integer questionId);
    public QuestionFeedback update1(Integer questionId,Integer userId);
}

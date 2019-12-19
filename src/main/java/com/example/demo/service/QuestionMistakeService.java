package com.example.demo.service;

import com.example.demo.dataobject.QuestionMistake;

import java.util.List;

public interface QuestionMistakeService {
    public QuestionMistake save(QuestionMistake questionMistake);
    public List<QuestionMistake> findByUserId (Integer userId);

    public QuestionMistake OnMistake(Integer questionId,Integer userId);
    public QuestionMistake OffMistake(Integer questionId,Integer userId);

    public QuestionMistake findByUserIdOne(Integer userId);
    public List<QuestionMistake> findByMistakeStatus(Integer mistakeStatus);
    public QuestionMistake findByQuestionIdAndUserId(Integer questionId,Integer userId);
    List<QuestionMistake> findByMistakeStatusAndUserId(Integer mistakeStatus,Integer userId);
    public QuestionMistake deleteByQuestionId(Integer questionId);
    public QuestionMistake findByUserIdAndQuestionId (Integer userId,Integer questionId);
}

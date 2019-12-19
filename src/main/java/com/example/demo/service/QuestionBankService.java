package com.example.demo.service;

import com.example.demo.dataobject.QuestionBank;

import java.util.List;

public interface QuestionBankService {

    List<QuestionBank> findAll();
    public QuestionBank findByQuestionId(Integer questionId);
    QuestionBank save(QuestionBank questionBank);
    List<QuestionBank> findByQuestionSubject(String questionSubject);
    public List<QuestionBank> findByQuestionType(String questionType);
    public List<QuestionBank> findByQuestionId2(Integer questionId);
}

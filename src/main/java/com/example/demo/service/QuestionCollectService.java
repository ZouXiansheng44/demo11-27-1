package com.example.demo.service;

import com.example.demo.dataobject.QuestionCollect;

import java.util.List;

public interface QuestionCollectService {
    public QuestionCollect findByCollectId(Integer collectId);
    public List<QuestionCollect> findAll();
    public QuestionCollect save(QuestionCollect questionCollect);
    //
    public List<QuestionCollect> findByUserId(Integer userId);
    public List<QuestionCollect> findByCollectStatus(Integer collectStatus);
    public List<QuestionCollect> findByCollectStatusAndUserId(Integer collectStatus,Integer userId);
    public QuestionCollect findByQuestionIdAndUserIdAndCollectStatus(Integer questionId,Integer userId,Integer collectStatus);
    public QuestionCollect deleteByCollectId(Integer collectId);
    //收藏
    QuestionCollect OnCollect(Integer userId);
    //不收藏
    QuestionCollect OffCollect(Integer userId);
    public QuestionCollect findByQuestionIdAndUserId(Integer questionId,Integer userId);
}

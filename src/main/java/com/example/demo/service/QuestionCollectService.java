package com.example.demo.service;

import com.example.demo.dataobject.QuestionCollect;

import java.util.List;

public interface QuestionCollectService {
    public QuestionCollect findByCollectId(Integer collectId);
    public List<QuestionCollect> findAll();
    public QuestionCollect save(QuestionCollect questionCollect);
}

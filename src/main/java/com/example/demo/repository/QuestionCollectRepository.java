package com.example.demo.repository;

import com.example.demo.dataobject.QuestionCollect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionCollectRepository extends JpaRepository<QuestionCollect,Integer>
{

    public QuestionCollect findByCollectId(Integer collectId);
    public List<QuestionCollect> findByUserId(Integer userId);
    public List<QuestionCollect> findByCollectStatus(Integer collectStatus);
    public QuestionCollect findByQuestionIdAndUserId(Integer questionId,Integer userId);
    public List<QuestionCollect> findByCollectStatusAndUserId(Integer collectStatus,Integer userId);
    public QuestionCollect findByQuestionIdAndUserIdAndCollectStatus(Integer questionId,Integer userId,Integer collectStatus);
    public QuestionCollect deleteByCollectId(Integer collectId);
 }

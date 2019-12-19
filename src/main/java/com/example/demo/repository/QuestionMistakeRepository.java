package com.example.demo.repository;

import com.example.demo.dataobject.QuestionMistake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionMistakeRepository extends JpaRepository<QuestionMistake,Integer> {
    public List<QuestionMistake> findByUserId (Integer userId);
    public List<QuestionMistake> findByMistakeStatus(Integer mistakeStatus);
    public QuestionMistake findByQuestionIdAndUserId(Integer questionId,Integer userId);
    List<QuestionMistake> findByMistakeStatusAndUserId(Integer mistakeStatus,Integer userId);
    public QuestionMistake deleteByQuestionId(Integer questionId);
    public QuestionMistake findByQuestionId(Integer questionId);
}

package com.example.demo.repository;

import com.example.demo.dataobject.QuestionMistake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionMistakeRepository2 extends JpaRepository<QuestionMistake,Integer> {

    public QuestionMistake findByMistakeStatus(Integer mistakeStatus);
    public QuestionMistake findByUserId(Integer userId);
    public QuestionMistake findByUserIdAndQuestionId (Integer userId,Integer questionId);

}

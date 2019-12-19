package com.example.demo.repository;

import com.example.demo.dataobject.QuestionScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionScoreRepository extends JpaRepository<QuestionScore,Integer > {

    public QuestionScore findByQuestionType(String questionType);
}

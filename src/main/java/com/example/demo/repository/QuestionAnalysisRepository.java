package com.example.demo.repository;

import com.example.demo.dataobject.AnswerAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnalysisRepository extends JpaRepository<AnswerAnalysis,Integer> {
    public AnswerAnalysis findByQuestionId(Integer questionId );
}

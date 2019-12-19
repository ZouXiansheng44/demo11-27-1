package com.example.demo.repository;

import com.example.demo.dataobject.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBankRepository2 extends JpaRepository<QuestionBank,Integer> {

    // public List<QuestionBank> findByQuestionId(Integer questionId);

    public List<QuestionBank> findByQuestionId(Integer questionId);
    public List<QuestionBank> findByQuestionSubject(String questionSubject);
    public List<QuestionBank>  findByQuestionType(String questionType);
}

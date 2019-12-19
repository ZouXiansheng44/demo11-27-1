package com.example.demo.service.Impi;

import com.example.demo.dataobject.AnswerAnalysis;
import com.example.demo.repository.QuestionAnalysisRepository;
import com.example.demo.service.QuestionAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnalysisServiceImpi implements QuestionAnalysisService {
    @Autowired
    QuestionAnalysisRepository questionAnalysisRepository;

    @Override
    public List<AnswerAnalysis> findAll() {
        return questionAnalysisRepository.findAll();
    }

    @Override
    public AnswerAnalysis findByQuestionId(Integer questionId) {
        return questionAnalysisRepository.findByQuestionId(questionId);


    }

}

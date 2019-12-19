package com.example.demo.service.Impi;

import com.example.demo.dataobject.QuestionScore;
import com.example.demo.repository.QuestionScoreRepository;
import com.example.demo.service.QuestionScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionScoreServiceImpi implements QuestionScoreService {

    @Autowired
    private QuestionScoreRepository questionScoreRepository;
    @Override
    public QuestionScore findByQuestionType(String questionType) {
        return questionScoreRepository.findByQuestionType(questionType);
    }

}

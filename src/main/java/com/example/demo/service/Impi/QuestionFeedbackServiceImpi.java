package com.example.demo.service.Impi;

import com.example.demo.dataobject.QuestionFeedback;
import com.example.demo.repository.QuestionFeedbackRepository;
import com.example.demo.service.QuestionFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionFeedbackServiceImpi implements QuestionFeedbackService {

    @Autowired
    private QuestionFeedbackRepository questionFeedbackRepository;
    @Override
    public QuestionFeedback save(QuestionFeedback questionFeedback) {
        return questionFeedbackRepository.save(questionFeedback);
    }

    @Override
    public List<QuestionFeedback> findByQuestionId(Integer questionId) {
        return questionFeedbackRepository.findByQuestionId(questionId);
    }

    @Override
    public QuestionFeedback findByUserIdAndQuestionId(Integer userId, Integer questionId) {
        return questionFeedbackRepository.findByUserIdAndQuestionId(userId,questionId);
    }

    @Override
    public QuestionFeedback deleteByUserIdAndQuestionId(Integer userId, Integer questionId) {
        return questionFeedbackRepository.deleteByUserIdAndQuestionId(userId,questionId);
    }

    @Override
    public QuestionFeedback update1(Integer questionId, Integer userId) {
        return questionFeedbackRepository.update1(questionId,userId);
    }
}

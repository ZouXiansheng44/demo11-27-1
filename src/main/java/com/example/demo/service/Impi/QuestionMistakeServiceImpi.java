package com.example.demo.service.Impi;

import com.example.demo.Enum.MistakeEnum;
import com.example.demo.dataobject.QuestionMistake;
import com.example.demo.repository.QuestionMistakeRepository;
import com.example.demo.repository.QuestionMistakeRepository2;
import com.example.demo.service.QuestionMistakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionMistakeServiceImpi implements QuestionMistakeService {

    @Autowired
    private QuestionMistakeRepository questionMistakeRepository;
    @Autowired
    private QuestionMistakeRepository2 questionMistakeRepository2;

    @Override
    public QuestionMistake save(QuestionMistake questionMistake) {
        return questionMistakeRepository.save(questionMistake);
    }

    @Override
    public List<QuestionMistake> findByUserId(Integer userId) {
        return questionMistakeRepository.findByUserId(userId);
    }

    @Override
    public QuestionMistake OnMistake(Integer questionId,Integer userId) {
        QuestionMistake questionMistake=questionMistakeRepository.findByQuestionIdAndUserId(questionId,userId);
        questionMistake.setMistakeStatus(MistakeEnum.UP.getCode());
        return questionMistakeRepository2.save(questionMistake);
    }

    @Override
    public QuestionMistake OffMistake(Integer questionId,Integer userId) {
        QuestionMistake questionMistake=questionMistakeRepository.findByQuestionIdAndUserId(questionId,userId);
        questionMistake.setMistakeStatus(MistakeEnum.DOWN.getCode());
        return questionMistakeRepository2.save(questionMistake);
    }

    @Override
    public QuestionMistake findByUserIdOne(Integer userId) {
        return questionMistakeRepository2.findByUserId(userId);
    }


    @Override
    public List<QuestionMistake> findByMistakeStatus(Integer mistakeStatus) {
        return questionMistakeRepository.findByMistakeStatus(0);
    }

    @Override
    public QuestionMistake findByQuestionIdAndUserId(Integer questionId, Integer userId) {
        return questionMistakeRepository.findByQuestionIdAndUserId(questionId,userId);
    }

    @Override
    public List<QuestionMistake> findByMistakeStatusAndUserId(Integer mistakeStatus, Integer userId) {
        return questionMistakeRepository.findByMistakeStatusAndUserId(mistakeStatus,userId);
    }

    @Override
    public QuestionMistake deleteByQuestionId(Integer questionId) {
        return questionMistakeRepository.deleteByQuestionId(questionId);
    }

    @Override
    public QuestionMistake findByUserIdAndQuestionId(Integer userId, Integer questionId) {
        return questionMistakeRepository2.findByUserIdAndQuestionId(userId,questionId);
    }


}

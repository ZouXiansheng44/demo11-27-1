package com.example.demo.service.Impi;

import com.example.demo.Enum.QuestionCollectEnum;
import com.example.demo.Enum.ResultEnum;
import com.example.demo.dataobject.QuestionCollect;
import com.example.demo.exception.QuestionException;
import com.example.demo.repository.QuestionCollectRepository;
import com.example.demo.service.QuestionCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionCollectServiceImpi implements QuestionCollectService
{

    @Autowired
    private QuestionCollectRepository questionCollectRepository;

    @Override
    public QuestionCollect findByCollectId(Integer collectId) {
        return questionCollectRepository.findByCollectId(collectId);
    }

    @Override
    public List<QuestionCollect> findAll() {
        return questionCollectRepository.findAll();
    }

    @Override
    public QuestionCollect save(QuestionCollect questionCollect) {
        return questionCollectRepository.save(questionCollect);
    }

    @Override
    public List<QuestionCollect> findByUserId(Integer userId) {
        return questionCollectRepository.findByUserId(userId);
    }

    @Override
    public List<QuestionCollect> findByCollectStatus(Integer collectStatus) {
        return questionCollectRepository.findByCollectStatus(0);
    }

    @Override
    public List<QuestionCollect> findByCollectStatusAndUserId(Integer collectStatus, Integer userId) {
        return questionCollectRepository.findByCollectStatusAndUserId(collectStatus,userId);
    }

    @Override
    public QuestionCollect findByQuestionIdAndUserIdAndCollectStatus(Integer questionId, Integer userId, Integer collectStatus) {
        return questionCollectRepository.findByQuestionIdAndUserIdAndCollectStatus(questionId,userId,collectStatus);
    }

    @Override
    public QuestionCollect deleteByCollectId(Integer collectId) {
        return questionCollectRepository.deleteByCollectId(collectId);
    }

    @Override
    public QuestionCollect OnCollect(Integer userId) {
        QuestionCollect questionCollect=questionCollectRepository.findByCollectId(userId);
        if(questionCollect==null){
            throw new QuestionException(ResultEnum.USER_LOGIN_FAIL);
        }
        questionCollect.setCollectStatus(QuestionCollectEnum.UP.getCode());
        return questionCollectRepository.save(questionCollect);
    }

    @Override
    public QuestionCollect OffCollect(Integer userId) {
        QuestionCollect questionCollect=questionCollectRepository.findByCollectId(userId);
        if(questionCollect==null){
            throw new QuestionException(ResultEnum.USER_LOGIN_FAIL);
        }
        questionCollect.setCollectStatus(QuestionCollectEnum.DOWN.getCode());
        return questionCollectRepository.save(questionCollect);
    }

    @Override
    public QuestionCollect findByQuestionIdAndUserId(Integer questionId, Integer userId) {
        return questionCollectRepository.findByQuestionIdAndUserId(questionId, userId);
    }

}

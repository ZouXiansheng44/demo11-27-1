package com.example.demo.service.Impi;

import com.example.demo.dataobject.QuestionCollect;
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


}

package com.example.demo.service.Impi;

import com.example.demo.dataobject.QuestionBank;
import com.example.demo.repository.QuestionBankRepository;
import com.example.demo.repository.QuestionBankRepository2;
import com.example.demo.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBankServiceImpi implements QuestionBankService {
    QuestionBank questionBank=new QuestionBank();
    @Autowired
    private QuestionBankRepository questionBankRepository;
    @Autowired
    private QuestionBankRepository2 questionBankRepository2;
//    @Override
//    public List<QuestionBank> findAll() {
//        List<QuestionBank> questionBank=questionBankRepository.findAll();
//
//        String[] split =questionBank.getQuestionOption().split(",");
//        return questionBankRepository.findAll().add(split);
//    }
    @Override
    public List<QuestionBank> findAll() {
        return questionBankRepository.findAll();
    }

//    @Override
//    public QuestionBank findByQuestionId(Integer questionId) {
//        QuestionBank questionBank = new QuestionBank();
//        questionBank=questionBankRepository.findByQuestionId(questionId);
//        String[] split =questionBank.getQuestionOption().split(",");
//        return split;
//    }
    @Override
    public QuestionBank findByQuestionId(Integer questionId) {
        return questionBankRepository.findByQuestionId(questionId);
    }

    @Override
    public QuestionBank save(QuestionBank questionBank) {
        return questionBankRepository.save(questionBank);
    }

    @Override
    public List<QuestionBank> findByQuestionSubject(String questionSubject) {
        return questionBankRepository.findByQuestionSubject(questionSubject);
    }

    @Override
    public List<QuestionBank> findByQuestionType(String questionType) {
        return questionBankRepository.findByQuestionType(questionType);
    }

    @Override
    public List<QuestionBank> findByQuestionId2(Integer questionId) {
        return questionBankRepository2.findByQuestionId(questionId);
    }


}

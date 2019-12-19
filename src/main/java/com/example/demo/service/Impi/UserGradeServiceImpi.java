package com.example.demo.service.Impi;

import com.example.demo.dataobject.UserGrade;
import com.example.demo.repository.UserGradeRepository;
import com.example.demo.service.UserGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGradeServiceImpi implements UserGradeService {

    @Autowired
    private UserGradeRepository userGradeRepository;

    @Override
    public UserGrade findByGradeId(Integer gradeId) {
        return userGradeRepository.findByGradeId(gradeId);
    }

    @Override
    public List<UserGrade> findByQuestionSubject(String questionSubject) {
        return userGradeRepository.findByQuestionSubject("数学");
    }

    @Override
    public UserGrade save(UserGrade userGrade) {
        return userGradeRepository.save(userGrade);
    }

    @Override
    public List<UserGrade> querylike2(String questionSubject) {
        return userGradeRepository.querylike2(questionSubject);
    }

    @Override
    public List<UserGrade> findAll() {
        return userGradeRepository.findAll();
    }




}

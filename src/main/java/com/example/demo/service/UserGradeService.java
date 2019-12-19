package com.example.demo.service;

import com.example.demo.dataobject.UserGrade;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserGradeService {
    public UserGrade findByGradeId(Integer gradeId);
    public List<UserGrade> findByQuestionSubject(String questionSubject);
    public UserGrade save(UserGrade userGrade);
    public List<UserGrade> querylike2(@Param("questionSubject") String questionSubject );
    public List<UserGrade> findAll();
}

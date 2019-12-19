package com.example.demo.repository;

import com.example.demo.dataobject.UserGrade;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGradeRepository extends JpaRepository<UserGrade, Integer> {

    public UserGrade findByGradeId(Integer gradeId);

   //此方法并不会按照固定参数显示，而是按照整个列表排序显示
    @Query("select e from UserGrade e ORDER BY e.gradeValue DESC ")
    public List<UserGrade> findByQuestionSubject(String questionSubject);


    @Query("select e from UserGrade e WHERE e.questionSubject=:questionSubject ORDER BY e.gradeValue DESC ")
    public List<UserGrade> querylike2(@Param("questionSubject") String questionSubject );


}

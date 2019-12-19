package com.example.demo.repository;

import com.example.demo.dataobject.QuestionFeedback;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionFeedbackRepository extends JpaRepository<QuestionFeedback,Integer> {
    public QuestionFeedback findByFeedbackId(Integer feedbackId);
    public List<QuestionFeedback> findByQuestionId(Integer questionId);
    public QuestionFeedback findByUserIdAndQuestionId(Integer userId,Integer questionId);
    public QuestionFeedback deleteByUserIdAndQuestionId(Integer userId,Integer questionId);

    @Modifying
    @Query("update QuestionFeedback o set o.questionId =:questionId where o.userId=:userId")
    public QuestionFeedback update1(@Param("questionId" )Integer questionId,@Param("userId") Integer userId);
}

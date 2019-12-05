package com.example.demo.repository;

import com.example.demo.dataobject.QuestionCollect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCollectRepository extends JpaRepository<QuestionCollect,Integer>
{

    public QuestionCollect findByCollectId(Integer collectId);
 }

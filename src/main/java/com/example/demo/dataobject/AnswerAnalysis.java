package com.example.demo.dataobject;

import com.example.demo.utils.Date2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Proxy(lazy = false)
@Data
@DynamicInsert//动态
@DynamicUpdate
public class AnswerAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增长
    private Integer analysisId;
    private Integer questionId;
    private String analysisContent;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //使用@JsonSerialize注解，让返回的JSON对象中Date类型的数据显示的时间戳精确到秒
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}

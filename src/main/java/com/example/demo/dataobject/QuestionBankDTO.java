package com.example.demo.dataobject;

import com.example.demo.utils.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
//在类上边添加@JsonIgnoreProperties注释，为空的时候不序列化
@JsonIgnoreProperties
public class QuestionBankDTO {
    private Integer questionId;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionContent;
    //    private List<String> questionOption;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private List questionOption;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private List questionAnswer;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionType;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionSubject;
    private String questionStatus;
    //使用@JsonSerialize注解，让返回的JSON对象中Date类型的数据显示的时间戳精确到秒
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //使用@JsonSerialize注解，让返回的JSON对象中Date类型的数据显示的时间戳精确到秒
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

}

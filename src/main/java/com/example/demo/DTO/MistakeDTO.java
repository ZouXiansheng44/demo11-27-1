package com.example.demo.DTO;

import com.example.demo.utils.Date2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MistakeDTO {

    private Integer questionId;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionContent;
    //    private List<String> questionOption;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    //是list是因为要把questionOption题目选项切割为数组对象
    private List questionOption;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    //是list是因为要把questionAnswer题目答案切割为数组对象
    private List questionAnswer;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionType;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionSubject;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionStatus;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    //题目解析，该字段来自AnswerAnalysis表
    private String analysisContent;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    //题目分数，该字段来自QuestionScore表
    private Integer questionScore;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private List  feedbackContent;
    //    //使用@JsonSerialize注解，让返回的JSON对象中Date类型的数据显示的时间戳精确到秒
    @JsonSerialize(using = Date2LongSerializer.class)

    private Date createTime;
    //使用@JsonSerialize注解，让返回的JSON对象中Date类型的数据显示的时间戳精确到秒
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
}

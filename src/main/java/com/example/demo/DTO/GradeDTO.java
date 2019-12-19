package com.example.demo.DTO;

import com.example.demo.utils.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties
public class GradeDTO {

//    private Integer userId;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String userName;

    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private Integer gradeValue;

    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private String questionSubject;

    //使用@JsonSerialize注解，让返回的JSON对象中Date类型的数据显示的时间戳精确到秒
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    //使用@JsonSerialize注解，让返回的JSON对象中Date类型的数据显示的时间戳精确到秒
//    @JsonSerialize(using = Date2LongSerializer.class)
//    private Date updateTime;
}

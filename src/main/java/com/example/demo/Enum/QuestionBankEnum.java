package com.example.demo.Enum;

import lombok.Getter;

@Getter
public enum QuestionBankEnum implements CodeEnum {
    //
    MATH(1,"数学"),
    ENGLISH(2,"英语"),
    ;
    private Integer code;
    private  String msg;
    QuestionBankEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}

package com.example.demo.exception;


import com.example.demo.Enum.ResultEnum;
import lombok.Getter;

@Getter//只用getter更节省资源
public class QuestionException extends  RuntimeException
{

    private Integer code;

    private String msg;

    public QuestionException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public QuestionException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}

package com.example.demo.Enum;

import lombok.Getter;

@Getter
public enum QuestionCollectEnum implements CodeEnum{

    UP(0,"收藏"),
    DOWN(1,"不收藏")
    ;

    private Integer code;
    private  String msg;
    QuestionCollectEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

}

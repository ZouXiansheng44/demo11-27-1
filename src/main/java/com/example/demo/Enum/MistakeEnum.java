package com.example.demo.Enum;

import lombok.Getter;

@Getter
public enum MistakeEnum {

    UP(0,"收藏错题"),
    DOWN(1,"不收藏错题")
    ;
    private Integer code;
    private  String msg;
    MistakeEnum(Integer code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }
}

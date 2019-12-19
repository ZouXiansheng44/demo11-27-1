package com.example.demo.Enum;

import lombok.Getter;

@Getter
public enum ResultEnum
{

    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    USER_LOGIN_FAIL(2, "用户登录失败"),
    QUESTION_COLLECT(3,"已收藏"),
    LOGOUT_SUCCESS(15,"登陆成功")
    ;

    private String msg;
    private Integer code;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

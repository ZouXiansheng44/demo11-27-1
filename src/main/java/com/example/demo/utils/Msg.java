package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class Msg {

    private int code;
    private String msg;
    Map<String, Object> data = new HashMap<String, Object>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Msg success() {
        Msg msg = new Msg();
        msg.setCode(101);
        msg.setMsg("成功");
        return msg;
    }

    public static Msg failure() {
        Msg msg = new Msg();
        msg.setCode(102);
        msg.setMsg("失败");
        return msg;
    }

    public Msg add(String key, Object value) {
        this.getData().put(key, value);
        return this;
    }

}

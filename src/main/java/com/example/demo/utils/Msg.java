package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class Msg {
    private int code;
    private String msg;
    //String键，什么名字都可以，Object值任意类型都可以
    Map<String, Object> data = new HashMap<String, Object>();//这个HashMap用来把JSON打包传给前端

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
        //对成功赋值
        msg.setCode(101);
        //对成功赋文字标识
        msg.setMsg("成功");
        return msg;
    }

    public static Msg failure() {
        Msg msg = new Msg();
        //对失败赋值
        msg.setCode(102);
        //对失败赋文字标识
        msg.setMsg("失败");
        return msg;
    }

    public static Msg collect() {
        Msg msg = new Msg();
        //对失败赋值
        msg.setCode(103);
        //对失败赋文字标识
        msg.setMsg("已收藏");
        return msg;
    }

    public static Msg exists() {
        Msg msg = new Msg();
        //对失败赋值
        msg.setCode(104);
        //对失败赋文字标识
        msg.setMsg("用户已存在");
        return msg;
    }

    public static Msg comment() {
        Msg msg = new Msg();
        //对失败赋值
        msg.setCode(105);
        //对失败赋文字标识
        msg.setMsg("用户已评论");
        return msg;
    }
    //这个add方法实际上是用put实现的
    //add（）和put（）方法都是集合框架中的添加元素的方法。
    //区别是返回值不一样
    /**入参是key，value*/
    public Msg add(String key, Object value) {
       // add（）放回布尔（boolean）类型。因为像Set集合中不允许添加重复的元素。
        // 当HashSet调用add（）方法时，如果返回false，表示添加不成功。

        //put（）的使用是：添加时出现相同的键，那么后添加的值会替换（覆盖）
        // 掉此键对应的原来的值。并返回此键对应的原来的值。
        this.getData().put(key, value);
        /**出参是this，也就是赋值后的key，value*/
        return this;
    }

}

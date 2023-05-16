package com.wang.controller;


// 在springmvc_07_ssm的controller层中的BookController中表现层相应给客户端的数据有多种，不便于阅读，在这里要统一响应结果的格式。
// 所以在controlller层定义Result类封装响应结果。
// Result类中的字段并不是固定的，可以根据需要自行增减。

public class Result {

    //描述统一格式中的数据
    private Object data;
    //描述统一格式中的编码，用于区分操作，可以简化配置0或1表示成功失败.
    // code 的变量也很多，这里在controller层中定义Code类来封装不同的code变量。
    private Integer code;
    //描述统一格式中的消息，可选属性
    private String msg;

    // 这里使用构造器方便后面在创建对象的同时传递参数，不然使用Setter方法比较麻烦。
    public Result() {
    }

    public Result(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, Object data, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}

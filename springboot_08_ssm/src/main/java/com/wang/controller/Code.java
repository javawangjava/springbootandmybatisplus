package com.wang.controller;


// 在springmvc_07_ssm的controller层中的BookController中表现层相应给客户端的数据有多种，不便于阅读，在这里要统一响应结果的格式。
// 所以在controlller层定义Result类封装响应结果。
// Code类的静态变量对应Result类中的实例成员变量code。

// Code类封装响应码，不需要改变，所以这里定义为静态常量。
// Code类的常量设计也不是固定的，可以根据需要自行增减，例如将查询再进行细分为GET_OK，GET_ALL_OK，GET_PAGE_OK

public class Code {
    // 不同的状态码
    public static final Integer SAVE_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer UPDATE_OK = 20031;
    public static final Integer GET_OK = 20041;

    public static final Integer SAVE_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer UPDATE_ERR = 20030;
    public static final Integer GET_ERR = 20040;

    public static final Integer SYSTEM_ERR = 50001;
    public static final Integer SYSTEM_TIMEOUT_ERR = 50002;
    public static final Integer SYSTEM_UNKNOW_ERR = 59999;
    public static final Integer BUSINESS_ERR = 60002;

}

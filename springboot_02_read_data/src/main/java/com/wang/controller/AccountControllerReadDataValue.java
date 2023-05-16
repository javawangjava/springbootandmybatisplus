package com.wang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * YAML（YAML Ain't Markup Language），一种数据序列化格式：以数据为核心，重数据轻格式；
 * YAML文件扩展名：【*.yml（主流）】，【*.yaml】。
 * 属性值前面添加空格（属性名与属性值之间使用冒号+空格作为分隔）【核心规则：数据前面要加空格与冒号隔开】;
 * yaml数组数据:数组数据在数据书写位置的下方使用减号作为数据开始符号，每行书写一个数据，减号与数据间空格分隔;
 * yaml数据读取：
 *   【方式1】：使用@Value读取单个数据，属性名引用方式：${一级属性名.二级属性名……}；
 *   【方式2】：封装全部数据到Environment对象；框架中常使用，是使用反射实现的
 *   【方式3】：自定义对象封装指定数据【常用】
 *
 *
 * */


// yaml数据读取【方式1】：使用@Value读取单个数据，属性名引用方式：${一级属性名.二级属性名……}
@RestController
@RequestMapping("/accounts")
public class AccountControllerReadDataValue {

    //其实和前面的类似就是简单类型【基本数据类型和字符串】使用@Value读取单个数据。
    //使用@Value读取单个数据，属性名引用方式：${一级属性名.二级属性名……}
    //如果一级属性是数组，那么就在对应的一级后面添加数组下标。
    @Value("${lesson}")
    private String lesson;
    @Value("${server.port}")
    private Integer port;
    @Value("${enterprise.name}")
    private String name;
    @Value("${enterprise.age}")
    private Integer age;
    @Value("${enterprise.tel}")
    private String tel;
    @Value("${enterprise.subject[0]}")
    private String subject_00;
    @Value("${enterprise.subject[1]}")
    private String subject_01;
    @Value("${enterprise.subject[2]}")
    private String subject_02;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id) {
        System.out.println("========================");
        System.out.println("lesson:" + lesson);
        System.out.println("server.port:" + port);
        System.out.println("enterprise.name:" + name);
        System.out.println("enterprise.age:" + age);
        System.out.println("enterprise.tel:" + tel);
        System.out.println("enterprise.subject[0]:" + subject_00);
        System.out.println("enterprise.subject[1]:" + subject_01);
        System.out.println("enterprise.subject[2]:" + subject_02);
        System.out.println("========================");
        return "hello , spring boot!";
    }

}

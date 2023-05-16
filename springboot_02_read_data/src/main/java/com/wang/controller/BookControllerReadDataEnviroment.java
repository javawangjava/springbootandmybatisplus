package com.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
 * */


// yaml数据读取【方式2】：封装全部数据到Environment对象；框架中常使用，是使用反射实现的
@RestController
@RequestMapping("/books")
public class BookControllerReadDataEnviroment {

    //使用Environment封装全配置数据【就是自动封装配置文件里面的所有数据】
    @Autowired
    private Environment environment;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id) {
        System.out.println("========================");
        System.out.println(environment.getClass());
        System.out.println("lesson:" + environment.getProperty("lesson"));
        System.out.println("server.port:" + environment.getProperty("server.port"));
        System.out.println("enterprise.name:" + environment.getProperty("enterprise.name"));
        System.out.println("enterprise.age:" + environment.getProperty("enterprise.age"));
        System.out.println("enterprise.tel:" + environment.getProperty("enterprise.tel"));
        System.out.println("enterprise.subject[0]:" + environment.getProperty("enterprise.subject[0]"));
        System.out.println("enterprise.subject[1]:" + environment.getProperty("enterprise.subject[1]"));
        System.out.println("enterprise.subject[2]:" + environment.getProperty("enterprise.subject[2]"));
        System.out.println("========================");
        return "hello , spring boot!";
    }

}

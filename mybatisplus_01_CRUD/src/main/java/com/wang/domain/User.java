package com.wang.domain;

import lombok.Data;

// lombok 插件
// 常用注解：@Data，为当前实体类在编译期设置对应的get/set方法，toString方法，hashCode方法，equals方法等

@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;
}
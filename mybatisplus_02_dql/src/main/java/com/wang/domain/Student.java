package com.wang.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 数据库表的字段映射与表名映射不对应

// 问题一：表字段与编码属性设计不同步：在模型类【实体类】属性上方，使用@TableField属性注解，通过==value==属性，设置当前属性对应的数据库表中的字段关系。
// 问题二：编码中添加了数据库中未定义的属性：在模型类【实体类】属性上方，使用@TableField注解，通过==exist==属性，设置属性在数据库表字段中是否存在，默认为true。此属性无法与value合并使用。
// 问题三：采用默认查询开放了更多的字段查看权限：在模型类【实体类】属性上方，使用@TableField注解，通过==select==属性：设置该属性是否参与查询。此属性与select()映射配置不冲突
// 问题四：表名与编码开发设计不同步:在模型类【实体类】上方，使用@TableName注解，通过==value==属性，设置当前类对应的数据库表名称。

@Data
@TableName("tbl_student")
public class Student {
    private Long id;
    private String name;
    @TableField(value = "pwd",select = false)
    private String password;
    private Integer age;
    private String tel;
    @TableField(exist = false)//表示online字段不参与CRUD操作
    private Integer online;// 该属性在数据库字段中不存在
}

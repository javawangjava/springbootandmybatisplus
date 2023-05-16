package com.wang.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

// 数据库表的字段映射与表名映射不对应

// 问题一：表字段与编码属性设计不同步：在模型类【实体类】属性上方，使用@TableField属性注解，通过==value==属性，设置当前属性对应的数据库表中的字段关系。
// 问题二：编码中添加了数据库中未定义的属性：在模型类【实体类】属性上方，使用@TableField注解，通过==exist==属性，设置属性在数据库表字段中是否存在，默认为true。此属性无法与value合并使用。
// 问题三：采用默认查询开放了更多的字段查看权限：在模型类【实体类】属性上方，使用@TableField注解，通过==select==属性：设置该属性是否参与查询。此属性与select()映射配置不冲突
// 问题四：表名与编码开发设计不同步:在模型类【实体类】上方，使用@TableName注解，通过==value==属性，设置当前类对应的数据库表名称。

/*
*
* id生成策略控制（Insert）
* 不同的表应用不同的id生成策略
*       日志：自增（1,2,3,4，……）
*       购物订单：特殊规则（FQ23948AK3843）
*       外卖单：关联地区日期等信息（10 04 20200314 34 91）
*       关系表：可省略id
*
* */


@Data
@TableName("tbl_student")//设置表名映射关系
public class Student {
    // @TableId(type = IdType.ASSIGN_ID) // 设置主键生成策略
    private Long id;
    private String name;
    @TableField(value = "pwd",select = false)
    private String password;
    private Integer age;
    private String tel;
    @TableField(exist = false)//表示online字段不参与CRUD操作
    private Integer online;// 该属性在数据库字段中不存在

    //@TableLogic(value = "0",delval = "0") //逻辑删除字段，标记当前记录是否被删除
    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

}

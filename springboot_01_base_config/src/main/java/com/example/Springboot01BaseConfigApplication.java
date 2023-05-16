package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
*
* SpringBoot项目快速启动：jar支持命令行启动需要依赖maven插件支持，请确认打包时是否具有SpringBoot对应的maven插件。
* 【步骤一】：对SpringBoot项目打包（执行Maven构建指令package）；
* 【步骤二】：到打完包的jar文件位置，在标识文件路径的那个框中输入cmd然后回车【这里其实就是方便的去欸的那个路径】，执行启动指令：java -jar ***.jar【java -jar  jar包的全名】# 项目的名称根据实际情况修改
*
*
* starter：起步依赖，SpringBoot中常见项目名称，定义了当前项目使用的所有项目坐标，以达到减少依赖配置的目的；
* parent：所有SpringBoot项目要继承的项目，定义了若干个坐标版本号（依赖管理，而非依赖），以达到减少依赖冲突的目的；
*
* 实际开发：使用任意坐标时，仅书写GAV中的G和A，V由SpringBoot提供；如发生坐标错误，再指定version（要小心版本冲突）
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
    </dependency>
*
*
* SpringBoot程序启动:
* SpringBoot在创建项目时，采用jar的打包方式;
* SpringBoot的引导类是项目的入口，运行main方法就可以启动项目;
* 使用maven依赖管理变更起步依赖项;
* Jetty比Tomcat更轻量级，可扩展性更强（相较于Tomcat），谷歌应用引擎（GAE）已经全面切换为Jetty.
*
*
* SpringBoot配置文件格式:*.properties,*.yml,*.yaml
* 修改服务器端口:见三个文件。
* 【application.properties】:
* 【application.yml】：
* 【application.yaml】：
* SpringBoot配置文件加载顺序：application.properties > application.yml > application.yaml
* SpringBoot核心配置文件名为application；
* SpringBoot内置属性过多，且所有属性集中在一起修改，在使用时，通过提示键+关键字修改属性。
* YAML（YAML Ain't Markup Language），一种数据序列化格式：以数据为核心，重数据轻格式；
* YAML文件扩展名：【*.yml（主流）】，【*.yaml】。
* 属性值前面添加空格（属性名与属性值之间使用冒号+空格作为分隔）【核心规则：数据前面要加空格与冒号隔开】
*
* */

// SpringBoot的引导类是项目的入口，运行main方法就可以启动项目
@SpringBootApplication
public class Springboot01BaseConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01BaseConfigApplication.class, args);
    }

}

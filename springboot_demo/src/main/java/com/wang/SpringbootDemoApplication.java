package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
*
* SpringBoot项目快速启动：jar支持命令行启动需要依赖maven插件支持，请确认打包时是否具有SpringBoot对应的maven插件。
* 【步骤一】：对SpringBoot项目打包（执行Maven构建指令package）；
* 【步骤二】：到打完包的jar文件位置，在标识文件路径的那个框中输入cmd然后回车【这里其实就是方便的去确定那个路径】，执行启动指令：java -jar ***.jar【java -jar  jar包的全名】#
* 项目的名称根据实际情况修改
*
*
* starter：起步依赖，SpringBoot中常见项目名称，定义了当前项目使用的所有项目坐标，以达到减少依赖配置的目的；
* parent：所有SpringBoot项目要继承的项目，定义了若干个坐标版本号（依赖管理，而非依赖），以达到减少依赖冲突的目的；
*
* 实际开发：使用任意坐标时，仅书写GAV中的G和A，V由SpringBoot提供；如发生坐标错误，再指定version（要小心版本冲突）
*
* SpringBoot程序启动:
* 1.SpringBoot在创建项目时，采用jar的打包方式;
* 2.SpringBoot的引导类是项目的入口，运行main方法就可以启动项目;
* 3.使用maven依赖管理变更起步依赖项;
* 4.Jetty比Tomcat更轻量级，可扩展性更强（相较于Tomcat），谷歌应用引擎（GAE）已经全面切换为Jetty.
*
*
* SpringBoot配置文件格式:*.properties,*.yml,*.yaml
* SpringBoot配置文件加载顺序：application.properties > application.yml > application.yaml
* SpringBoot核心配置文件名为application；
* SpringBoot内置属性过多，且所有属性集中在一起修改，在使用时，通过提示键+关键字修改属性。
* YAML（YAML Ain't Markup Language），一种数据序列化格式：以数据为核心，重数据轻格式；
* YAML文件扩展名：【*.yml（主流）】，【*.yaml】。
* 属性值前面添加空格（属性名与属性值之间使用冒号+空格作为分隔）【核心规则：数据前面要加空格与冒号隔开】
*
* yaml数组数据:数组数据在数据书写位置的下方使用减号作为数据开始符号，每行书写一个数据，减号与数据间空格分隔;
* yaml数据读取：
*   【方式1】：使用@Value读取单个数据，属性名引用方式：${一级属性名.二级属性名……}；
*   【方式2】：封装全部数据到Environment对象；框架中常使用，是使用反射实现的
*   【方式3】：自定义对象封装指定数据【常用】
*
*
*
* SpringBoot中4级配置文件:
*       1级： file ：config/application.yml 【最高】
*       2级： file ：application.yml
*       3级：classpath：config/application.yml
*       4级：classpath：application.yml 【最低】
        作用：
        1级与2级留做系统打包后设置通用属性
        3级与4级用于系统开发阶段设置通用属性
*
* */


/*
*
* SpringBoot实现ssm整合：
*   【步骤一】:选择当前模块需要使用的技术集（MyBatis、MySQL，SpringWeb）;
*   【步骤二】:在pom.xml文件中配置起步依赖，必要的资源坐标(druid);
*   【步骤三】:在yml配置文件中设置设置数据源、端口等参数;
*   【步骤三】:定义数据层接口与映射配置，即在Dao层的接口上使用@Mapper注解
*   【步骤四】:设置domain【poji类】，业务层Service和表现层Controller和就是正常设置；
*   【步骤五】:配置对应的测试类进行测试，通常从Dao层开始写完一层测试一层。
*   【步骤六】:SpringBoot中静态资源保存在resources的static文件夹中
*
*
* */

@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}

package com.wang;

import com.wang.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/*
*
*@SpringBootTest:测试类注解；classes：设置SpringBoot启动类；如果测试类在SpringBoot启动类的包或子包中，可以省略启动类的设置，也就是省略classes的设定；
*
* */

//编写测试类，默认自动生成了一个
@SpringBootTest
class Springboot06TestApplicationTests {

    @Autowired
    private BookService bookService;

    @Test
    void contextLoads() {
        bookService.save();

    }

}

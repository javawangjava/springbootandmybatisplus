package com.wang.service;

import com.wang.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
 *
 * 功能模块：
 * 1.表与实体类；在MySQL数据库中创建数据库和表，在项目中定义实体类【pojo类】
 * 2.dao【数据层】（接口+自动代理使用@Mapper注释）：数据层接口测试（整合JUnit）；
 * 3.service【业务层】（接口+实现类）：业务层接口测试（整合JUnit）；
 * 4.controller【表现层】（类）：表现层接口测试（PostMan）
 *
 *
 * 功能模块开发步骤：
 * 注意只有在数据层，业务层和表现层的实现类上面使用@Component来标识Bean对象。
 * 【步骤一】：数据层开发(BookDao)；
 *       1.定义pojo类就是实体类【Book实体类】；
 *       2.数据层Dao【mappper】一般写成接口形式+注解形式【BookDao接口】；
 * 【步骤二】：业务层开发(BookService/BookServiceImpl)；
 *       业务层Service一般写接口和实现类；
 *       接口中的方法一般要见名知意，并且要写文档注释。增删查改要有返回值。
 *       1.定义Service接口并在接口中开启事务管理【@Transactional //表示所有方法进行事务管理】【BookService接口】；
 *       2.定义Service接口实现类【BookServiceImpl实现类】；
 * 【步骤三】：表现层开发(BookController)；
 *       1.定义表现层的类；
 *
 *
 * 接口测试：应该写完一层测试一层；Spring整合Junit做测试时要注意test文件夹和main文件夹的结构层次相对应。
 * 【步骤一】：数据层开发(BookDao)写完了测试Dao层：数据层接口测试（整合JUnit）；；
 * 【步骤二】：业务层开发(BookService)写完了测试Service层：业务层接口测试（整合JUnit）；；
 * 【步骤三】：表现层开发(BookController)写完了测试Controller层：表现层接口测试（PostMan）；
 *
 * * */


// 业务层接口测试。注意test文件下和main文件下的结构是对应的
// 业务层写完了测试业务层。Spring整合Junit测试业务层方法。
// 表现层写完了测试表现层。

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    // 测试查询,这里增删改都需要查看数据库的所有数据，这样就可以看到。
    // 增
    @Test
    //public void testSave(Book book) {
    public void testSave() {
        Book book=new Book();
        //book.setId(10);
        book.setType("数学");
        book.setName("53");
        book.setDescription("高考必备");
        bookService.save(book);
    }

    // 删
    @Test
    public void testDelete() {
        bookService.delete(13);
    }

    // 查
    @Test
    public void testGetById() {
        Book book = bookService.getById(6);
        System.out.println(book);
    }

    // 查
    @Test
    public void testGetAll() {
        List<Book> all = bookService.getAll();
        System.out.println(all);
    }

    // 改
    @Test
    public void testUpdate() {
        Book book = bookService.getById(9);
        book.setType("数学");
        book.setName("53");
        book.setDescription("高考必备123");
        bookService.update(book);
    }

}

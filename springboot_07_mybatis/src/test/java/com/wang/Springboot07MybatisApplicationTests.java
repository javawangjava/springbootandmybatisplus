package com.wang;

import com.wang.dao.BookDao;
import com.wang.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springboot07MybatisApplicationTests {

    @Autowired
    private BookDao bookDao;

    // 测试查询
    // 增
    @Test
    public void testSave() {
        Book book=new Book();
        //book.setId(10);
        book.setType("数学");
        book.setName("53");
        book.setDescription("高考必备");
        bookDao.save(book);
    }

    // 删
    @Test
    public void testDelete() {
        bookDao.delete(13);
    }

    // 查
    @Test
    public void testGetById() {
        Book book = bookDao.getById(1);
        System.out.println(book);
    }

    // 查
    @Test
    public void testGetAll() {
        List<Book> all = bookDao.getAll();
        System.out.println(all);
    }

    // 改
    @Test
    public void testUpdate() {
        Book book = bookDao.getById(10);
        book.setType("数学");
        book.setName("53");
        book.setDescription("高考必备456");
        bookDao.update(book);
    }

}

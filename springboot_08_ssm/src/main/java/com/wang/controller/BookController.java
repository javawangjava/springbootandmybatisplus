package com.wang.controller;

import com.wang.domain.Book;
import com.wang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 表现层controller调用业务层service的对象。
// 表现层数据封装返回Result对象

// 在springmvc_07_ssm的controller层中的BookController中表现层相应给客户端的数据有多种，不便于阅读，在这里要统一响应结果的格式。
// 所以在controlller层定义Result类封装响应结果。

// 根据业务层方法的返回值来设计表现层对应的结果数据的Result对象。
// Result类有三个实例成员变量：Object data,Integer code,String msg。

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping// 增
    public Result save(@RequestBody Book book) {
        // 标识符flag标识业务层的结果，根据业务层的结果返回不同的状态码
        boolean flag = bookService.save(book);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @DeleteMapping("/{id}")// 删
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.delete(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/{id}")// 查
    public Result getById(@PathVariable Integer id) {
        // 查结果返回的是对象，那么检查对象是否为null,来确定Result对象的成员变量。
        Book book = bookService.getById(id);
        Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
        String msg = book != null ? "" : "数据查询失败，请重试！";
        return new Result(code, book, msg);
    }

    @GetMapping// 查
    public Result getAll() {
        // 查结果返回的是对象的集合，那么检查集合是否为null,来确定Result对象的成员变量。
        List<Book> bookList = bookService.getAll();
        Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = bookList != null ? "" : "数据查询失败，请重试！";
        return new Result(code, bookList, msg);
    }


    @PutMapping// 改
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.update(book);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }
}

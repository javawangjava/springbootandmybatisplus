package com.wang.dao;

import com.wang.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 数据层Dao【mappper】一般写成接口形式+注解形式【BookDao接口】
// 接口+自动代理

// @Repository,//注解开发的@Component和三个衍生注解都要注解在实现类上面，因为这几个注解都是表示Bean对象的，所以必须在能产生对象的类上面使用。
// Dao层使用接口+注释实现时，将Dao接口使用@Mapper注释。

// 定义数据层接口与映射配置
@Mapper// Dao层接口使用Mapper注释
public interface BookDao {

    // 这里就修改的很好，将增，删，改方法的返回值改为int型，就是表示当前操作影像的行数。
    // 查单个数据只要返回数据库中的单个元素；查所有元素只要返回数据库中的所有元素，以集合形式。
    // Dao层操作数据库
    // 增
    @Insert("insert into tbl_book (type,name,description) values(#{type},#{name},#{description})")
    public int save(Book book);//返回值表示影响的行数

    // 删
    @Delete("delete from tbl_book where id = #{id}")
    public int delete(Integer id);

    // 查
    @Select("select * from tbl_book where id = #{id}")
    public Book getById(Integer id);

    // 查
    @Select("select * from tbl_book")
    public List<Book> getAll();

    // 改
    @Update("update tbl_book set type = #{type}, name = #{name}, description = #{description} where id = #{id}")
    public int update(Book book);
}
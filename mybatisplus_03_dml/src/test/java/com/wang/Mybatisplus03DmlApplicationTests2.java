package com.wang;

import com.wang.dao.StudentDao;
import com.wang.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mybatisplus03DmlApplicationTests2 {

    @Autowired
    private StudentDao studentDao;

    @Test
    void testSave() {
        Student student = new Student();
        student.setName("123");
        student.setPassword("123");
        student.setAge(123);
        student.setTel("18512345678");
        studentDao.insert(student);
    }


    @Test
        //按照主键删除多条记录 deleteBatchIds()
    void testDeleteBatchIds() {
        List<Long> list = new ArrayList<>();
        list.add(1655354556845961218L);
        list.add(1655354556845961219L);
        list.add(1655354556845961220L);
        studentDao.deleteBatchIds(list);
    }


    @Test
        // 根据主键查询多条记录
    void testSelectBatchIds() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        studentDao.selectBatchIds(list);
    }



    /*
     *
     * 删除操作业务问题：业务数据从数据库中丢弃;
     * 逻辑删除：为数据设置是否可用状态字段，删除时设置状态字段为不可用状态，数据保留在数据库中;
     * 逻辑删除本质：逻辑删除的本质其实是修改操作。如果加了逻辑删除字段，查询数据时也会自动带上逻辑删除字段。
     * */

    @Test
        //
    void testDeleteUpdate() {
        // SELECT id,name,age,tel,deleted FROM tbl_student WHERE id=? AND deleted=0
        Student student = studentDao.selectById(2L);
        System.out.println(student);
    }

    /*
     *
     * 乐观锁（Update）
     *   乐观锁假设数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测，如果发现冲突了，则返回给用户错误的信息，让用户决定如何去做。
     *   使用乐观锁的流程：
     *       1.数据库表中添加锁标记字段；
     *       2.实体类中添加对应字段，并设定当前字段为逻辑删除标记字段；
     *       3.配置乐观锁拦截器实现锁机制对应的动态SQL语句拼装；
     *       4.使用乐观锁机制在修改前必须先获取到对应数据的verion方可正常进行；
     *
     * */


    @Test
    void testUpdateByIdOptimistic() {
     /*
        // 使用乐观锁机制在修改前必须先获取到对应数据的verion方可正常进行
        Student student=new Student();
        student.setId(5L);
        student.setName("张三");
        student.setVersion(1);
        studentDao.updateById(student);
        */


        //1.先通过要修改的数据id将当前数据查询出来
        Student student = studentDao.selectById(5L);
        //2.将要修改的属性逐一设置进去
        student.setName("张三");

        //UPDATE tbl_student SET name=?, age=?, tel=?, version=? WHERE id=? AND version=? AND deleted=0
        studentDao.updateById(student);
    }


    @Test
    void testUpdateByIdOptimistic2() {
        // 乐观锁假设数据一般情况下不会造成冲突，所以在数据进行提交更新的时候，才会正式对数据的冲突与否进行检测，如果发现冲突了，则返回给用户错误的信息，让用户决定如何去做

        //1.先通过要修改的数据id将当前数据查询出来
        Student student = studentDao.selectById(5L);
        Student student2 = studentDao.selectById(5L);

        //2.将要修改的属性逐一设置进去
        student.setName("张三");
        studentDao.updateById(student);

        student.setName("张三2");
        studentDao.updateById(student2);
    }


}

package com.wang;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.dao.UserDao;
import com.wang.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01CrudApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
    void testSave(){//增：Save
        User user=new User();
        user.setName("隔壁老王");
        user.setPassword("laowang");
        user.setAge(30);
        user.setTel("18512345678");
        userDao.insert(user);
    }

    @Test
    void testDelete(){//删：Delete
        userDao.deleteById(1655328572041666561L);
    }

    @Test
    void testGetById(){//查：Get
        User user = userDao.selectById(5L);
        System.out.println(user);
    }

    @Test
    void testGetAll() {//查：Get
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }

    @Test
    void testUpdate(){//改：Update
        User user = new User();
        user.setId(7L);
        user.setName("赵六666");
        user.setPassword("赵六666");
        userDao.updateById(user);
    }


    // 分页查询
    @Test
    void testSelectPage(){
        // 1.创建IPage分页对象，设置分页参数
        IPage<User> page = new Page<>(1,3);
        // 2.执行分页查询
        userDao.selectPage(page,null);
        // 3.获取分页结果
        System.out.println("当前页码值"+page.getCurrent());
        System.out.println("每页显示数"+page.getSize());
        System.out.println("总页数"+page.getPages());
        System.out.println("当前页数据"+page.getRecords());
        System.out.println("总条数"+page.getTotal());
    }



}

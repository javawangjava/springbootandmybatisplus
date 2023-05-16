package com.wang;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.dao.UserDao;
import com.wang.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
class Mybatisplus02DqlApplicationTests {

    @Autowired
    private UserDao userDao;

    @Test
        // 方式一：按条件查询
    void test1() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 18);
        List<User> userList = userDao.selectList(queryWrapper);
        System.out.println(userList);
    }

    @Test
        // 方式二：Lambda格式按条件查询
    void test2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().lt(User::getAge, 18);
        List<User> userList = userDao.selectList(queryWrapper);
        System.out.println(userList);
    }

    @Test
        // 方式三：Lambda格式按条件查询2
    void test3() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.lt(User::getAge, 18);
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);
    }


    @Test
        // 并且关系 and
    void testAnd() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 并且关系：10到30岁之间
        lambdaQueryWrapper.lt(User::getAge, 30).gt(User::getAge, 10);
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);
    }


    @Test
        // 或者关系 or
    void testOr() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 或者关系：小于10岁或者大于30岁
        lambdaQueryWrapper.lt(User::getAge, 10).or().gt(User::getAge, 30);
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);
    }


    @Test
        // NULL值处理方式1: if语句控制条件追加
    void testNull1() {
        Integer minAge = 10;//将来有用户传递进来,此处简化成直接定义变量了
        Integer maxAge = null;//将来有用户传递进来,此处简化成直接定义变量了
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // if语句控制条件追加
        if (minAge != null) {
            lambdaQueryWrapper.gt(User::getAge, minAge);
        }
        if (maxAge != null) {
            lambdaQueryWrapper.lt(User::getAge, maxAge);
        }
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
        // NULL值处理方式2: 条件参数控制
    void testNull2() {
        Integer minAge = 10;//将来有用户传递进来,此处简化成直接定义变量了
        Integer maxAge = null;//将来有用户传递进来,此处简化成直接定义变量了
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //参数1：如果表达式为true，那么查询才使用该条件
        lambdaQueryWrapper.gt(minAge != null, User::getAge, minAge);
        lambdaQueryWrapper.gt(minAge != null, User::getAge, maxAge);

        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
        // NULL值处理方式3:条件参数控制（链式编程）
    void testNull3() {
        Integer minAge = 10;//将来有用户传递进来,此处简化成直接定义变量了
        Integer maxAge = null;//将来有用户传递进来,此处简化成直接定义变量了
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //参数1：如果表达式为true，那么查询才使用该条件
        lambdaQueryWrapper.gt(minAge != null, User::getAge, minAge)
                .lt(maxAge != null, User::getAge, maxAge);
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
        // 查询结果包含模型类中部分属性
    void test4() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(User::getId, User::getName, User::getAge);
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age", "tel");
        List<User> userList2 = userDao.selectList(queryWrapper);
        System.out.println(userList2);
    }


    @Test
        // 查询结果包含模型类中未定义的属性
    void test5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("count(*) as count,tel");
        queryWrapper.groupBy("tel");
        List<Map<String, Object>> userList = userDao.selectMaps(queryWrapper);
        System.out.println(userList);
    }


    @Test
        // 用户登录（eq匹配）
    void testEQ() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // eq()等同于=
        lambdaQueryWrapper.eq(User::getName, "jack").eq(User::getPassword, "123456");
        User user = userDao.selectOne(lambdaQueryWrapper);
        System.out.println(user);
    }


    @Test
        // 购物设定价格区间、户籍设定年龄区间（le ge匹配 或 between匹配）
    void testBetween() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //范围查询 lt le gt ge eq between
        lambdaQueryWrapper.between(User::getAge,10,30);
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);
    }


    @Test
        // 查信息，搜索新闻（非全文检索版：like匹配）
    void testLike() {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //模糊匹配 like,likeLeft,likeRight,notLike
        lambdaQueryWrapper.like(User::getName,"j");
        List<User> userList = userDao.selectList(lambdaQueryWrapper);
        System.out.println(userList);
    }




}

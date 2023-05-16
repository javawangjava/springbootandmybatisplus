package com.wang;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.dao.StudentDao;

import com.wang.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;


@SpringBootTest
class Mybatisplus02DqlApplicationTests2 {

    @Autowired
    private StudentDao studentDao;

    @Test
        // 方式一：按条件查询
    void test1() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 18);
        List<Student> StudentList = studentDao.selectList(queryWrapper);
        System.out.println(StudentList);
    }

    @Test
        // 方式二：Lambda格式按条件查询
    void test2() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().lt(Student::getAge, 18);
        List<Student> StudentList = studentDao.selectList(queryWrapper);
        System.out.println(StudentList);
    }

    @Test
        // 方式三：Lambda格式按条件查询2
    void test3() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.lt(Student::getAge, 18);
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        System.out.println(StudentList);
    }


    @Test
        // 并且关系 and
    void testAnd() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 并且关系：10到30岁之间
        lambdaQueryWrapper.lt(Student::getAge, 30).gt(Student::getAge, 10);
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        System.out.println(StudentList);
    }


    @Test
        // 或者关系 or
    void testOr() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 或者关系：小于10岁或者大于30岁
        lambdaQueryWrapper.lt(Student::getAge, 10).or().gt(Student::getAge, 30);
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        System.out.println(StudentList);
    }


    @Test
        // NULL值处理方式1: if语句控制条件追加
    void testNull1() {
        Integer minAge = 10;//将来有用户传递进来,此处简化成直接定义变量了
        Integer maxAge = null;//将来有用户传递进来,此处简化成直接定义变量了
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // if语句控制条件追加
        if (minAge != null) {
            lambdaQueryWrapper.gt(Student::getAge, minAge);
        }
        if (maxAge != null) {
            lambdaQueryWrapper.lt(Student::getAge, maxAge);
        }
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        StudentList.forEach(System.out::println);
    }


    @Test
        // NULL值处理方式2: 条件参数控制
    void testNull2() {
        Integer minAge = 10;//将来有用户传递进来,此处简化成直接定义变量了
        Integer maxAge = null;//将来有用户传递进来,此处简化成直接定义变量了
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //参数1：如果表达式为true，那么查询才使用该条件
        lambdaQueryWrapper.gt(minAge != null, Student::getAge, minAge);
        lambdaQueryWrapper.gt(minAge != null, Student::getAge, maxAge);

        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        StudentList.forEach(System.out::println);
    }


    @Test
        // NULL值处理方式3:条件参数控制（链式编程）
    void testNull3() {
        Integer minAge = 10;//将来有用户传递进来,此处简化成直接定义变量了
        Integer maxAge = null;//将来有用户传递进来,此处简化成直接定义变量了
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //参数1：如果表达式为true，那么查询才使用该条件
        lambdaQueryWrapper.gt(minAge != null, Student::getAge, minAge)
                .lt(maxAge != null, Student::getAge, maxAge);
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        StudentList.forEach(System.out::println);
    }


    @Test
        // 查询结果包含模型类中部分属性
    void test4() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Student::getId, Student::getName, Student::getAge);
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        System.out.println(StudentList);

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age", "tel");
        List<Student> StudentList2 = studentDao.selectList(queryWrapper);
        System.out.println(StudentList2);
    }


    @Test
        // 查询结果包含模型类中未定义的属性
    void test5() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("count(*) as count,tel");
        queryWrapper.groupBy("tel");
        List<Map<String, Object>> StudentList = studentDao.selectMaps(queryWrapper);
        System.out.println(StudentList);
    }


    @Test
        // 用户登录（eq匹配）
    void testEQ() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // eq()等同于=
        lambdaQueryWrapper.eq(Student::getName, "jack").eq(Student::getPassword, "123456");
        Student Student = studentDao.selectOne(lambdaQueryWrapper);
        System.out.println(Student);
    }


    @Test
        // 购物设定价格区间、户籍设定年龄区间（le ge匹配 或 between匹配）
    void testBetween() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //范围查询 lt le gt ge eq between
        lambdaQueryWrapper.between(Student::getAge,10,30);
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        System.out.println(StudentList);
    }


    @Test
        // 查信息，搜索新闻（非全文检索版：like匹配）
    void testLike() {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //模糊匹配 like,likeLeft,likeRight,notLike
        lambdaQueryWrapper.like(Student::getName,"j");
        List<Student> StudentList = studentDao.selectList(lambdaQueryWrapper);
        System.out.println(StudentList);
    }


}

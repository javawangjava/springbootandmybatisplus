package com.wang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
}

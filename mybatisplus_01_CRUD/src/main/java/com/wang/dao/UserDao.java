package com.wang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.domain.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDao extends BaseMapper<User> {
}

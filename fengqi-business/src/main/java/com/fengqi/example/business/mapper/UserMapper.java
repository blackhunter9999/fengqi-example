package com.fengqi.example.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengqi.example.business.entity.User;

/**
 * 用户Mapper接口
 * 继承BaseMapper可以获得MyBatis-Plus提供的基础CRUD操作
 */
public interface UserMapper extends BaseMapper<User> {
    // 这里定义自定义的SQL方法
}


package com.fengqi.example.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fengqi.example.business.entity.User;

/**
 * 用户服务接口
 * 继承IService可以获得MyBatis-Plus提供的更多业务层操作方法
 */
public interface UserService extends IService<User> {
    // 这里定义自定义的业务方法
}


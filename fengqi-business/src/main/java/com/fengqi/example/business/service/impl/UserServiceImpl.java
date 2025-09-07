package com.fengqi.example.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fengqi.example.business.entity.User;
import com.fengqi.example.business.mapper.UserMapper;
import com.fengqi.example.business.service.UserService;
import org.springframework.stereotype.Service;

/** 
 * @Description 用户服务实现类
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // 这里实现自定义的业务方法
}


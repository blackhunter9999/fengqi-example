package com.fengqi.example.business.init;

import com.fengqi.example.business.entity.User;
import com.fengqi.example.business.service.UserService;
import com.fengqi.example.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据初始化类
 * 在应用启动时插入测试数据
 */
@Component
@Slf4j
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 检查是否已有用户数据
        if (userService.count() == 0) {
            log.info("开始初始化用户数据...");
            List<User> userList = new ArrayList<>();

            // 创建测试用户
            User user1 = new User();
            user1.setUsername("admin");
            user1.setPassword("admin123");
            user1.setNickname("管理员");
            user1.setEmail("admin@example.com");
            user1.setPhone("13800138001");
            user1.setStatus(Constants.USER_STATUS_ENABLED);
            user1.setCreateTime(LocalDateTime.now());
            user1.setUpdateTime(LocalDateTime.now());
            user1.setDeleted(Constants.NOT_DELETED);
            userList.add(user1);

            User user2 = new User();
            user2.setUsername("user1");
            user2.setPassword("user123");
            user2.setNickname("普通用户");
            user2.setEmail("user1@example.com");
            user2.setPhone("13800138002");
            user2.setStatus(Constants.USER_STATUS_ENABLED);
            user2.setCreateTime(LocalDateTime.now());
            user2.setUpdateTime(LocalDateTime.now());
            user2.setDeleted(Constants.NOT_DELETED);
            userList.add(user2);

            // 批量插入用户数据
            userService.saveBatch(userList);
            log.info("用户数据初始化完成，共插入 {} 条数据", userList.size());
        }
    }
}


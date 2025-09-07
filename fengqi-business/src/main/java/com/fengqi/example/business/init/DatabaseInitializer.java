package com.fengqi.example.business.init;

import com.fengqi.example.business.entity.User;
import com.fengqi.example.business.service.UserService;
import com.fengqi.example.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** 
 * @Description 数据库初始化器类
 * 负责在应用启动时初始化数据库表结构和测试数据
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DatabaseInitializer implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("开始数据库初始化...");
        
        try {
            // 1. 先执行表结构初始化
            initializeTableStructure();
            
            // 2. 再执行数据初始化
            initializeData();
            
            log.info("数据库初始化完成");
        } catch (Exception e) {
            log.error("数据库初始化失败: {}", e.getMessage(), e);
            // 可以根据需要决定是否抛出异常中断启动
            // throw e;
        }
    }

    /**
     * 初始化表结构
     */
    private void initializeTableStructure() {
        log.info("开始检查表结构...");
        
        try {
            // 打印当前连接的数据库信息
            String dbName = jdbcTemplate.queryForObject("SELECT DATABASE()", String.class);
            log.info("当前连接的数据库: {}", dbName);
        } catch (Exception e) {
            log.warn("无法获取数据库名称: {}", e.getMessage());
        }
        
        // 检查t_user表是否存在
        boolean tableExists = checkTableExists("t_user");
        
        if (!tableExists) {
            log.info("t_user表不存在，开始创建...");
            createTableFromSchema();
            log.info("t_user表创建成功");
        } else {
            log.info("t_user表已存在，无需创建");
        }
    }

    /**
     * 检查表是否存在
     */
    private boolean checkTableExists(String tableName) {
        try {
            log.info("执行检查表是否存在的SQL: 表名 = {}", tableName);
            // 查询表是否存在
            String sql = "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?";
            log.debug("执行SQL: {}", sql);
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, tableName);
            log.info("表检查结果: 存在数量 = {}", count);
            return count != null && count > 0;
        } catch (Exception e) {
            log.error("检查表是否存在时出错: {}", e.getMessage(), e);
            // 如果查询出错，默认认为表不存在
            return false;
        }
    }

    /**
     * 从schema.sql文件中读取并执行建表语句
     */
    private void createTableFromSchema() {
        try {
            log.info("开始从schema.sql文件读取建表语句");
            // 读取schema.sql文件 - 使用ClassPathResource确保能正确加载
            org.springframework.core.io.Resource resource = new ClassPathResource("schema.sql");
            log.info("schema.sql文件是否存在: {}", resource.exists());
            
            if (!resource.exists()) {
                log.error("schema.sql文件不存在！");
                throw new RuntimeException("schema.sql文件不存在");
            }
            
            String sql = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            log.debug("读取到的建表SQL: {}", sql);
            
            // 执行建表语句
            log.info("开始执行建表语句");
            jdbcTemplate.execute(sql);
            log.info("建表语句执行成功");
        } catch (IOException e) {
            log.error("读取schema.sql文件时出错: {}", e.getMessage(), e);
            throw new RuntimeException("读取schema.sql文件失败", e);
        } catch (Exception e) {
            log.error("创建表时出错: {}", e.getMessage(), e);
            throw new RuntimeException("创建表失败", e);
        }
    }

    /**
     * 初始化测试数据
     */
    private void initializeData() {
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
        } else {
            log.info("用户表已有数据，无需初始化");
        }
    }
}
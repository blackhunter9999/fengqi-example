package com.fengqi.example.business.init;

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

/** 
 * @Description 表初始化器类
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TableInitializer implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
}
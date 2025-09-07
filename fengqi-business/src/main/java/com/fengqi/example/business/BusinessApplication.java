package com.fengqi.example.business;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/** 
 * @Description 应用程序入口类
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
@SpringBootApplication
@MapperScan("com.fengqi.example.business.mapper")
@ComponentScan({"com.fengqi.example.business", "com.fengqi.example.common"})
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}


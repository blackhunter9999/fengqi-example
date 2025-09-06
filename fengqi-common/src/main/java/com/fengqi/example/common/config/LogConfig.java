package com.fengqi.example.common.config;

import com.fengqi.example.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 日志配置类
 * 使用AOP记录请求和响应日志
 */
@Aspect
@Component
@Slf4j
public class LogConfig {

    /**
     * 定义切点，织入所有Controller方法
     */
    @Pointcut(Constants.CONTROLLER_POINTCUT_EXPRESSION)
    public void logPointCut() {
    }

    /**
     * 环绕通知，记录请求和响应日志
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // 记录请求信息（不依赖ServletRequestAttributes）
        log.info("请求类方法: {}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        log.info("请求参数: {}", Arrays.toString(point.getArgs()));

        // 执行方法
        Object result = point.proceed();

        // 记录响应信息
        log.info("响应结果: {}", result);
        log.info("请求耗时: {}ms", System.currentTimeMillis() - startTime);

        return result;
    }
}


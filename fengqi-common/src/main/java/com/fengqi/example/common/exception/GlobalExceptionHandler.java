package com.fengqi.example.common.exception;

import com.fengqi.example.common.result.Result;
import com.fengqi.example.common.result.ResultCode;
import com.fengqi.example.common.utils.I18nUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;

/**
 * 全局异常处理器
 */
@ControllerAdvice
@ResponseBody
@Slf4j
@Component
public class GlobalExceptionHandler {

    @Resource
    private I18nUtils i18nUtils;

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        log.error("业务异常: {}", e.getMessage(), e);
        // 检查异常消息是否是消息键
        if (e.getMessage() != null && e.getMessage().contains("_")) {
            // 如果是消息键，获取国际化消息
            String message = i18nUtils.getMessage(e.getMessage());
            return Result.fail(e.getCode(), message);
        }
        // 否则返回原始消息
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("参数异常: {}", e.getMessage(), e);
        return Result.fail(ResultCode.PARAM_ERROR);
    }

    /**
     * 处理所有未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return Result.fail(ResultCode.INTERNAL_SERVER_ERROR);
    }
}


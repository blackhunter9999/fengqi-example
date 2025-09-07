package com.fengqi.example.common.result;

import com.fengqi.example.common.utils.I18nUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/** 
 * @Description 统一结果返回类
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
@Data
@Slf4j
@Component
public class Result<T> implements Serializable, ApplicationContextAware {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    // 静态的I18nUtils实例
    private static I18nUtils i18nUtils;

    private Result() {
    }

    /**
     * 设置ApplicationContext，用于获取I18nUtils实例
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (i18nUtils == null) {
            i18nUtils = applicationContext.getBean(I18nUtils.class);
            log.info("I18nUtils已初始化: {}", i18nUtils);
        }
    }

    /**
     * 成功结果
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(getMessage(ResultCode.SUCCESS.getMessageKey()));
        result.setData(data);
        return result;
    }

    /**
     * 成功结果（自定义消息）
     */
    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 失败结果
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(getMessage(resultCode.getMessageKey()));
        return result;
    }

    /**
     * 失败结果（自定义消息）
     */
    public static <T> Result<T> fail(ResultCode resultCode, String message) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 失败结果（自定义状态码和消息）
     */
    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 通过消息键获取国际化消息
     * @param messageKey 消息键
     * @return 国际化消息
     */
    private static String getMessage(String messageKey) {
        if (i18nUtils != null) {
            return i18nUtils.getMessage(messageKey);
        }
        // 如果i18nUtils还未初始化，返回消息键
        return messageKey;
    }
}


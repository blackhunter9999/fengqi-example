package com.fengqi.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.Locale;

/**
 * 国际化工具类
 * 用于获取不同语言环境下的消息
 */
@Component
@Slf4j
public class I18nUtils {

    @Resource
    private MessageSource messageSource;

    /**
     * 获取当前语言环境下的消息
     * @param code 消息键
     * @return 国际化消息
     */
    public String getMessage(String code) {
        try {
            // 检查code是否为空
            if (code == null || code.isEmpty()) {
                log.warn("消息键为空");
                return ""; // 返回空字符串
            }
            
            // 获取当前语言环境的消息
            Locale locale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage(code, null, locale);
            return message;
        } catch (NoSuchMessageException e) {
            log.warn("未找到消息: code={}, 异常: {}", code, e.getMessage());
            
            // 尝试直接从配置文件中查找，忽略语言环境
            try {
                Locale defaultLocale = Locale.getDefault();
                String defaultMessage = messageSource.getMessage(code, null, defaultLocale);
                return defaultMessage;
            } catch (NoSuchMessageException ex) {
                log.warn("使用默认语言环境也未找到消息: code={}", code);
                // 如果找不到对应的消息，返回消息键
                return code;
            }
        }
    }

    /**
     * 获取指定语言环境下的消息
     * @param code 消息键
     * @param locale 语言环境
     * @return 国际化消息
     */
    public String getMessage(String code, Locale locale) {
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
            // 如果找不到对应的消息，返回消息键
            return code;
        }
    }

    /**
     * 获取当前语言环境下的带参数消息
     * @param code 消息键
     * @param args 消息参数
     * @return 国际化消息
     */
    public String getMessage(String code, Object[] args) {
        try {
            return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            // 如果找不到对应的消息，返回消息键
            return code;
        }
    }

    /**
     * 获取指定语言环境下的带参数消息
     * @param code 消息键
     * @param args 消息参数
     * @param locale 语言环境
     * @return 国际化消息
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        try {
            return messageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            // 如果找不到对应的消息，返回消息键
            return code;
        }
    }
}
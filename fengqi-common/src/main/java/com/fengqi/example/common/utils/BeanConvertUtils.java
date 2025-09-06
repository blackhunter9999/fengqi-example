package com.fengqi.example.common.utils;

import org.springframework.beans.BeanUtils;

/**
 * Bean转换工具类
 * 提供对象之间的属性复制和转换功能
 */
public class BeanConvertUtils {

    /**
     * 将源对象转换为目标对象
     * @param source 源对象
     * @param targetClass 目标对象类型
     * @param <S> 源对象类型
     * @param <T> 目标对象类型
     * @return 转换后的目标对象
     */
    public static <S, T> T convert(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("对象转换失败: " + e.getMessage(), e);
        }
    }

    /**
     * 将源对象转换为目标对象（使用已存在的目标对象）
     * @param source 源对象
     * @param target 目标对象
     * @param <S> 源对象类型
     * @param <T> 目标对象类型
     * @return 转换后的目标对象
     */
    public static <S, T> T convert(S source, T target) {
        if (source == null || target == null) {
            return target;
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
package com.fengqi.example.common.utils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合工具类
 * 提供集合相关的通用操作方法
 */
public class CollectionUtils {

    /**
     * 将源对象列表转换为目标对象列表
     * @param sourceList 源对象列表
     * @param mapper 转换函数
     * @param <S> 源对象类型
     * @param <T> 目标对象类型
     * @return 转换后的目标对象列表
     */
    public static <S, T> List<T> convertList(List<S> sourceList, Function<S, T> mapper) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * 判断集合是否为空
     * @param list 集合
     * @param <T> 集合元素类型
     * @return 如果集合为null或空返回true，否则返回false
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    /**
     * 判断集合是否不为空
     * @param list 集合
     * @param <T> 集合元素类型
     * @return 如果集合不为null且不为空返回true，否则返回false
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }
}
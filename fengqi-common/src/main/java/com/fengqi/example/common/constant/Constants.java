package com.fengqi.example.common.constant;

/** 
 * @Description 系统常量类
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
public class Constants {

    // ===================== HTTP状态码 =====================
    /**
     * 请求成功
     */
    public static final Integer HTTP_STATUS_SUCCESS = 200;

    /**
     * 参数错误
     */
    public static final Integer HTTP_STATUS_BAD_REQUEST = 400;

    /**
     * 未授权
     */
    public static final Integer HTTP_STATUS_UNAUTHORIZED = 401;

    /**
     * 拒绝访问
     */
    public static final Integer HTTP_STATUS_FORBIDDEN = 403;

    /**
     * 资源不存在
     */
    public static final Integer HTTP_STATUS_NOT_FOUND = 404;

    /**
     * 服务器内部错误
     */
    public static final Integer HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    // ===================== 用户状态 =====================
    /**
     * 用户状态-启用
     */
    public static final Integer USER_STATUS_ENABLED = 1;

    /**
     * 用户状态-禁用
     */
    public static final Integer USER_STATUS_DISABLED = 0;

    // ===================== 逻辑删除标识 =====================
    /**
     * 未删除
     */
    public static final Integer NOT_DELETED = 0;

    /**
     * 已删除
     */
    public static final Integer DELETED = 1;

    // ===================== API路径 =====================
    /**
     * 用户API前缀
     */
    public static final String API_USER_PREFIX = "/api/user";

    // ===================== 国际化参数名 =====================
    /**
     * 语言参数名
     */
    public static final String LANG_PARAM_NAME = "lang";

    // ===================== 日志切面表达式 =====================
    /**
     * 控制器切点表达式
     */
    public static final String CONTROLLER_POINTCUT_EXPRESSION = "execution(* com.fengqi.example.business.controller.*.*(..))";

    // ===================== 消息键 =====================
    /**
     * 用户不存在消息
     */
    public static final String MSG_USER_NOT_EXIST = "user_not_exist";
}


package com.fengqi.example.common.result;

/** 
 * @Description 结果状态码枚举
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
public enum ResultCode {
    // 成功状态码
    SUCCESS(200, "success"),

    // 失败状态码
    FAIL(10001, "fail"),
    PARAM_ERROR(400, "param_error"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not_found"),
    INTERNAL_SERVER_ERROR(500, "internal_server_error"),
    // 用户相关错误
    USER_NOT_EXIST(10002, "user_not_exist");

    private final Integer code;
    private final String messageKey;

    ResultCode(Integer code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * 获取消息键
     * @return 消息键
     */
    public String getMessageKey() {
        return messageKey;
    }
}


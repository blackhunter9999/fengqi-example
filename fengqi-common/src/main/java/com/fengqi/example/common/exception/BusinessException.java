package com.fengqi.example.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
 * @Description 自定义业务异常
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private Integer code;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.code = 500;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}


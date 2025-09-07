package com.fengqi.example.business.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/** 
 * @Description 用户视图对象
 * @Author blackhunter 
 * @Date 2025-09-07 
 * @Version 1.0 
 **/
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态：0禁用，1启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
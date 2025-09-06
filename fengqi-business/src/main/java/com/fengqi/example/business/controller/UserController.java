package com.fengqi.example.business.controller;

import com.fengqi.example.business.entity.User;
import com.fengqi.example.business.service.UserService;
import com.fengqi.example.business.vo.UserVO;
import com.fengqi.example.common.result.Result;
import com.fengqi.example.common.result.ResultCode;
import com.fengqi.example.common.utils.BeanConvertUtils;
import com.fengqi.example.common.utils.CollectionUtils;
import com.fengqi.example.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制器
 * 提供用户相关的HTTP接口
 */
@RestController
@RequestMapping(Constants.API_USER_PREFIX)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @return 用户VO列表
     */
    @GetMapping("/list")
    public Result<List<UserVO>> getUserList() {
        List<User> userList = userService.list();
        // 将User列表转换为UserVO列表
        List<UserVO> userVOList = CollectionUtils.convertList(userList, user -> BeanConvertUtils.convert(user, UserVO.class));
        return Result.success(userVOList);
    }

    /**
     * 根据ID获取用户详情
     * @param id 用户ID
     * @return 用户VO详情
     */
    @GetMapping("/{id}")
    public Result<UserVO> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            // 使用USER_NOT_EXIST状态码，这样会自动通过国际化机制获取消息
            return Result.fail(ResultCode.USER_NOT_EXIST);
        }
        // 将User对象转换为UserVO对象
        UserVO userVO = BeanConvertUtils.convert(user, UserVO.class);
        return Result.success(userVO);
    }
}


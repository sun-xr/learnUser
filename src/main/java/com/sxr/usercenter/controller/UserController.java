/**
 * @packageName com.sxr.usercenter.controller
 * @className UserController
 * @description TODO
 * @version 1.0
 * @author sxr
 * @date 2024/12/6 下午7:02
 */
package com.sxr.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sxr.usercenter.commonClass.BaseResponse;
import com.sxr.usercenter.commonClass.ErrorCode;
import com.sxr.usercenter.commonClass.ResultUtils;
import com.sxr.usercenter.exception.BusinessException;
import com.sxr.usercenter.model.domain.User;
import com.sxr.usercenter.model.domain.request.UserLoginRequest;
import com.sxr.usercenter.model.domain.request.UserRegisterRequest;
import com.sxr.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sxr.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.sxr.usercenter.contant.UserConstant.USER_LOGIN_STATE;
// 适用于编写restful风格的api, 返回值默认为json类型

/**
 * @Title: 用户接口
 * @Author: sxr
 * @Date: 2024-12-06 19:04:24
 * @Params:
 * @Return: null
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            // 不够优雅，在exception包里
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR );
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        // 对请求数据本身的校验，不涉及业务逻辑本身（越少越好）
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
//        return new BaseResponse<Long>(0,result,"ok");
        return ResultUtils.success(result);
    }


    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        // 对请求数据本身的校验，不涉及业务逻辑本身（越少越好）
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
//        return
        User user = userService.userLogin(userAccount, userPassword, request);

        return ResultUtils.success(user);
    }


    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            List<User> objects = new ArrayList<>();
            // todo
            return ResultUtils.success(objects);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> collect = userList.stream().map(user ->
                userService.getSafetyUser(user)
        ).collect(Collectors.toList());
        return ResultUtils.success(collect);
    }

    /**
     * 获取当前用户
     *
     * @param request
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            return null;
        }
        long userId = currentUser.getId();
        // todo 校验用户是否合法
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }


    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUsers(@RequestBody long id, HttpServletRequest request) {

        if (!isAdmin(request)) {
            return null;
        }
        if (id <= 0) {
            return null;
        }
        // 逻辑删除
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);

    }

    /**
     * @Title: isAdmin
     * @Author: sxr
     * @Date: 2024-12-07 09:00:57
     * @Params: [request]
     * @Return: boolean
     * @Description: 是否为管理员
     */

    private boolean isAdmin(HttpServletRequest request) {

        //        // 仅管理员可查询
//        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
//        User user = (User) userObj;
//        if (user == null || user.getRole() != ADMIN_ROLE) {
//            return false;
//        }
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getUserRole() == ADMIN_ROLE;

    }
}

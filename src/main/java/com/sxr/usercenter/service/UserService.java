package com.sxr.usercenter.service;

import com.sxr.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sxr
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-12-06 09:50:28
 */
public interface UserService extends IService<User> {


    /**
     * @Title: userRegister
     * @Author: sxr
     * @Date: 2024-12-06 15:15:05
     * @Params: [userAccount, userPassword, checkPassword，planetCode]
     * @Return: long 新用户
     * @Description:
     */
    long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode);


    /**
     * @Title: doLogin
     * @Author: sxr
     * @Date: 2024-12-06 18:38:42
     * @Params: [userAccount, userPassword, request]
     * @Return: User
     * @Description:
     */

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * @Title: getSafetyUser
     * @Author: sxr
     * @Date: 2024-12-07 09:37:38
     * @Params: [originUser]
     * @Return: User
     * @Description: 用户脱敏
     */

    User getSafetyUser(User originUser);

    int userLogout(HttpServletRequest request);
}




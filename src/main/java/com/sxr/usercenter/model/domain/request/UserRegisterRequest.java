/**
 * @packageName com.sxr.usercenter.model.domain.request
 * @className UserLoginRequest
 * @description 用户注册请求体
 * @version 1.0
 * @author sxr
 * @date 2024/12/6 下午7:09
 */

package com.sxr.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = -3995004557838608706L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}


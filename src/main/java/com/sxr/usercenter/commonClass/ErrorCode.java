/**
 * @packageName com.sxr.usercenter.commonClass
 * @className ErrorCode
 * @description TODO
 * @version 1.0
 * @author sxr
 * @date 2024/12/8 下午1:39
 */

package com.sxr.usercenter.commonClass;

import org.springframework.aop.scope.ScopedObject;

public enum ErrorCode {
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "数据为空", ""),
    NO_LOGIN(40100, "未登录", ""),
    NO_AUfTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000,"系统内部异常","");
    private int code;
    private String message;
    private String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
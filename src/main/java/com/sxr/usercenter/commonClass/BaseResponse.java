/**
 * @packageName com.sxr.usercenter.commonClass
 * @className BaseResponse
 * @description TODO
 * @version 1.0
 * @author sxr
 * @date 2024/12/8 上午10:47
 */

package com.sxr.usercenter.commonClass;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 通用返回类
 * @Author: sxr
 * @Date: 2024-12-08 10:51:30
 * @Params:
 * @Return: null
 * @Description:
 */

@Data
public class BaseResponse<T> implements Serializable {
    private int code;
    private T data;
    private String message;
    private String description;

    public BaseResponse(int code, T data, String msg, String description) {
        this.code = code;
        this.data = data;
        this.message = msg;
        this.description = description;

    }


    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }
    public BaseResponse(int code, T data) {
        this(code, data,  "","");
    }

    public BaseResponse(ErrorCode errorCode) {

        this(errorCode.getCode(), null, errorCode.getDescription());
    }


}

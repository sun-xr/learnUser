/**
 * @packageName com.sxr.usercenter.commonClass
 * @className ResultUtils
 * @description TODO
 * @version 1.0
 * @author sxr
 * @date 2024/12/8 上午11:06
 */

package com.sxr.usercenter.commonClass;

import lombok.extern.slf4j.Slf4j;


public class ResultUtils {
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(0, data, "ok");
    }

    /**
     * @Title: error
     * @Author: sxr
     * @Date: 2024-12-08 15:19:46
     * @Params: [errorCode]
     * @Return: BaseResponse
     * @Description:
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }


    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse<>(code,null, message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(), message, description);
    }

    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(),null, errorCode.getMessage(), description);
    }

}

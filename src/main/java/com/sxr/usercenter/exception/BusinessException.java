/**
 * @packageName com.sxr.usercenter.exception
 * @className BusinessException
 * @description TODO
 * @version 1.0
 * @author sxr
 * @date 2024/12/8 下午1:58
 */

package com.sxr.usercenter.exception;

import com.sxr.usercenter.commonClass.ErrorCode;

public class BusinessException extends RuntimeException {

    private final int code;
    private final String description;


    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}

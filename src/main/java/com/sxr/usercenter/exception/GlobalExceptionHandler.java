/**
 * @packageName com.sxr.usercenter.exception
 * @className GlobalExceptionHandler
 * @description TODO
 * @version 1.0
 * @author sxr
 * @date 2024/12/8 下午2:28
 */

package com.sxr.usercenter.exception;

import com.sxr.usercenter.commonClass.BaseResponse;
import com.sxr.usercenter.commonClass.ErrorCode;
import com.sxr.usercenter.commonClass.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
* @Title: 全局异常处理器
* @Author: sxr
* @Date: 2024-12-08 15:42:25
* @Params:
* @Return: null
* @Description:
*
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException e) {
        log.error("businessException"+e.getMessage(), e);
        return ResultUtils.error(e.getCode(),e.getMessage(),"");
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(),"");

    }

}

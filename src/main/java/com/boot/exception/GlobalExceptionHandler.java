package com.boot.exception;

import com.boot.enums.ErrorEnum;
import com.boot.util.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ExceptionHandler
 * @Description 全局异常处理
 * @Date 2020/6/19 5:12 下午
 * @Created by hly
 */
@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public AjaxResult bizExceptionHandler(BusinessException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.defineError(e);
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public AjaxResult exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.otherError(ErrorEnum.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public AjaxResult handleException(RuntimeException e) {
        log.info("拦截器认证错误，信息如下：" + e.getMessage());
//        return new ResultModel().error(e.getMessage());
        return AjaxResult.otherAjaxResult(false, ErrorEnum.INTERNAL_SERVER_ERROR.getErrorCode(), e.getMessage());
    }
}

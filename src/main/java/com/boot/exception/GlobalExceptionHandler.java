package com.boot.exception;

import com.boot.common.ResultModel;
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

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultModel handleException(RuntimeException e) {
        log.info("拦截器认证错误，信息如下：" + e.getMessage());
        return new ResultModel().error(e.getMessage());
    }
}
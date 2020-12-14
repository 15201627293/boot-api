package com.boot.aspect;

import com.alibaba.fastjson.JSON;
import com.boot.util.IpUtil;
import com.boot.util.LocalDateTimeUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Classname ControllerAspect
 * @Description TODO
 * @Date 2020/6/22 2:47 下午
 * @Created by hly
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {
    /**
     * 进入方法时间戳
     */
    private Long startTime;
    /**
     * 方法结束时间戳(计时)
     */
    private Long endTime;

    public ControllerAspect() {
    }

    /**
     * 自定义切点
     */
    private final String POINTCUT = "execution(* com.boot.controller..*(..))";

    /**
     * 前置通知，方法之前执行
     *
     * @param joinPoint
     */
    @Before(POINTCUT)
    public void doBefore(JoinPoint joinPoint) {
        // 获取当前的HttpServletRequest对象
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求头中的User-Agent
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 打印请求的内容
        startTime = System.currentTimeMillis();
        log.info("请求开始时间：{}", LocalDateTimeUtils.getLocalDateTime(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss:SSS"));
        log.info("请求Url : {}", request.getRequestURL().toString());
        log.info("请求方式 : {}", request.getMethod());
        log.info("请求ip : {}", IpUtil.getIp(request));
        log.info("请求内容类型 : {}", request.getContentType());
        log.info("请求参数 : {}", Arrays.toString(joinPoint.getArgs()));
        // 系统信息
        log.info("浏览器 : {}", userAgent.getBrowser().toString());
        log.info("浏览器版本 : {}", userAgent.getBrowserVersion());
        log.info("操作系统: {}", userAgent.getOperatingSystem().toString());
    }

    @After(POINTCUT)
    public void doAfter(JoinPoint joinPoint) {
//    System.out.println("doAfter");
    }

    /**
     * 返回通知 正常结束时进入此方法
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = POINTCUT)
    public void doAfterReturning(Object ret) {
        endTime = System.currentTimeMillis();
        log.info("请求结束时间 : {}", LocalDateTimeUtils.getLocalDateTime(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss:SSS"));
        log.info("请求耗时 : {}", (endTime - startTime));
        // 处理完请求，返回内容
        log.info("请求返回 : {}", JSON.toJSONString(ret));
    }

    /**
     * 异常通知： 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     *
     * @param throwable
     */
    @AfterThrowing(pointcut = POINTCUT, throwing = "throwable")
    public void doAfterThrowing(Throwable throwable) {
        // 保存异常日志记录
        log.error("发生异常时间 : {}", LocalDateTime.now());
        log.error("抛出异常 : {}", throwable.getMessage());
    }

}

package com.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Classname LogConfig
 * @Description 日志配置
 * @Date 2020/6/13 12:03 下午
 * @Created by hly
 */
@Controller
@Configuration
public class LogConfig {
    private static final Logger LOG = LoggerFactory.getLogger(LogConfig.class);

    @Bean
    public void logMethod() {
        LOG.info("==========print log==========");
    }

    /**
     * 获取日志对象，构造函数传入当前类，查找日志方便定位
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${log.home}")
    private String logUrl;

    /**
     * 端口
     */
    @Value("${server.port}")
    private String port;

    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> {
            try {
                InetAddress ia = InetAddress.getLocalHost();
                //获取本机内网IP
                log.info("启动成功：" + "http://" + ia.getHostAddress() + ":" + port + "/");
                log.info("启动成功：" + "http://" + ia.getHostAddress() + ":" + port + "/doc.html");
                log.info("启动成功：" + "http://" + ia.getHostAddress() + ":" + port + "/swagger-ui.html");
                log.info("${log.home} ：" + logUrl);
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
            }
        };
    }
}

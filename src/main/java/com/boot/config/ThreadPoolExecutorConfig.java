package com.boot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname ThreadPoolExecutorConfig
 * @Description 异步任务线程池
 * @Date 2021/3/18 6:08 下午
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutorConfig {

    @Bean
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(16);
        //最大线程数
        executor.setMaxPoolSize(32);
        //队列容量
        executor.setQueueCapacity(5000);
        //活跃时间
        executor.setKeepAliveSeconds(60);
        //线程名字前缀
        executor.setThreadNamePrefix("asyncTaskExecutor-");
        executor.setThreadGroupName("asyncTaskExecutorGroup-");
        executor.setWaitForTasksToCompleteOnShutdown(true);

        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
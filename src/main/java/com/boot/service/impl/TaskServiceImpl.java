package com.boot.service.impl;

import com.boot.service.TaskService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Classname TaskServiceImpl
 * @Description TODO
 * @Date 2021/3/22 11:01 上午
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);



    public void task(List<T> list) {

    }

    @Override
    @Async
    public Future<String> task(List<T> list, Integer renovateBatch) {
        log.info("[数据处理] [第{}批次] [开始] [待更新数 = {}]", renovateBatch, list.size());
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            //逻辑处理
            log.info("[更新 第{}批次 第{}个数据, 手机号为:{}]", renovateBatch, i + 1, customerOwner.getMobile());
        }
        Long time = System.currentTimeMillis() - begin;
        System.out.println("车主发放批次");
        log.info("[数据处理] [第{}批次] [结束] [更新数 = {}], [更新耗时 = {} 秒]", renovateBatch, customerOwners.size(), time / 1000);
        return new AsyncResult<>("[数据处理][第"+renovateBatch+"批次] [结束]");
    }
}

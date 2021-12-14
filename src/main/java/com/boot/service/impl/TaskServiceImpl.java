package com.boot.service.impl;

import com.boot.service.TaskService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Classname TaskServiceImpl
 * @Description TODO
 * @Date 2021/3/22 11:01 上午
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Override
    @Async
    public Future<String> task(List<T> list, Integer renovateBatch) {
        log.info("[数据处理] [第{}批次] [开始] [待更新数 = {}]", renovateBatch, list.size());
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            //逻辑处理
            log.info("[更新 第{}批次 第{}个数据]", renovateBatch, i + 1);
        }
        Long time = System.currentTimeMillis() - begin;
        System.out.println("车主发放批次");
        log.info("[数据处理] [第{}批次] [结束] [更新数 = {}], [更新耗时 = {} 秒]", renovateBatch, list.size(), time / 1000);
        return new AsyncResult<>("[数据处理][第" + renovateBatch + "批次] [结束]");
    }

    //https://so.csdn.net/so/search/blog?q=%E5%BC%82%E6%AD%A5&t=blog&p=1&s=0&tm=0&lv=-1&ft=0&l=&u=u012839098
    // 异步处理数据 可用在接口逻辑复杂或者接口处理耗时长
    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }
    public Future<Double> getPriceAsync1(String product) {
        //创建CompletableFuture 对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //在另一个线程中以异步方式执行计算
        new Thread(() -> {
            double price = calculatePrice(product);
            //需长时间计算的任务结 束并得出结果时，设置 Future的返回值
            futurePrice.complete(price);
        }).start();
        // 无需等待还没结束的计算，直接返回Future对象
        return futurePrice;
    }

    private double calculatePrice(String product) {
        //一个模拟的延迟方法
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        int delay = 1000;
        //int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

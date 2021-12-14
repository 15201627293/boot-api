package com.boot.controller;

import com.boot.service.TaskService;
import io.swagger.annotations.Api;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ThreadController
 * @Description TODO
 * @Date 2021/3/22 10:39 上午
 */
@Api(tags = "异步多线程接口相关")
@RequestMapping("/redis")
@RestController
public class ThreadController {

    private static final Logger log = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/taskTest", method = RequestMethod.GET)
    @ResponseBody
    public String taskTest() {
        List<List<T>> list = new ArrayList<>();
        // 获取总条数
        Long totalSize = 0L;
        // 总页数
        Long totalPage = 0L;
        // 每页条数
        Integer pageSize = 5000;
        // 目前更新批次数
        Integer renovateBatch = 0;
        if (totalSize > 0) {
            totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
            for (int i = 0; i < totalPage; i++) {
                renovateBatch++;
                Long begin = System.currentTimeMillis();
                log.info("[数据查询] [当前批次:{}] [总批次:{}]", renovateBatch, totalPage);
                List<T> tList = null;
                Long time = System.currentTimeMillis() - begin;
                log.info("[数据查询] [当前批次:{}] [开始] [待更新数 = {}], [查询耗时 = {} 秒]", renovateBatch, tList.size(), time / 1000);
                list.add(tList);
            }
        }
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                //数据处理
                taskService.task(list.get(i), i++);
            }
        }
        return "success";
    }


}

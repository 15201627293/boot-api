package com.boot.service;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Classname TaskService
 * @Description TODO
 * @Date 2021/3/22 11:01 上午
 */
public interface TaskService {

    Future<String> task(List<T> list, Integer renovateBatch);
}

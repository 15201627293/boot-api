package com.boot.base;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Classname BaseDao
 * @Description TODO
 * @Date 2020/6/18 10:04 上午
 * @Created by hly
 */
@RegisterMapper
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {

}

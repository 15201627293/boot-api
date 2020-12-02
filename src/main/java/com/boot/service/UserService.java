package com.boot.service;

import com.boot.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-06-18 09:58:32
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);


    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 用户详情
     * @param user
     * @return
     */
    User detail(User user) ;

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 密码获取
     * @param pwd 密码
     * @param salt 盐
     * @return
     */
    String getPwd(String pwd, String salt);
}
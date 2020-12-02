package com.boot.entity;

import com.boot.base.BaseEntity;
import lombok.Data;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-06-18 09:58:29
 */
@Data
public class User extends BaseEntity {

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 盐
     */
    private String salt;

    /**
     * 性别 1男 2女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String headUrl;
}
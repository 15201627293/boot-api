package com.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.entity.User;
import com.boot.service.UserService;
import com.boot.mapper.UserMapper;
import com.boot.util.PassWordUtil;
import com.boot.util.SaltUtil;
import com.boot.util.SnowIdUtils;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-06-18 10:17:07
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${default.pwd}")
    private String defaultPwd;
    @Value("${pwd.length}")
    private Integer pwdLength;
    @Value("${salt.length}")
    private Integer saltLength;

    @Resource
    private UserMapper userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return userDao.selectById(id);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<User> queryAll(User user) {
        return userDao.queryAll(user);
    }

    /**
     * 用户详情
     *
     * @param user
     * @return
     */
    @Override
    public User detail(User user) {
        return userDao.detail(user);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        String salt = SaltUtil.getRandomString(saltLength);
        String pwd = getPwd(user.getPassWord(), salt);
        user.setId(SnowIdUtils.uniqueLong());
        user.setPassWord(pwd);
        user.setSalt(salt);
        user.setCreateTime(new Date());
        userDao.insert(user);
        return user;
    }

    /**
     * 密码获取
     * @param pwd 密码
     * @param salt 盐
     * @return
     */
    @Override
    public String getPwd(String pwd, String salt) {
        String password = "";
        if (StringUtil.isBlank(pwd)) {
            password = pwd.length() == pwdLength ? pwd : DigestUtils.md5DigestAsHex(pwd.getBytes());
        }else{
            password = DigestUtils.md5DigestAsHex(defaultPwd.getBytes());
        }
        String userPwd = PassWordUtil.sha256((salt + password).getBytes());
        return userPwd;
    }
}
package com.boot.controller;

import com.boot.common.ResultModel;
import com.boot.entity.User;
import com.boot.util.RedisUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname RedisController
 * @Description TODO
 * @Date 2020/9/1 11:10 上午
 */
@Api(tags = "redis用户相关接口")
@RequestMapping("/redis")
@RestController
public class RedisController {


    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisUtil redisUtil;
    
    @ApiOperation(value = "redis用户列表接口")
    @GetMapping("/list")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥")})
    public ResultModel list( @ApiParam(value = "from") @RequestParam(value = "from", defaultValue = "1") String from) {
        PageInfo<User> pageInfo = (PageInfo<User>)redisUtil.get("pageInfo");
        return new ResultModel().success(pageInfo);
    }

    @ApiOperation(value = "redis用户详情接口")
    @GetMapping("/detail")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥")})
    public ResultModel detail(@ApiParam(value = "from") @RequestParam(value = "from", defaultValue = "1") String from) {
        User user = (User)redisUtil.get("user");
        return new ResultModel().success(user);
    }
}

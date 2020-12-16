package com.boot.controller;

import com.boot.dto.UserDto;
import com.boot.entity.User;
import com.boot.excel.ExcelToolUtil;
import com.boot.excel.UserImportListener;
import com.boot.excel.UserModel;
import com.boot.excel.UserModelUtil;
import com.boot.service.UserService;
import com.boot.common.ResultModel;
import com.boot.util.PassWordUtil;
import com.boot.util.RedisUtil;
import com.boot.util.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Classname UsersController
 * @Description TODO
 * @Date 2020/6/13 6:49 下午
 * @Created by hly
 */
@Api(tags = "用户相关接口", produces = "提供用户相关的 Rest API")
@RequestMapping("/user")
@RestController
public class UsersController {

    private Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;


    @ApiOperation(value = "用户列表接口")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥"),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", defaultValue = "20")
    })
    public ResultModel list(
            @ApiParam(value = "当前页", required = true) @RequestParam(value = "pageNo", defaultValue = "1", required = true) Integer pageNo,
            @ApiParam(value = "每页条数", required = true) @RequestParam(value = "pageSize", defaultValue = "20", required = true) Integer pageSize,
            @ApiParam(value = "from") @RequestParam(value = "from", defaultValue = "1") String from) {
        PageHelper.startPage(pageNo, pageSize);
        User user = new User();
        List<User> users = userService.queryAll(user);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        redisUtil.set("pageInfo", pageInfo);
        return new ResultModel().success(pageInfo);
    }

    @ApiOperation(value = "用户登录接口")
    @PostMapping("/login")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥")})
    public ResultModel login(
            @ApiParam(value = "用户名", required = true) @RequestParam(value = "userName", required = true) String userName,
            @ApiParam(value = "密码", required = true) @RequestParam(value = "passWord", required = true) String passWord) {
        User queryUser = new User();
        queryUser.setUserName(userName);
        User user = userService.detail(queryUser);
        passWord = passWord.length() == 32 ? passWord : DigestUtils.md5DigestAsHex(passWord.getBytes());
        if (null == user) {
            return new ResultModel().error("用户名不存在");
        }
        String pwd = userService.getPwd(passWord, user.getSalt());
        if (!PassWordUtil.equals(pwd, user.getPassWord())) {
            return new ResultModel().error("用户名或密码错误");
        }
        redisUtil.set("user_info_" + user.getId(), user, 1800);
        Map<String, Object> map = TokenUtil.accessToken(user);
        return new ResultModel().success(map);
    }


    @ApiOperation(value = "用户注册接口")
    @PostMapping("/register")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥")})
    public ResultModel register(@RequestBody UserDto userDto) {
        User queryUser = new User();
        queryUser.setUserName(userDto.getUserName());
        User detail = userService.detail(queryUser);
        if (null != detail) {
            return new ResultModel().error("用户名已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userService.insert(user);
        redisUtil.set("user_info_" + user.getId(), user, 1800);
        Map<String, Object> map = TokenUtil.accessToken(user);
        return new ResultModel().success(map);
    }

    @ApiOperation(value = "用户详情接口")
    @GetMapping("/detail")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥"),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization"),
            @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "00")
    })
    public ResultModel detail(
            @ApiParam(value = "用户ID", required = true) @RequestParam(value = "id", defaultValue = "null", required = true) Long id,
            @ApiParam(value = "from") @RequestParam(value = "from", defaultValue = "1") String from) {
        User user = userService.queryById(id);
        redisUtil.set("user", user);
        return new ResultModel().success(user);
    }


    @ApiOperation(value = "用户导入接口")
    @PostMapping("/import")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥"),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization"),
    })
    public void excelImport(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            @ApiParam(value = "from") @RequestParam(value = "from", defaultValue = "1") String from) throws IOException {
        // 批量发放，根据文件用户信息
        ExcelToolUtil.readExcel(file.getInputStream(), UserModel.class, new UserImportListener(userService));
    }

    @ApiOperation(value = "用户导出接口")
    @GetMapping("/export")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "sercet", value = "密钥"),
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization"),
    })
    public void excelExport(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(value = "from") @RequestParam(value = "from", defaultValue = "1") String from) {
        User user = new User();
        List<User> users = userService.queryAll(user);
        redisUtil.set("user", user);
        // 导出积分事件触发记录列表
        ExcelToolUtil.writeExcel(request, response, UserModelUtil.convertToExcelModel(users), UserModel.class);
    }
}

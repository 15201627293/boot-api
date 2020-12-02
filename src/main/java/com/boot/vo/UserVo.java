package com.boot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname UserVo
 * @Description TODO
 * @Date 2020/12/2 1:57 下午
 */
@Data
@ApiModel("user")
public class UserVo implements Serializable {

    @ApiModelProperty(value = "用户名称")
    private String name;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "用户性别 1:男 2:女", example = "00")
    private Integer sex;

    @ApiModelProperty(value = "用户头像")
    private String headUrl;
}
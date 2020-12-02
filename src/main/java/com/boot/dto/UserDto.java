package com.boot.dto;

import com.boot.base.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname UserDto
 * @Description TODO
 * @Date 2020/6/18 11:23 上午
 * @Created by hly
 */

@Data
@ApiModel("user")
public class UserDto extends BaseDTO {

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

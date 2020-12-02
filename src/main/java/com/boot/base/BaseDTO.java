package com.boot.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname BaseDTO
 * @Description TODO
 * @Date 2020/6/18 4:17 下午
 * @Created by hly
 */
@Data
@ApiModel("BaseDTO")
public class BaseDTO {

    @ApiModelProperty(value = "用户ID", example = "00")
    private Long id;

    /**
     * 所属平台 from 1-test 2-dev 3-prod
     */
    @ApiModelProperty(value = "所属平台 from 1-test 2-dev 3-prod", example = "1")
    private String from;
}

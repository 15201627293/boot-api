package com.boot.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Classname ResultModel
 * @Description TODO
 * @Date 2020/6/13 7:01 下午
 * @Created by hly
 */
@ApiModel("通用接口返回对象")
public class ResultModel<T> {

    @ApiModelProperty(required = true, notes = "结果码", example = "200")
    private int status;
    @ApiModelProperty(required = true, notes = "时间戳", example = "1567425139000")
    private long time;
    @ApiModelProperty(required = true, notes = "返回信息", example = "SUCCESS")
    private String message;
    @ApiModelProperty(required = true, notes = "返回数据", example = "{\"name\":\"blues\"}")
    private T content;


    public ResultModel<T> success(T content) {
        this.status = 200;
        this.message = "SUCCESS";
        this.content = content;
        this.time = System.currentTimeMillis();
        return this;
    }

    public ResultModel<T> success() {
        this.status = 200;
        this.message = "SUCCESS";
        this.time = System.currentTimeMillis();
        return this;
    }

    public ResultModel<T> error(String msg) {
        this.status = -1;
        this.message = msg;
        this.time = System.currentTimeMillis();
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}

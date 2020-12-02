package com.boot.common;

import com.boot.constant.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class ResResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String msg = "操作成功！";

    /**
     * 返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T data;


    public ResResult() {

    }

    public ResResult<T> success() {
        ResResult<T> r = new ResResult<T>();
        r.setCode(ErrorEnum.SUCCESS.getHttpCode());
        r.setMsg(ErrorEnum.SUCCESS.getErrMsg());
        return r;
    }

    public ResResult<T> success(String msg) {
        ResResult<T> r = new ResResult<T>();
        r.setCode(ErrorEnum.SUCCESS.getHttpCode());
        r.setMsg(msg);
        return r;
    }


    public <T> ResResult<T> success(T data) {
        ResResult<T> r = new ResResult<T>();
        r.setCode(ErrorEnum.SUCCESS.getHttpCode());
        r.setData(data);
        return r;
    }

    public ResResult<T> error(String msg) {
        return error(ErrorEnum.InternalSystemError.getHttpCode(), msg);
    }

    public ResResult<T> error(int code, String msg) {
        ResResult<T> r = new ResResult<T>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    /**
     * 无权限访问返回结果
     */
    public ResResult<T> noAuth(String msg) {
        return error(ErrorEnum.NoAuth.getHttpCode(), msg);
    }
}

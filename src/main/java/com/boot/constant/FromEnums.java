package com.boot.constant;

/**
 * @Classname FromEnums
 * @Description TODO
 * @Date 2020/6/18 3:48 下午
 * @Created by hly
 */
public enum FromEnums {

    TEST("1", "测试"),
    DEV("2", "开发"),
    PROD("3", "生产");

    private String code;

    private String msg;

    FromEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

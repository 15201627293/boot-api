package com.boot.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

/**
 * @Classname UserMedol
 * @Description TODO
 * @Date 2020/9/14 2:15 下午
 */
@Data
@ExcelModel("用户信息")
@ContentRowHeight(18)
@HeadRowHeight(20)
@ColumnWidth(15)
public class UserModel {

    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "用户名", index = 1)
    private String userName;

    @ExcelProperty(value = "性别", index = 2)
    private String sex;

    @ExcelProperty(value = "注册时间", index = 3)
    private String createTime;
}
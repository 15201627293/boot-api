package com.boot.excel.model;

import java.lang.annotation.*;

/**
 * @Classname ExcelModel
 * @Description excel注解
 * @Date 2020/9/14 11:17 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelModel {
    /**
     * 模块名，用于导出时的文件名
     *
     * @return String
     */
    String value() default "";

    /**
     * 页名
     *
     * @return String
     */
    String[] sheets() default {"sheet1"};
}

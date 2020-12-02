package com.boot.util;

import java.io.File;

/**
 * @Classname PathUtil
 * @Description TODO
 * @Date 2020/7/13 2:07 下午
 * @Created by hly
 */
public class PathUtil {

    /*
     * 获取classpath
     */
    public static String getClasspath() {
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(":") != 1) {
            path = File.separator + path;
        }
        return path;
    }
}

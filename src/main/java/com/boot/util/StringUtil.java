package com.boot.util;

/**
 * @Classname StringUtil
 * @Description TODO
 * @Date 2020/9/1 3:42 下午
 */
public class StringUtil {

    public static boolean nullity(String param) {
        return (param == null) || (param.length() <= 0 || param.equals("null") || param.equals("(null)"));
    }
}

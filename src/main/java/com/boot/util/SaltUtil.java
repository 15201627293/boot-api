package com.boot.util;

import java.util.Random;

/**
 * @Classname Md5SaltUtil
 * @Description TODO
 * @Date 2020/9/1 2:11 下午
 */
public class SaltUtil {

    public static String getRandomString(Integer length) { // length表示生成字符串的长度
        StringBuffer base = new StringBuffer("abcdefghijklmnopqrstuvwxyz0123456789");
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomNum(Integer length) {
        StringBuffer base = new StringBuffer("0123456789");
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(base.charAt(random.nextInt(base.length())));
        }
        return sb.toString();
    }
}

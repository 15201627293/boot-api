package com.boot;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.List;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2020/8/13 2:29 下午
 */
public class Test {


    public static void main(String[] args) {
//        cloneByStream();
//        toStr();
//        toIntArray();
//        toIntArray2();
//        toDate();
//        toList();
//        toSBC();
//        toDBC();
//        toHex();
//        hexToStr();
        strToUnicode();
        unicodeToStr();
    }





    public static void strToUnicode() {
        String a = "我是一个小小的可爱的字符串";
        //结果为："\\u6211\\u662f\\u4e00\\u4e2a\\u5c0f\\u5c0f\\u7684\\u53ef\\u7231\\u7684\\u5b57\\u7b26\\u4e32"
        String unicode = Convert.strToUnicode(a);
        System.out.println(JSON.toJSONString(unicode));
    }
    public static void unicodeToStr() {
        String unicode = "\\u6211\\u662f\\u4e00\\u4e2a\\u5c0f\\u5c0f\\u7684\\u53ef\\u7231\\u7684\\u5b57\\u7b26\\u4e32";
        //结果为："我是一个小小的可爱的字符串"
        String raw = Convert.unicodeToStr(unicode);
        System.out.println(JSON.toJSONString(raw));
    }

    public static void toStr() {
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        System.out.println(aStr);
    }

    public static void toIntArray() {
        String[] b = {"1", "2", "3", "4"};
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    public static void toIntArray2() {
        long[] c = {1, 2, 3, 4, 5};
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(c);
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i]);
        }
    }

    public static void toDate() {
        String a = "2017-05-06";
        Date value = Convert.toDate(a);
        System.out.println(value);
    }

    public static void toList() {
        Object[] a = {"a", "你", "好", "", 1};
        //从4.1.11开始可以这么用
        List<?> list = Convert.toList(a);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void toSBC() {
        String a = "123456789";
        //结果为："１２３４５６７８９"
        String sbc = Convert.toSBC(a);
        System.out.println(sbc);
    }


    public static void toDBC() {
        String a = "１２３４５６７８９";
        //结果为"123456789"
        String dbc = Convert.toDBC(a);
        System.out.println(dbc);
    }

    public static void toHex() {
        String a = "我是一个小小的可爱的字符串";
        //结果："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"
        String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
        System.out.println(hex);
    }

    public static void hexToStr() {
        String hex = "e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2";
        //结果为："我是一个小小的可爱的字符串"
        String raw = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(raw);
    }


}

package com.boot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Classname PassWordUtil
 * @Description TODO
 * @Date 2020/9/1 2:07 下午
 */
public class PassWordUtil {

    /**
     * @description: 256加密
     * @Param:  * @param bt
     * @return: java.lang.String
     * @author:
     * @time: 2020/9/1 2:07 下午
     */
    public static String sha256(byte[] bt) {
        MessageDigest md = null;
        String strDes = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 密码校验
     * @param pwd1
     * @param pwd2
     * @return
     */
    public static boolean equals(Object pwd1, Object pwd2) {
        if (pwd1 == pwd2) {
            return true;
        }
        if ((pwd1 == null) || (pwd2 == null)) {
            return false;
        }
        return pwd1.toString().equals(pwd2.toString());
    }
}

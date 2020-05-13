package com.aisino.util.handledata;

/**
 * @program: hunxiao
 * @description:
 * @author: Bruse Queen
 * @create: 2018-12-25 17:37
 **/
public class StringUtils {

    private static final int PAD_LIMIT = 8192;

    public static boolean isBlank(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

}

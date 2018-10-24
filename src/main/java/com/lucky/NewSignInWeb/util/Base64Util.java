package com.lucky.NewSignInWeb.util;

import com.sun.istack.internal.NotNull;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * 秘钥.
     */
    private static final String SALT = "iai2020877";

    /**
     * 编码.
     * @param str 明文.
     * @return String.
     */
    public static String encode(@NotNull String str) {
        try {
            return encoder.encodeToString((SALT + str).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 解码.
     * @param str 密文.
     * @return 去除秘钥后的值.
     */
    public static String decode(@NotNull String str) {
        String decodeStr = new String(decoder.decode(str));
        if (decodeStr.length() <= SALT.length() || !decodeStr.startsWith(SALT)) {
            return "";
        }

        return decodeStr.substring(SALT.length());
    }

    /**
     * 判断秘钥能否匹配.
     * @param str 密文.
     * @return boolean.
     */
    public static boolean check(String str) {
        if (str == null) {
            return false;
        }

        String decodeStr = new String(decoder.decode(str));
        if (decodeStr.length() <= SALT.length() || !decodeStr.startsWith(SALT)) {
            return false;
        }

        return true;
    }
}


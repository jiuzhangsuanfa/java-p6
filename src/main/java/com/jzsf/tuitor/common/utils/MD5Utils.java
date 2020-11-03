package com.jzsf.tuitor.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * MD5对字符串加密
 */
public final class MD5Utils {

    /**
     * 获取md5加密后的信息
     *
     * @param plainText
     * @return
     */
    public static String getMD5(String plainText) {
        try {
            //获取MD5实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] digest = md.digest();

            /*二次加密*/
            int i;
            StringBuilder codeBuilder = new StringBuilder();
            for (int offset = 0; offset < digest.length; offset++) {
                i = digest[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    codeBuilder.append(0);
                }
                //通过Integer.toHexString方法把值变为16进制
                codeBuilder.append(Integer.toHexString(i));
            }
            return codeBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}

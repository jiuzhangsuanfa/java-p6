package com.jzsf.tuitor.common.utils;

import java.util.Random;

/**
 * @author by plain yuan
 * @since 2020/04/12
 * 随机一个验证码
 */
public final class RandomCaptcha {

    /**
     * 验证码字符合集
     */
    private static char[] randString =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * @return 返回四位随机字符作为验证码
     */
    public static String get() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int count = 0;
        int len = randString.length;
        while (count++ < 4) {
            builder.append(randString[random.nextInt(len)]);
        }
        return builder.toString();
    }

}

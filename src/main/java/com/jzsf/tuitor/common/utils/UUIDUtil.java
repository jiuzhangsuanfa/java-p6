package com.jzsf.tuitor.common.utils;

import java.util.UUID;

/**
 * @author by plain yuan
 * @since 2020/04/13
 * UUID工具类
 */
public final class UUIDUtil {

    /**
     * 获取一个UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}

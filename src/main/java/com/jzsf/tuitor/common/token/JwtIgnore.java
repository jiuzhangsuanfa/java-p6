package com.jzsf.tuitor.common.token;

import java.lang.annotation.*;

/**
 * @author by plain yuan
 * @since 2020/04/13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}

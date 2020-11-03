package com.jzsf.tuitor.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/13
 * 校验entity字段的工具类
 *
 */
public final class BeanUtil {

    /**
     * 校验这个类的所有字段
     */
    public static List<String> validateProperty(Object validateObj) {
        return validateProperty(validateObj, (String[]) null);
    }

    /**
     * 类中的某些字段不校验
     *
     * @param validateObj
     * @param ignoreProperties 不校验的字段名
     * @return
     */
    public static List<String> validateProperty(Object validateObj, String... ignoreProperties) {
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(validateObj.getClass());
        List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);
        List<String> errList = new ArrayList<>();
        for (PropertyDescriptor targetPd : targetPds) {
            Method readMethod = targetPd.getReadMethod();
            if (readMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                try {
                    if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                        readMethod.setAccessible(true);
                    }
                    Object value = readMethod.invoke(validateObj);
                    if (value instanceof String) {
                        if (StringUtils.isEmpty((String) value)) {
                            errList.add(validateObj.getClass().getSimpleName() + " 字段 " + targetPd.getName() + " 不能为空");
                            continue;
                        }
                    }
                    if (value == null) {
                        errList.add("字段 " + targetPd.getName() + " 不能为空");
                    }
                } catch (Throwable ex) {
                    throw new FatalBeanException(
                            "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                }
            }
        }
        return errList;
    }

    /**
     * 将一个对象转换为另一个对象
     *
     * @param <T1>     要转换的对象
     * @param <T2>     转换后的类
     * @param original 要转换的对象
     * @param cast     转换后的类
     * @return 转换后的对象
     */
    public static <T1, T2> T2 convertBean(T1 original, Class<T2> cast) {
        T2 returnModel = null;
        try {
            returnModel = cast.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建" + cast.getName() + "对象失败");
        }
        //要转换的字段集合
        List<Field> fields = new ArrayList<>();
        //循环获取要转换的字段,包括父类的字段
        while (cast != null && !cast.getName().toLowerCase().equals("java.lang.object")) {
            fields.addAll(Arrays.asList(cast.getDeclaredFields()));
            //得到父类,然后赋给自己
            cast = (Class<T2>) cast.getSuperclass();
        }
        for (Field field : fields) {
            PropertyDescriptor getter = null;
            PropertyDescriptor setter = null;
            try {
                getter = new PropertyDescriptor(field.getName(), original.getClass());
                setter = new PropertyDescriptor(field.getName(), returnModel.getClass());
            } catch (Exception e) {
                continue;
            }
            try {
                Method getMethod = getter.getReadMethod();
                Object transValue = getMethod.invoke(original);
                Method setMethod = setter.getWriteMethod();
                setMethod.invoke(returnModel, transValue);
            } catch (Exception e) {
                throw new RuntimeException("cast " + original.getClass().getName() + "to "
                        + cast.getName() + " failed");
            }
        }
        return returnModel;
    }
}

package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by xueaohui on 2016/6/22.
 */
public final class ArrayUtil {
    /**
     * 判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtil.isEmpty(array);
    }

    /**
     *判断数组是否为空
     */
    private static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}

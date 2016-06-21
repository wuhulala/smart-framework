package com.smart4j.framework.util;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by xueaohui on 2016/6/20.
 */
public final class StringUtil {
    /**
     * �ж��ַ��Ƿ�Ϊ��
     */
    public static  boolean isEmpty(String strValue){
        if(strValue != null){
            strValue = strValue.trim();
        }
        return StringUtils.isEmpty(strValue);
    }
    /**
     * �ж��ַ��Ƿ�ǿ�
     */
    public static boolean isNotEmpty(String strValue) {
        return !isEmpty(strValue);
    }
}

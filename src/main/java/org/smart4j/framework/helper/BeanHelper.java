package org.smart4j.framework.helper;

import org.smart4j.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean 助手类
 * Created by xueaohui on 2016/6/22.
 */
public final class BeanHelper {

    /**
     * 定义Bean映射 (用于存放 Bean 类 与 Bean 实例的映射关系)
     */

    private static final Map<Class<?>,Object>BEAN_MAP = new HashMap<Class<?>, Object>();

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();

        for(Class<?>beanClass : classSet){
            BEAN_MAP.put(beanClass, ReflectionUtil.newInstance(beanClass));
        }
    }

    /**
     * 获取Bean映射
     */
    public static Map<Class<?>,Object>getBeanMap(){
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class " + cls );
        }
        return (T)BEAN_MAP.get(cls);
    }

}

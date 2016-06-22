package com.smart4j.framework.helper;

import com.smart4j.framework.annotation.Inject;
import com.smart4j.framework.util.ArrayUtil;
import com.smart4j.framework.util.CollectionUtil;
import com.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by xueaohui on 2016/6/22.
 */
public class IocHelpter {

    static {
        //获取所有的 Bean类 与 Bean实例 之间的映射关系（简称Bean Map）
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();

        if(CollectionUtil.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object>beanEntry : beanMap.entrySet()){
                //从 BeanMap 获取Bean类的实例
                Class<?>beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                Field[] beanFields = beanClass.getDeclaredFields();

                if(ArrayUtil.isNotEmpty(beanFields)){

                    for(Field beanField : beanFields){
                        //判断是否带有 Inject 的注解
                        if(beanField.isAnnotationPresent(Inject.class)){
                            //在映射中 获取 BeanField 实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if(beanFieldInstance != null){
                                //反射 初始化beanField的实例
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }

                    }
                }
            }
        }
    }

}

package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xueaohui on 2016/6/22.
 * 类操作助手
 */
public final class ClassHelper {

    /**
     * 定义类集合（用于存放加载的类）
     */
    private static final Set<Class<?>> CLASS_SET;

    static {
        String beanPackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(beanPackage);
    }

    /**
     * 获取包下的所有类
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取应用包下所有的Service类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> set = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                set.add(cls);
            }
        }

        return set;
    }

    /**
     * 获取应用包下所有的Controller类
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> set = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                set.add(cls);
            }
        }

        return set;
    }

    /**
     * 获取包下所有的bean 包括 service 和 controller 等
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getControllerClassSet());
        beanClassSet.addAll(getServiceClassSet());
        return beanClassSet;
    }

    /**
     * 获取应用包名下某父类（或接口）的所有接口（或实现类）
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){
                classSet.add(cls);
            }
        }

        return classSet;
    }

    /**
     * 获取应用包名下带有某注解的类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }

        return classSet;
    }
}

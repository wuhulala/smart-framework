package com.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xueaohui on 2016/6/22.
 * 控制器注解
 * @Target(ElementType.FIELD)   //字段、枚举的常量
 * @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 * http://blog.csdn.net/yixiaogang109/article/details/7328466 具体可以看
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject{
}
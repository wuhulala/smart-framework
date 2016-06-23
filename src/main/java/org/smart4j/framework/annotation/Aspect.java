package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * @author xueaohui
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 注解
     */
    Class<? extends Annotation> value();
}

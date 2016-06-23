package org.smart4j.app.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * @author xueaohui
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void before(Class<?> targetClass, Method targetMethod, Object[] params) {
        LOGGER.debug("--------------begin--------------");
        LOGGER.debug(String.format("class %s",targetClass.getName()));
        LOGGER.debug(String.format("method %s",targetMethod.getName()));
        begin = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> targetClass, Method targetMethod, Object[] params) {
        long x = System.currentTimeMillis() - begin;
        LOGGER.debug("time: "+x+"ms");
        LOGGER.debug("--------------end--------------");
    }
}

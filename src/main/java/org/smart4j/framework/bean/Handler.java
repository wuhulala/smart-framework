package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * Created by xueaohui on 2016/6/22.
 */
public class Handler {
    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler() {
    }

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}

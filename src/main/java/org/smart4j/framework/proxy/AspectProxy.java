package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author xueaohui
 */
public class AspectProxy implements Proxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    public Object doProxy(ProxyChain proxyChain) throws Throwable {


        Object result = null;

        Class<?> targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            //拦截方法
            if(intercept(targetClass,targetMethod,params)){
                before(targetClass, targetMethod, params);
                result = proxyChain.doProxyChain(); //执行下一个代理
                after(targetClass, targetMethod, params);
            }
        }catch (Exception e){
            LOGGER.error("aspectProxy failure" + e);
            error(targetClass, targetMethod, params, e);
            throw e;
        }finally {
            end();
        }
        return result;
    }

    public void end() {

    }

    public void error(Class<?> targetClass, Method targetMethod, Object[] params, Throwable e) {

    }

    public void after(Class<?> targetClass, Method targetMethod, Object[] params) {

    }

    public void before(Class<?> targetClass, Method targetMethod, Object[] params) {

    }

    public void begin() {

    }

    public boolean intercept(Class<?> targetClass, Method targetMethod, Object[] params) throws Throwable{
        return true;
    }
}

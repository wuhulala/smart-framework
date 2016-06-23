package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理链
 *
 * @author xueaohui
 *
 */
public class ProxyChain {

    private final Class<?> targetClass; //目标类
    private final Object targetObject; //目标对象
    private final Method targetMethod; //目标方法
    private final MethodProxy methodProxy; //方法代理
    private final Object[] methodParams; //方法参数

    private List<Proxy> proxyList = new ArrayList<Proxy>();

    private int proxyIndex = 0 ;

    public ProxyChain(List<Proxy> proxyList,Class<?> targetClass, Object targetObject,   Method targetMethod,  MethodProxy methodProxy ,Object[] methodParams) {
        this.proxyList = proxyList;
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object doProxyChain()throws Throwable{
        Object methodResult;
        if(proxyIndex < proxyList.size()){
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        }else{
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}

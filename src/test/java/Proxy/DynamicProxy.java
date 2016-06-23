package Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author xueaohui
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;

    public DynamicProxy() {
    }

    public DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * @param proxy 在一个莫名的方法会把本身传入作为参数传进去
     * @param method 就是我们要是实现的方法
     * @param args  就是此方法的参数
     * @return 返回方法的返回值
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        //想了好久 发现这就是方法的返回值
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }
    private void after() {
        System.out.println(new Date().getTime());
    }

    private void before() {
        System.out.println(new Date().getTime());
    }
}

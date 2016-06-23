package Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author xueaohui
 */
public class CGLibProxy implements MethodInterceptor {
    private static CGLibProxy instance = new CGLibProxy();

    private CGLibProxy(){

    }

    public static CGLibProxy getInstance(){
        return instance;
    }

    public <T> T getProxy(Class<T> tClass){
        return (T) Enhancer.create(tClass,this);
    }

    public Object intercept(Object obj,Method method,Object[] args,MethodProxy proxy) throws Throwable {
        before();
        Object result = proxy.invokeSuper(obj,args);
        after();
        return result;
    }

    private void after() {
        System.out.println("end : " + new Date().getTime());
    }

    private void before() {
        System.out.println("start : " + new Date().getTime());
    }
}

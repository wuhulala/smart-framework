package org.smart4j.framework.proxy;

/**
 * 代理接口
 *
 * @author xueaohui
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;

}

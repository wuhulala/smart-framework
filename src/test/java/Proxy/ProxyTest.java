package Proxy;

import org.junit.Test;

/**
 * @author xueaohui
 */
public class ProxyTest {
    @Test
    public void TestStaticProxy(){
        /*HelloProxy helloProxy = new HelloProxy();
        helloProxy.say("jack");

        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());

        Hello helloProxy2 = dynamicProxy.getProxy();

        String string = helloProxy2.getString();
        System.out.println(string);
        *//**
         1466648587670
         Hello jack
         1466648587671
         1466648587679
         1466648587680
         String
         */

        Hello hello = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        hello.say("sssss");

    }
}

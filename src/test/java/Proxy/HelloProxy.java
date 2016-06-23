package Proxy;

import java.util.Date;

/**
 * @author xueaohui
 */
public class HelloProxy implements Hello {
    private Hello hello;
    public HelloProxy(){
        hello = new HelloImpl();
    }

    public void say(String name) {
        before();
        hello.say(name);
        after();
    }

    public String getString() {
        return "String";
    }

    private void after() {
        System.out.println(new Date().getTime());
    }

    private void before() {
        System.out.println(new Date().getTime());
    }

}

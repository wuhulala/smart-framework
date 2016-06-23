package Proxy;

/**
 * @author xueaohui
 */
public class HelloImpl implements Hello {
    public void say(String name) {
        System.out.println("Hello " + name);
    }

    public String getString() {
        return "String";
    }
}

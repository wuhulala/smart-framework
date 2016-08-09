import org.junit.Test;
import org.smart4j.app.model.Customer;
import org.smart4j.framework.helper.ConfigHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xueaohui
 */
public class TTTT {
    @Test
    public void test() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("admmm");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "1111");
        map.put("customer", customer);
        System.out.println(map);
        customer = (Customer) map.get("customer");
        customer.setName("asd");
        System.out.println(map);
    }
}

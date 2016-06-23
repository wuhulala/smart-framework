import org.junit.Test;
import org.smart4j.framework.helper.ConfigHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xueaohui
 */
public class TTTT {
    @Test
         public void test(){
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("1","1111");
        map.put("2","22222");
        System.out.println(map);
        map.put("1", "91923123");
        System.out.println(map);
    }
}

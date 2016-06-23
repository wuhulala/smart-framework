package org.smart4j.framework;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

import java.util.Map;

/**
 * @author xueaohui
 */
public class HelpLoader {
    public static void init(){
        //因为要先获取完代理对象 才可以进行依赖注入
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelpter.class,
                ControllerHelper.class
        };

        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }

        for(Map.Entry<Class<?>,Object> a: BeanHelper.getBeanMap().entrySet()){
            System.out.println("类:"+a.getKey() + " ------ 实例:" +a.getValue());
        }

    }

}

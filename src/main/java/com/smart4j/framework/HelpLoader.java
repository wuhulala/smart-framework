package com.smart4j.framework;

import com.smart4j.framework.annotation.Controller;
import com.smart4j.framework.helper.BeanHelper;
import com.smart4j.framework.helper.ClassHelper;
import com.smart4j.framework.helper.IocHelpter;
import com.smart4j.framework.util.ClassUtil;

/**
 * @author xueaohui
 */
public class HelpLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelpter.class,
                Controller.class
        };

        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName());
        }

    }

}

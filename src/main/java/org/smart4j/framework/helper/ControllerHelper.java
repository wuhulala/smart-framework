package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xueaohui on 2016/6/22.
 */
public final class ControllerHelper {
    /**
     * 用来存放请求与处理器的映射关系(Action Map)
     */
    private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();


        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for(Class<?> controllerClass : controllerClassSet){

                Method[] methods = controllerClass.getMethods();

                if(ArrayUtil.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        //如果这个方法被Action注释了
                        if(method.isAnnotationPresent(Action.class)){
                            Action action = method.getAnnotation(Action.class);
                            //获取Action的属性值
                            String mapping = action.value();
                            //验证URL 如get:customer_list post:customer_update
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtil.isNotEmpty(array)){
                                    String requestMethod = array[0];
                                    String requestPath = array[1];

                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //初始化handler
                                    ACTION_MAP.put(request,handler);
                                }

                            }

                        }
                    }
                }
            }
        }
    }
    /**
     * 获取Handler
     */
    public static Handler getHandler(String requestMethod ,  String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}

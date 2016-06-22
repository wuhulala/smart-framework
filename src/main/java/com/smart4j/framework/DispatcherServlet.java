package com.smart4j.framework;

import com.smart4j.framework.bean.Data;
import com.smart4j.framework.bean.Handler;
import com.smart4j.framework.bean.Param;
import com.smart4j.framework.bean.View;
import com.smart4j.framework.helper.BeanHelper;
import com.smart4j.framework.helper.ConfigHelper;
import com.smart4j.framework.helper.ControllerHelper;
import com.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xueaohui
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        HelpLoader.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        ServletRegistration defaultServlet =servletContext.getServletRegistration("default");
        jspServlet.addMapping(ConfigHelper.getAppJSpPath()+"*");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);

        if(handler != null){
            //获取请求方法与路径
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //创建请求参数对象
            Map<String,Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();

            //1. 先通过调用request.getParameter() 方法得到参数后，
            //2. 再调用 request.getInputStream()或request.getReader()已经得不到流中的内容，
            //3. 因为在调用 request.getParameter()时
            //4. 系统可能对表单中提交的数 据以流的形式读了一次,反之亦然。
            while(paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);

            }

            String body = CodeUtil.decodeURL(StreamUtil.getString(req.getInputStream()));

            if(StringUtil.isNotEmpty(body)){
                String[] params = StringUtil.splitString(body,"&");
                if(ArrayUtil.isNotEmpty(params)){
                    for(String param : params){
                        String[] array = StringUtil.splitString(param,"=");
                        if(ArrayUtil.isNotEmpty(array) && array.length == 2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }

            Param param = new Param(paramMap);

            //调用 Action 方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
            //处理Action返回值
            if(result instanceof View){
                View view = (View) result;
                String path = view.getPath();

                if(StringUtil.isNotEmpty(path)){
                    if(path.startsWith("/")){
                        resp.sendRedirect(req.getContextPath() + path);
                    }else{
                        Map<String ,Object> model = view.getModel();

                        for(Map.Entry<String,Object>entry : model.entrySet()){
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJSpPath()+path).forward(req,resp);
                    }
                }
            }else if(result instanceof Data){
                //返回JSON
                Data data = (Data)result;
                Object model = data.getModel();

                if(model != null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }

    }
}

package com.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author xueaohui
 */
public final class StreamUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     */
    public static String getString(InputStream is){
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line ;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception e) {
            LOGGER.error("get String failure" , e);
        }
        return sb.toString();
    }

}

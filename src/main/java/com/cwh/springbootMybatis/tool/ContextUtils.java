package com.cwh.springbootMybatis.tool;

//import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * @author wanghu
 * spring上下文工具类
 * bean 的手动注入工具类
 */
public class ContextUtils {
	
    private static ApplicationContext applicationContext;

   
    public static void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (ContextUtils.class) {
            ContextUtils.applicationContext = applicationContext;
            ContextUtils.class.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext() {
        synchronized (ContextUtils.class) {
            while (applicationContext == null) {
                try {
                   // logger.debug("getApplicationContext, wait...");
                    ContextUtils.class.wait(60000);
                    if (applicationContext == null) {
                     //   logger.warn("Have been waiting for ApplicationContext to be set for 1 minute", new Exception());
                    }
                } catch (InterruptedException ex) {
                    //logger.debug("getApplicationContext, wait interrupted");
                }
            }
            return applicationContext;
        }
    }

    /**
     * 根据名字获取实体
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据类型获取实体
     *
     * @param <T>
     * @param cls
     * @return
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> cls) {
        return getApplicationContext().getBeansOfType(cls);
    }


}
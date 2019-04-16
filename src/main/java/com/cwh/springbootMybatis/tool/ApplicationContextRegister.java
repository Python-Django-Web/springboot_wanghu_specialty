package com.cwh.springbootMybatis.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import org.springframework.stereotype.Component;

/**
 * @author wanghu
 * ContextUtils注册
 * bean的手动注入
 * 实现ApplicationContextAware接口
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {


    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        ContextUtils.setApplicationContext(applicationContext);
    }
}
package com.qgx.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *@Author: goxcheer
 *@Date:22:07 2018/9/8
 *@email:604721660@qq.com
 *@decription:获取SpringContext的工具类
 */
@Component
public final class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac =  applicationContext;
    }

    public static ApplicationContext getApplicationContext () {
        return  ac;
    }

    /**
     * 根据id获取Bean
     * @param id
     * @return
     */
    public static Object getBean (String id) {
        return ac.getBean(id);
    }

    /**
     * 根据类获取Bean
     * @param c
     * @return
     */
    public static Object getBean (Class c) {
        return ac.getBean(c);
    }
}

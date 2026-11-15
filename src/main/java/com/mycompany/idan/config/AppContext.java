package com.mycompany.idan.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mycompany.idan.dao.AkunDao;

public final class AppContext {

    private static AnnotationConfigApplicationContext context;

    private AppContext() {
    }

    public static synchronized AkunDao akunDao() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext(SpringConfig.class);
        }
        return context.getBean(AkunDao.class);
    }

    public static synchronized void shutdown() {
        if (context != null) {
            context.close();
            context = null;
        }
    }
}
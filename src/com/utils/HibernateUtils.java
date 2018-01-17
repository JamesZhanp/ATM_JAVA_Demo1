package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtils {
    private HibernateUtils(){}
    private static HibernateUtils instance = new HibernateUtils();

    public static HibernateUtils getInstance(){
        return instance;
    }

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            //创建配置对象，获取hibernate.cfg.xml配置文件的信息
            org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration().configure();
            //创建服务注册对象，创建和销毁都相当耗费资源，通常一个系统内一个数据库只创建一个
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
            //创建会话工厂对象，类似于JDBC的Connection
            sessionFactory = config.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public Session getSession(){
        return getSessionFactory().openSession();
    }
}

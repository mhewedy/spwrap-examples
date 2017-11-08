package com.mkyong.rest;

import spwrap.DAO;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.sql.DataSource;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class MyApplication extends Application {

    @Resource(mappedName = "java:jboss/datasources/TestDS")
    private DataSource dataSource;

    @Produces
    @Singleton
    public DAO dao(){
        System.out.println("init DAO");
        return new DAO.Builder(dataSource)
                .config(new spwrap.Config().useStatusFields(false))
                .build();
    }

    @Produces
    @Singleton
    public CoffeeDAO coffeeDAO(DAO dao){
        System.out.println("init CoffeeDAO");
        return dao.create(CoffeeDAO.class);
    }
}

package com.mkyong.rest;

import spwrap.DAO;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.lang.reflect.Field;

public class Config {

    /**
     * DataSource is defined in the application server
     */
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

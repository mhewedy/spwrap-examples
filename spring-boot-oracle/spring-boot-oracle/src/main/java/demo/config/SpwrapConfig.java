package demo.config;

import demo.CoffeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spwrap.Config;
import spwrap.DAO;

import javax.sql.DataSource;

@Configuration
public class SpwrapConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public DAO dao(){
        return new DAO.Builder(dataSource)
                .config(new Config().useStatusFields(false))
                .build();
    }

    @Bean
    public CoffeeDAO coffeeDAO(DAO dao){
        return dao.create(CoffeeDAO.class);
    }
}

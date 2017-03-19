package com.example;

import com.example.repo.SupplierDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import spwrap.Config;
import spwrap.DAO;

import javax.sql.DataSource;

@EnableJpaRepositories
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public DAO dao(DataSource dataSource) {
        return new DAO.Builder(dataSource)
                .config(new Config().useStatusFields(false))
                .build();
    }

    @Bean
    public SupplierDAO supplierDAO(DAO dao) {
        return dao.create(SupplierDAO.class);
    }
}

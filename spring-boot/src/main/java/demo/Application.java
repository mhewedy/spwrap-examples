package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    CoffeeDAO coffeeDAO;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> coffeeNames = coffeeDAO.listCoffeeNames();
        logger.info("list of coffee names: {} \n\n", coffeeNames);

        List<Supplier> suppliers = coffeeDAO.showCoffeeSuppliers();
        logger.info("list of coffee suppliers: {} \n\n", suppliers);

        coffeeNames.forEach(it -> {
            logger.info("coffee: '{}' is supplied by: '{}'\n\n", coffeeDAO.getSupplier(it), it);
        });

        BigDecimal newPrice = coffeeNames.stream()
                .findFirst()
                .map(it -> coffeeDAO.raisePrice(it, 0.20f, BigDecimal.valueOf(20)))
                .orElse(null);
        logger.info("New Price: {}\n\n", newPrice);
    }
}

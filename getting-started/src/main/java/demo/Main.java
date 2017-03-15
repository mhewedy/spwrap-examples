package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spwrap.Config;
import spwrap.DAO;
import utility.ScriptRunner;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/test?user=root";

    public static void main(String[] args) {
        try{
            ScriptRunner.run(JDBC_URL, "install.sql");

            DAO dao = new DAO.Builder(JDBC_URL, "root", "")
                    .config(new Config().useStatusFields(false))
                    .build();

            CoffeeDAO coffeeDAO = dao.create(CoffeeDAO.class);

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

        }finally {
            ScriptRunner.run(JDBC_URL, "rollback.sql");
        }
    }
}

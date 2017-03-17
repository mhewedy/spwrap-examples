package demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spwrap.*;

import java.util.List;

import static demo.Util.getOrclResultCode;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    private static final int ORCL_NO_DATA_FOUND = 1403;
    private CoffeeDAO coffeeDAO;

    public CoffeeController(CoffeeDAO coffeeDAO) {
        this.coffeeDAO = coffeeDAO;
    }

    @GetMapping("/names/list")
    ResponseEntity<?> listCoffeeNames() {
        return ok(coffeeDAO.listCoffeeNames());
    }

    @GetMapping("/suppliers")
    ResponseEntity<?> showCoffeeSuppliers() {
        List<Supplier> suppliers = coffeeDAO.showCoffeeSuppliers();
        return ok(suppliers);
    }

    @GetMapping("/supplier/{coffeeName}")
    ResponseEntity<?> getSupplier(@PathVariable("coffeeName") String coffeeName) {
        try {
            return ok(coffeeDAO.getSupplier(coffeeName));
        } catch (CallException ex) {
            if (getOrclResultCode(ex) == ORCL_NO_DATA_FOUND) {
                return status(NOT_FOUND).body("Not Found!");
            } else {
                return status(INTERNAL_SERVER_ERROR).body(ex.getMessage());
            }
        }
    }
}

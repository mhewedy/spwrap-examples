package demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    private CoffeeDAO coffeeDAO;

    public CoffeeController(CoffeeDAO coffeeDAO){
        this.coffeeDAO = coffeeDAO;
    }

    @GetMapping("/names/list")
    ResponseEntity<?> listCoffeeNames(){
        return ok(coffeeDAO.listCoffeeNames());
    }

    @GetMapping("/suppliers")
    ResponseEntity<?> showCoffeeSuppliers(){
        List<Supplier> suppliers = coffeeDAO.showCoffeeSuppliers();
        return ok(suppliers);
    }

    @GetMapping("/supplier/{coffeeName}")
    ResponseEntity<?> getSupplier(@PathVariable("coffeeName") String coffeeName){
        String supplier = coffeeDAO.getSupplier(coffeeName);
        if (supplier != null){
            return ok(supplier);
        }else{
            return status(NOT_FOUND).body("Not Found!");
        }
    }


    @PostMapping("/raisePrice")
    ResponseEntity<?> raisePrice(@RequestParam("coffeeName") String coffeeName,
                                 @RequestParam("maximumPercentage") float maximumPercentage,
                                 @RequestParam("newInput") BigDecimal newInput){
        return ok(coffeeDAO.raisePrice(coffeeName, maximumPercentage, newInput));
    }
}

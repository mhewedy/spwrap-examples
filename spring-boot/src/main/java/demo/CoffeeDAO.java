package demo;

import demo.mappers.StringResultSetMapper;
import spwrap.annotations.Mapper;
import spwrap.annotations.Param;
import spwrap.annotations.Scalar;
import spwrap.annotations.StoredProc;

import java.math.BigDecimal;
import java.util.List;

import static java.sql.Types.*;

public interface CoffeeDAO {

    @Mapper(StringResultSetMapper.class)
    @StoredProc("list_coffee_names")
    List<String> listCoffeeNames();

    @StoredProc("show_coffee_suppliers")
    List<Supplier> showCoffeeSuppliers();

    @Scalar(VARCHAR)
    @StoredProc("get_supplier_of_coffee")
    String getSupplier(@Param(VARCHAR) String coffeeName);

    @Scalar(NUMERIC)
    @StoredProc("raise_price")
    BigDecimal raisePrice(@Param(VARCHAR) String coffeeName,
                          @Param(FLOAT) float maximumPercentage,
                          @Param(NUMERIC) BigDecimal newInput);

}

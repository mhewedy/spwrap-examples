package demo;

import spwrap.annotations.Mapper;
import spwrap.annotations.Param;
import spwrap.annotations.Scalar;
import spwrap.annotations.StoredProc;

import java.math.BigDecimal;
import java.util.List;

import static java.sql.Types.FLOAT;
import static java.sql.Types.NUMERIC;
import static java.sql.Types.VARCHAR;

public interface CoffeeDAO {

    @Mapper(StringResultSetMapper.class)
    @StoredProc("LIST_COFFEE_NAMES")
    List<String> listCoffeeNames();

    @StoredProc("SHOW_COFFEE_SUPPLIERS")
    List<Supplier> showCoffeeSuppliers();

    @Scalar(VARCHAR)
    @StoredProc("GET_SUPPLIER_OF_COFFEE")
    String getSupplier(@Param(VARCHAR) String coffeeName);

    @Scalar(NUMERIC)
    @StoredProc("RAISE_PRICE")
    BigDecimal raisePrice(@Param(VARCHAR) String coffeeName,
                          @Param(FLOAT) float maximumPercentage,
                          @Param(NUMERIC) BigDecimal newInput);

}

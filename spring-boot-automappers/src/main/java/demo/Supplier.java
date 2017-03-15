package demo;

import spwrap.mappers.ResultSetMapper;
import spwrap.result.Result;

public class Supplier implements ResultSetMapper<Supplier> {

    private String supplierName;
    private String coffeeName;

    public Supplier() {
    }

    private Supplier(String supplierName, String coffeeName) {
        this.supplierName = supplierName;
        this.coffeeName = coffeeName;
    }

    @Override
    public Supplier map(Result<?> result) {
        return new Supplier(result.getString("SUP_NAME"), result.getString("COF_NAME"));
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierName='" + supplierName + '\'' +
                ", coffeeName='" + coffeeName + '\'' +
                '}';
    }
}

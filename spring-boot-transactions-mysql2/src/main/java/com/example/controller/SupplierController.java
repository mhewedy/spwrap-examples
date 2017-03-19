package com.example.controller;

import com.example.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/add")
    public ResponseEntity<?> add(){
        try{
            supplierService.add2Suppliers();
            return ok().build();
        }catch (Exception ex){
            return status(INTERNAL_SERVER_ERROR).body(ex.getMessage() +
                    ", <b>class</b>: " + ex.getClass() + ", <b>cause</b>: " + ex.getCause());
        }

    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return ok(supplierService.listSuppliers());
    }

    @GetMapping("/listDAO")
    public ResponseEntity<?> listDAO(){
        return ok(supplierService.listSuppliersUsingDAO());
    }
}

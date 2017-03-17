package com.example.controller;

import com.example.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/add")
    public ResponseEntity<?> add(){
        supplierService.add2Suppliers();
        return ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return ok(supplierService.listSuppliers());
    }
}

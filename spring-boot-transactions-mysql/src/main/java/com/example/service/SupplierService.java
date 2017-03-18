package com.example.service;

import com.example.model.Supplier;
import com.example.repo.SupplierDAO;
import com.example.repo.SupplierRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierService {

    private SupplierDAO supplierDAO;
    private SupplierRepo supplierRepo;

    public SupplierService(SupplierDAO supplierDAO, SupplierRepo supplierRepo) {
        this.supplierDAO = supplierDAO;
        this.supplierRepo = supplierRepo;
    }

    @Transactional
    public void add2Suppliers(){
        final String supplierName = "Abdullah";

        supplierDAO.insertSupplier(supplierName);   // << will rolled-back

        Supplier s2 = new Supplier();
        s2.setName(supplierName);
        supplierRepo.save(s2);      // throws exception
    }

    public List<Supplier> listSuppliers(){
        return supplierRepo.findAll();
    }
}

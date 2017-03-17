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

        supplierDAO.insertSupplier("ali");

        Supplier s2 = new Supplier();
        s2.setName("ali");
        supplierRepo.save(s2);
    }

    public List<Supplier> listSuppliers(){
        return supplierRepo.findAll();
    }
}

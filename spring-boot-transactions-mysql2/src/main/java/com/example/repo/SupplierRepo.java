package com.example.repo;

import com.example.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    List<Supplier> findAll();
}

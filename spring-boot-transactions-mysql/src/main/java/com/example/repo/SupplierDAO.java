package com.example.repo;

import spwrap.annotations.Param;
import spwrap.annotations.StoredProc;

import static java.sql.Types.VARCHAR;

public interface SupplierDAO {

    @StoredProc("insert_new_supplier")
    void insertSupplier(@Param(VARCHAR) String name);
}

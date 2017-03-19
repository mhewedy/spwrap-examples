package com.example.repo;

import com.example.model.Supplier;
import spwrap.annotations.Param;
import spwrap.annotations.Props;
import spwrap.annotations.Props.Connection;
import spwrap.annotations.StoredProc;

import java.util.List;

import static java.sql.Types.VARCHAR;
import static spwrap.annotations.Props.Isolation.READ_UNCOMMITTED;

public interface SupplierDAO {

    @StoredProc("insert_new_supplier")
    void insertSupplier(@Param(VARCHAR) String name);

    @Connection(isolation = READ_UNCOMMITTED)
    @StoredProc("find_all")
    List<Supplier> findAll();
}

package com.warehouse_management_system.repository.warehouse;

import com.warehouse_management_system.model.User;
import com.warehouse_management_system.model.Warehouse;

import java.util.List;

public interface WarehouseRepository {
    void save(Warehouse warehouse);
    Warehouse findById(String warehouseId);
    List<Warehouse> findAll();
    void update(Warehouse warehouse);
    void delete(String warehouseId);
}
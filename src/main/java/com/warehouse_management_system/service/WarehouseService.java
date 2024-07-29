package com.warehouse_management_system.service;

import com.warehouse_management_system.model.Warehouse;
import com.warehouse_management_system.repository.warehouse.WarehouseRepository;
import com.warehouse_management_system.repository.warehouse.WarehouseRepositoryImpl;
import com.warehouse_management_system.util.DatabaseUtil;

import java.util.List;

public class WarehouseService {
    private final WarehouseRepository warehouseRepository = new WarehouseRepositoryImpl();

    public void saveWarehouse(Warehouse warehouse) {
        warehouse.setWare_id(DatabaseUtil.generateId());
        warehouseRepository.save(warehouse);
    }

    public Warehouse getWarehouseById(String wareId) {
        return warehouseRepository.findById(wareId);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public void updateWarehouse(Warehouse warehouse) {
        warehouseRepository.update(warehouse);
    }

    public void deleteWarehouse(String wareId) {
        warehouseRepository.delete(wareId);
    }
}
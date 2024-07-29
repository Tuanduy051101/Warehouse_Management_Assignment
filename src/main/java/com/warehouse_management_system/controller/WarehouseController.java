package com.warehouse_management_system.controller;

import com.warehouse_management_system.model.Warehouse;
import com.warehouse_management_system.service.WarehouseService;

import java.util.List;

public class WarehouseController {
    private final WarehouseService warehouseService = new WarehouseService();

    public void createWarehouse(Warehouse warehouse) {
        warehouseService.saveWarehouse(warehouse);
    }

    public Warehouse getWarehouse(String wareId) {
        return warehouseService.getWarehouseById(wareId);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    public void updateWarehouse(Warehouse warehouse) {
        warehouseService.updateWarehouse(warehouse);
    }

    public void deleteWarehouse(String wareId) {
        warehouseService.deleteWarehouse(wareId);
    }
}
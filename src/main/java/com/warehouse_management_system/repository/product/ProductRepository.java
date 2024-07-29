package com.warehouse_management_system.repository.product;

import com.warehouse_management_system.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    void save(Product product);
    Product findById(String prodId);
    List<Product> findAll();

    List<Product> findByWarehouseId(String warehouseId);

    void update(Product product);
    void delete(String prodId);

    int findTotalProductQuantityForUser(String user_id);

    Map<String, Integer> findTotalProductQuantityForAdmin();
}
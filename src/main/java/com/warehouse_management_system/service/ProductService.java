package com.warehouse_management_system.service;

import com.warehouse_management_system.model.Product;
import com.warehouse_management_system.repository.product.ProductRepository;
import com.warehouse_management_system.repository.product.ProductRepositoryImpl;
import com.warehouse_management_system.util.DatabaseUtil;

import java.util.List;
import java.util.Map;

public class ProductService {
    private final ProductRepository productRepository = new ProductRepositoryImpl();

    public String saveProduct(Product product) {
        product.setProd_id(DatabaseUtil.generateId());
        product.setDecrypt_key("empty");
        product.setRecord_status("ACTIVE");
        productRepository.save(product);
        return product.getProd_id();
    }

    public Product getProductById(String prodId) {
        return productRepository.findById(prodId);
    }

    public List<Product> getProductByWarehouseId(String warehouseId) {
        return productRepository.findByWarehouseId(warehouseId);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    public void deleteProduct(String prodId) {
        productRepository.delete(prodId);
    }

    public int getTotalProductQuantityForUser(String user_id) {
        return productRepository.findTotalProductQuantityForUser(user_id);
    }

    public Map<String, Integer> getTotalProductQuantityForAdmin() {
        return productRepository.findTotalProductQuantityForAdmin();
    }
}
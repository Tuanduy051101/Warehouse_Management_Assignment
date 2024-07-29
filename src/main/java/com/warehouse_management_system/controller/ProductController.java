package com.warehouse_management_system.controller;

import com.warehouse_management_system.model.Product;
import com.warehouse_management_system.service.ProductService;

import java.util.List;
import java.util.Map;

public class ProductController {
    private ProductService productService = new ProductService();

    public String createProduct(Product product) {
        return productService.saveProduct(product);
    }

    public Product getProduct(String prodId) {
        return productService.getProductById(prodId);
    }

    public List<Product> getProductsByWarehouseId(String warehouseId) {
        return productService.getProductByWarehouseId(warehouseId);
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    public void deleteProduct(String prodId) {
        productService.deleteProduct(prodId);
    }

    public int getAllProductQuantityForUser(String user_id) {
        return productService.getTotalProductQuantityForUser(user_id);
    }

    public Map<String, Integer> getProductsQuantityForAdmin() {
        return productService.getTotalProductQuantityForAdmin();
    }
}
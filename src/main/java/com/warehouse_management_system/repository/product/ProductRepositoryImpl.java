package com.warehouse_management_system.repository.product;

import com.warehouse_management_system.config.DatabaseConfig;
import com.warehouse_management_system.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO Product (prod_id, prod_name, prod_quantity, prod_cost, ware_id, decrypt_key) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getProd_id());
            stmt.setString(2, product.getProd_name());
            stmt.setInt(3, product.getProd_quantity());
            stmt.setDouble(4, product.getProd_cost());
            stmt.setString(5, product.getWare_id());
            stmt.setString(6, "x");
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Created successfully.");
            } else {
                System.out.println("Record already exists.");
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
    }

    @Override
    public Product findById(String prodId) {
        String sql = "SELECT * FROM Product WHERE prod_id = ? AND record_status = 'ACTIVE'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prodId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setProd_id(rs.getString("prod_id"));
                product.setProd_name(rs.getString("prod_name"));
                product.setProd_quantity(rs.getInt("prod_quantity"));
                product.setProd_cost(rs.getDouble("prod_cost"));
                product.setWare_id(rs.getString("ware_id"));
                product.setDecrypt_key(rs.getString("decrypt_key"));
                product.setRecord_status(rs.getString("record_status"));
                return product;
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.*, w.user_id " +
                "FROM Product p " +
                "JOIN Warehouse w ON p.ware_id = w.ware_id " +
                "WHERE p.record_status = 'ACTIVE'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProd_id(rs.getString("prod_id"));
                product.setProd_name(rs.getString("prod_name"));
                product.setProd_quantity(rs.getInt("prod_quantity"));
                product.setProd_cost(rs.getDouble("prod_cost"));
                product.setWare_id(rs.getString("ware_id"));
                product.setDecrypt_key(rs.getString("decrypt_key"));
                product.setRecord_status(rs.getString("record_status"));
                product.setUser_id(rs.getString("user_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return products;
    }

    @Override
    public List<Product> findByWarehouseId(String warehouseId) {
        System.out.println("running");
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE record_status = 'ACTIVE' AND ware_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, warehouseId);
            System.out.println(warehouseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProd_id(rs.getString("prod_id"));
                product.setProd_name(rs.getString("prod_name"));
                product.setProd_quantity(rs.getInt("prod_quantity"));
                product.setProd_cost(rs.getDouble("prod_cost"));
                product.setWare_id(rs.getString("ware_id"));
                product.setDecrypt_key(rs.getString("decrypt_key"));
                product.setRecord_status(rs.getString("record_status"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return products;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE Product SET prod_name = ?, prod_quantity = ?, prod_cost = ?, ware_id = ? WHERE prod_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getProd_name());
            stmt.setInt(2, product.getProd_quantity());
            stmt.setDouble(3, product.getProd_cost());
            stmt.setString(4, product.getWare_id());
            stmt.setString(5, product.getProd_id());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
            } else {
                System.out.println("Record already exists.");
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
    }

    @Override
    public void delete(String prodId) {
        String sql = "UPDATE Product SET record_status = 'DELETED' WHERE prod_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prodId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully.");
            } else {
                System.out.println("Record already exists.");
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
    }

    @Override
    public int findTotalProductQuantityForUser(String user_id) {
        String sql = "SELECT SUM(p.prod_quantity) AS total_quantity " +
                "FROM Product p " +
                "JOIN Warehouse w ON p.ware_id = w.ware_id " +
                "WHERE w.user_id = ? AND p.record_status = 'ACTIVE'";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Map<String, Integer> findTotalProductQuantityForAdmin() {
        String sql = "SELECT w.ware_id, SUM(p.prod_quantity) AS total_quantity " +
                "FROM Product p " +
                "JOIN Warehouse w ON p.ware_id = w.ware_id " +
                "WHERE p.record_status = 'ACTIVE' " +
                "GROUP BY w.ware_id";

        Map<String, Integer> warehouseQuantities = new HashMap<>();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String wareId = rs.getString("ware_id");
                int totalQuantity = rs.getInt("total_quantity");
                warehouseQuantities.put(wareId, totalQuantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouseQuantities;
    }
}
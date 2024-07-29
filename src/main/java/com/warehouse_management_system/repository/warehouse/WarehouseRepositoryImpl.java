package com.warehouse_management_system.repository.warehouse;

import com.warehouse_management_system.config.DatabaseConfig;
import com.warehouse_management_system.model.Warehouse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseRepositoryImpl implements WarehouseRepository {

    @Override
    public void save(Warehouse warehouse) {
        String sql;
        if (warehouse.getUser_id().equals("null")) {
            sql = "INSERT INTO Warehouse (ware_id, ware_name, ware_location, decrypt_key) VALUES (?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO Warehouse (ware_id, ware_name, ware_location, user_id, decrypt_key) VALUES (?, ?, ?, ?, ?)";
        }
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, warehouse.getWare_id());
            stmt.setString(2, warehouse.getWare_name());
            stmt.setString(3, warehouse.getWare_location());
            if(warehouse.getUser_id().equals("null")) {
                stmt.setString(4, "x");
            } else {
                stmt.setString(4, warehouse.getUser_id());
                stmt.setString(5, "x");
            }

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
    public Warehouse findById(String wareId) {
        String sql = "SELECT * FROM Warehouse WHERE ware_id = ? AND record_status = 'ACTIVE'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, wareId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWare_id(rs.getString("ware_id"));
                warehouse.setWare_name(rs.getString("ware_name"));
                warehouse.setWare_location(rs.getString("ware_location"));
                warehouse.setUser_id(rs.getString("user_id"));
                return warehouse;
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return null;
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouses = new ArrayList<>();
        String sql = "SELECT * FROM Warehouse WHERE record_status = 'ACTIVE'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Warehouse warehouse = new Warehouse();
                warehouse.setWare_id(rs.getString("ware_id"));
                warehouse.setWare_name(rs.getString("ware_name"));
                warehouse.setWare_location(rs.getString("ware_location"));
                warehouse.setUser_id(rs.getString("user_id"));
                warehouses.add(warehouse);
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return warehouses;
    }

    @Override
    public void update(Warehouse warehouse) {
        String sql = "UPDATE Warehouse SET ware_name = ?, ware_location = ?, user_id = ? WHERE ware_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, warehouse.getWare_name());
            stmt.setString(2, warehouse.getWare_location());
            stmt.setString(3, warehouse.getUser_id());
            stmt.setString(4, warehouse.getWare_id());
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
    public void delete(String wareId) {
        String sql = "UPDATE Warehouse SET record_status = 'DELETED' WHERE ware_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, wareId);
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
}
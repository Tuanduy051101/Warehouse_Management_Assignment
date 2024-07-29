package com.warehouse_management_system.repository.attribute;

import com.warehouse_management_system.config.DatabaseConfig;
import com.warehouse_management_system.model.Attribute;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttributeRepositoryImpl implements AttributeRepository {

    @Override
    public void save(Attribute attribute) {
        String sql = "INSERT INTO Attribute (attr_id, attr_name, prod_id, decrypt_key) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attribute.getAttr_id());
            stmt.setString(2, attribute.getAttr_name());
            stmt.setString(3, attribute.getProd_id());
            stmt.setString(4, "x");
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
    public Attribute findById(String attrId) {
        String sql = "SELECT * FROM Attribute WHERE attr_id = ? AND record_status = 'ACTIVE'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attrId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Attribute attribute = new Attribute();
                attribute.setAttr_id(rs.getString("attr_id"));
                attribute.setAttr_name(rs.getString("attr_name"));
                attribute.setProd_id(rs.getString("prod_id"));
                attribute.setDecrypt_key(rs.getString("decrypt_key"));
                attribute.setRecord_status(rs.getString("record_status"));
                return attribute;
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return null;
    }

    @Override
    public List<Attribute> findAll() {
        List<Attribute> attributes = new ArrayList<>();
        String sql = "SELECT a.*, w.user_id " +
                "FROM Attribute a " +
                "JOIN Product p ON a.prod_id = p.prod_id " +
                "JOIN Warehouse w ON p.ware_id = w.ware_id " +
                "WHERE a.record_status = 'ACTIVE'";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Attribute attribute = new Attribute();
                attribute.setAttr_id(rs.getString("attr_id"));
                attribute.setAttr_name(rs.getString("attr_name"));
                attribute.setProd_id(rs.getString("prod_id"));
                attribute.setDecrypt_key(rs.getString("decrypt_key"));
                attribute.setRecord_status(rs.getString("record_status"));
                attribute.setUser_id(rs.getString("user_id"));
                attributes.add(attribute);
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return attributes;
    }

    @Override
    public void update(Attribute attribute) {
        String sql = "UPDATE Attribute SET attr_name = ?, prod_id = ? WHERE attr_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attribute.getAttr_name());
            stmt.setString(2, attribute.getProd_id());
            stmt.setString(3, attribute.getAttr_id());
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
    public void delete(String attrId) {
        String sql = "UPDATE Attribute SET record_status = 'DELETED' WHERE attr_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attrId);
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
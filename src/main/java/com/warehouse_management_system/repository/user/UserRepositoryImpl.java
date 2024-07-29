package com.warehouse_management_system.repository.user;

import com.warehouse_management_system.config.DatabaseConfig;
import com.warehouse_management_system.model.User;
import com.warehouse_management_system.util.encrypt.Base64Util;
import com.warehouse_management_system.util.encrypt.SHAUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(User user) {
        String sql = "INSERT INTO Users (user_id, user_username, user_password, user_role, decrypt_key) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, user.getUser_id());
            stmt.setString(2, user.getUser_username());
            stmt.setString(3, user.getUser_password());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getDecrypt_key());
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
    public User findById(String userId) {
        String sql = "SELECT * FROM Users WHERE user_id = ? AND record_status = 'ACTIVE'";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUser_username(rs.getString("user_username"));
                user.setUser_password(rs.getString("user_password"));
                user.setRole(rs.getString("user_role"));
                user.setDecrypt_key(rs.getString("decrypt_key"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE record_status = 'ACTIVE'";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUser_username(rs.getString("user_username"));
                user.setUser_password(rs.getString("user_password"));
                user.setRole(rs.getString("user_role"));
                user.setDecrypt_key(rs.getString("decrypt_key"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Users SET user_username = ?, user_password = ?, user_role = ? WHERE user_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUser_username());
            stmt.setString(2, user.getUser_password());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getUser_id());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
            } else {
                System.out.println("Record already exists.");
            }
        }catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
    }

    @Override
    public void delete(String userId) {
        String sql = "UPDATE Users SET record_status = 'DELETED' Where user_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, userId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Deleted successfully.");
            } else {
                System.out.println("Record already exists.");
            }
        }catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        }
    }

    @Override
    public User login(String username, String password) {
        String sql = "SELECT * FROM Users Where user_username = ?";
        try(Connection conn = DatabaseConfig.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getString("user_id"));
                user.setUser_username(rs.getString("user_username"));
                user.setRole(rs.getString("user_role"));
                user.setUser_password(rs.getString("user_password"));
                user.setDecrypt_key(rs.getString("decrypt_key"));
                if(SHAUtil.checkPassword(password, user.getUser_password(), Base64Util.decode(user.getDecrypt_key()))) {
                    return user;
                }
                return user;
            }
        } catch (SQLException e) {
            System.out.println("This function has failed. " + e.getMessage());
            System.out.println("Please try another function.");
        } catch (Exception e) {
            System.out.println("This function has failed. " + e.getMessage());
        }
        return null;
    }
}
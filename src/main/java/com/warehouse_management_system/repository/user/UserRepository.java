package com.warehouse_management_system.repository.user;

import com.warehouse_management_system.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    User findById(String userId);
    List<User> findAll();
    void update(User user);
    void delete(String userId);
    User login(String username, String password);
}
package com.warehouse_management_system.controller;

import com.warehouse_management_system.model.User;
import com.warehouse_management_system.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService = new UserService();

    public void createUser(User user) {
        userService.saveUser(user);
    }

    public User getUser(String userId) {
        return userService.getUserById(userId);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public void deleteUser(String userId) {
        userService.deleteUser(userId);
    }

    public User login(String username, String password) {
        return userService.login(username, password);
    }
}
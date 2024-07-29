package com.warehouse_management_system.service;

import com.warehouse_management_system.model.User;
import com.warehouse_management_system.repository.user.UserRepository;
import com.warehouse_management_system.repository.user.UserRepositoryImpl;
import com.warehouse_management_system.util.DatabaseUtil;
import com.warehouse_management_system.util.encrypt.Base64Util;
import com.warehouse_management_system.util.encrypt.SHAUtil;

import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void saveUser(User user) {
        user.setUser_id(DatabaseUtil.generateId());
        byte[] salt = SHAUtil.generateSalt();
        user.setDecrypt_key(Base64Util.encode(salt));
        try {
            String passwordEncrypted = SHAUtil.hashPassword(user.getUser_password(), salt);
            user.setUser_password(passwordEncrypted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        userRepository.save(user);
    }

    public User getUserById(String userId) {
       return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(String userId) {
        userRepository.delete(userId);
    }

    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

}
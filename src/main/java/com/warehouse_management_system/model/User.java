package com.warehouse_management_system.model;

public class User {
    private String user_id;
    private String user_username;
    private String user_password;
    private String decrypt_key;
    private String record_status;
    private String role;

    // Setters and Getters

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_username() {
        return user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDecrypt_key() {
        return decrypt_key;
    }

    public void setDecrypt_key(String decrypt_key) {
        this.decrypt_key = decrypt_key;
    }

    public String getRecord_status() {
        return record_status;
    }

    public void setRecord_status(String record_status) {
        this.record_status = record_status;
    }

    @Override
    public String toString() {
        return "{ 'id': " + getUser_id() + ", 'username': "
                + getUser_username() + ", 'password': " + getUser_password() + ", 'role': " + getRole() + " }";
    }
}
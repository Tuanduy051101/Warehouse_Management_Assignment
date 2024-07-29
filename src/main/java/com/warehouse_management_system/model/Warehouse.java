package com.warehouse_management_system.model;

public class Warehouse {
    private String ware_id;
    private String ware_name;
    private String ware_location;
    private String user_id;
    private String decrypt_key;
    private String record_status;

    // Setters and Getters

    public String getWare_id() {
        return ware_id;
    }

    public void setWare_id(String ware_id) {
        this.ware_id = ware_id;
    }

    public String getWare_name() {
        return ware_name;
    }

    public void setWare_name(String ware_name) {
        this.ware_name = ware_name;
    }

    public String getWare_location() {
        return ware_location;
    }

    public void setWare_location(String ware_location) {
        this.ware_location = ware_location;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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
        return "{ 'id': " + getWare_id() + ", 'name': "
                + getWare_name() + ", 'location': " + getWare_location() + ", 'userID': " + getUser_id() +  " }";
    }
}
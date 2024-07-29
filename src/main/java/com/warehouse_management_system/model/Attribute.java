package com.warehouse_management_system.model;

public class Attribute {
    private String attr_id;
    private String attr_name;
    private String prod_id;
    private String decrypt_key;
    private String record_status;
    private String user_id;

    // Setters and Getters

    public String getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(String attr_id) {
        this.attr_id = attr_id;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "{ 'id': " + getAttr_id() + ", 'name': " + getAttr_name() + ", 'product_id': " + getProd_id() + " }";
    }
}
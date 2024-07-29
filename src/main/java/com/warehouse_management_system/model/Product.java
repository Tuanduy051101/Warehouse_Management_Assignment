package com.warehouse_management_system.model;

public class Product {
    private String prod_id;
    private String prod_name;
    private int prod_quantity;
    private double prod_cost;
    private String ware_id;
    private String decrypt_key;
    private String record_status;
    private String user_id;

    // Setters and Getters


    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public int getProd_quantity() {
        return prod_quantity;
    }

    public void setProd_quantity(int prod_quantity) {
        this.prod_quantity = prod_quantity;
    }

    public double getProd_cost() {
        return prod_cost;
    }

    public void setProd_cost(double prod_cost) {
        this.prod_cost = prod_cost;
    }

    public String getWare_id() {
        return ware_id;
    }

    public void setWare_id(String ware_id) {
        this.ware_id = ware_id;
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
        return "{ 'id': " + getProd_id() + ", 'name': " + getProd_name() + ", 'quantity': " + getProd_quantity() +
                ", 'cost': " + getProd_cost() + ", 'warehouse_id': " + getWare_id() + " }";
    }
}
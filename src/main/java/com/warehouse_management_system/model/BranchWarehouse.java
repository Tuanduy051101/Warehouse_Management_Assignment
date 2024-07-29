package com.warehouse_management_system.model;

public class BranchWarehouse {
    private String branchW_id;
    private String branchW_name;
    private String branchW_location;
    private String ware_id;

    // Setters and Getters

    public String getBranchW_id() {
        return branchW_id;
    }

    public void setBranchW_id(String branchW_id) {
        this.branchW_id = branchW_id;
    }

    public String getBranchW_name() {
        return branchW_name;
    }

    public void setBranchW_name(String branchW_name) {
        this.branchW_name = branchW_name;
    }

    public String getBranchW_location() {
        return branchW_location;
    }

    public void setBranchW_location(String branchW_location) {
        this.branchW_location = branchW_location;
    }

    public String getWare_id() {
        return ware_id;
    }

    public void setWare_id(String ware_id) {
        this.ware_id = ware_id;
    }
}
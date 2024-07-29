package com.warehouse_management_system.repository.attribute;

import com.warehouse_management_system.model.Attribute;

import java.util.List;

public interface AttributeRepository {
    void save(Attribute attribute);
    Attribute findById(String attrId);
    List<Attribute> findAll();
    void update(Attribute attribute);
    void delete(String attrId);
}
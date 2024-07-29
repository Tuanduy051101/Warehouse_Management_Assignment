package com.warehouse_management_system.service;

import com.warehouse_management_system.model.Attribute;
import com.warehouse_management_system.repository.attribute.AttributeRepository;
import com.warehouse_management_system.repository.attribute.AttributeRepositoryImpl;
import com.warehouse_management_system.util.DatabaseUtil;

import java.util.List;

public class AttributeService {
    private final AttributeRepository attributeRepository = new AttributeRepositoryImpl();

    public void saveAttribute(Attribute attribute) {
        attribute.setAttr_id(DatabaseUtil.generateId());
        attribute.setDecrypt_key("empty");
        attribute.setRecord_status("ACTIVE");
        attributeRepository.save(attribute);
    }

    public Attribute getAttributeById(String attrId) {
        return attributeRepository.findById(attrId);
    }

    public List<Attribute> getAllAttributes() {
        return attributeRepository.findAll();
    }

    public void updateAttribute(Attribute attribute) {
        attributeRepository.update(attribute);
    }

    public void deleteAttribute(String attrId) {
        attributeRepository.delete(attrId);
    }
}
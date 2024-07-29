package com.warehouse_management_system.controller;

import com.warehouse_management_system.model.Attribute;
import com.warehouse_management_system.service.AttributeService;

import java.util.List;

public class AttributeController {
    private AttributeService attributeService = new AttributeService();

    public void createAttribute(Attribute attribute) {
        attributeService.saveAttribute(attribute);
    }

    public Attribute getAttribute(String attrId) {
        return attributeService.getAttributeById(attrId);
    }

    public List<Attribute> getAllAttributes() {
        return attributeService.getAllAttributes();
    }

    public void updateAttribute(Attribute attribute) {
        attributeService.updateAttribute(attribute);
    }

    public void deleteAttribute(String attrId) {
        attributeService.deleteAttribute(attrId);
    }
}
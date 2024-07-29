package com.warehouse_management_system.util;

import java.util.UUID;

public class DatabaseUtil {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}

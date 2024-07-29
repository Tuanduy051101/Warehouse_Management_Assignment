-- 1. Users
CREATE TABLE Users
(
    user_id       VARCHAR2(100) NOT NULL PRIMARY KEY,
    user_username VARCHAR2(100) NOT NULL,
    user_password VARCHAR2(100) NOT NULL,
    user_role     VARCHAR2(20) NOT NULL,
    decrypt_key   VARCHAR2(100) NOT NULL,
    record_status VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT chk_user_role CHECK (user_role IN ('admin', 'user')),
    CONSTRAINT chk_user_status CHECK (record_status IN ('ACTIVE', 'DELETED'))
);

-- 2. Warehouse
CREATE TABLE Warehouse
(
    ware_id       VARCHAR2(100) NOT NULL PRIMARY KEY,
    ware_name     VARCHAR2(200) NOT NULL,
    ware_location VARCHAR2(500) NOT NULL,
    user_id       VARCHAR2(100),
    decrypt_key   VARCHAR2(100) NOT NULL,
    record_status VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES Users (user_id),
    CONSTRAINT chk_warehouse_status CHECK (record_status IN ('ACTIVE', 'DELETED'))
);

-- 3. Branch_Warehouse
CREATE TABLE Branch_Warehouse
(
    branchW_id       VARCHAR2(100) NOT NULL PRIMARY KEY,
    branchW_name     VARCHAR2(200) NOT NULL,
    branchW_location VARCHAR2(500) NOT NULL,
    ware_id          VARCHAR2(100) NOT NULL,
    decrypt_key      VARCHAR2(100) NOT NULL,
    record_status    VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT fk_warehouse_branch FOREIGN KEY (ware_id) REFERENCES Warehouse (ware_id),
    CONSTRAINT chk_branch_status CHECK (record_status IN ('ACTIVE', 'DELETED'))
);

-- 4. Product
CREATE TABLE Product
(
    prod_id       VARCHAR2(100) NOT NULL PRIMARY KEY,
    prod_name     VARCHAR2(200) NOT NULL,
    prod_quantity NUMBER(10) NOT NULL,
    prod_cost     NUMBER(10,2) NOT NULL,
    ware_id       VARCHAR2(100) NOT NULL,
    decrypt_key   VARCHAR2(100) NOT NULL,
    record_status VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT fk_warehouse_product FOREIGN KEY (ware_id) REFERENCES Warehouse (ware_id),
    CONSTRAINT chk_prod_quantity CHECK (prod_quantity >= 0),
    CONSTRAINT chk_prod_cost CHECK (prod_cost >= 0),
    CONSTRAINT chk_product_status CHECK (record_status IN ('ACTIVE', 'DELETED'))
);

-- 5. Attribute
CREATE TABLE Attribute
(
    attr_id       VARCHAR2(100) NOT NULL PRIMARY KEY,
    attr_name     VARCHAR2(200) NOT NULL,
    prod_id       VARCHAR2(100) NOT NULL,
    decrypt_key   VARCHAR2(100) NOT NULL,
    record_status VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (prod_id) REFERENCES Product (prod_id),
    CONSTRAINT chk_attribute_status CHECK (record_status IN ('ACTIVE', 'DELETED'))
);

-- 6. Log
CREATE TABLE Data_Change_Log
(
    log_id         NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    table_name     VARCHAR2(100) NOT NULL,
    record_id      VARCHAR2(100) NOT NULL,
    action         VARCHAR2(20) NOT NULL,
    changed_column VARCHAR2(100),
    old_value      CLOB,
    new_value      CLOB,
    change_date    TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    changed_by     VARCHAR2(100),
    CONSTRAINT chk_action CHECK (action IN ('INSERT', 'UPDATE', 'DELETE'))
);

-- Tạo các index cho các khóa ngoại và trạng thái bản ghi để tối ưu hiệu suất truy vấn
CREATE INDEX idx_warehouse_user ON Warehouse (user_id);
CREATE INDEX idx_branch_warehouse ON Branch_Warehouse (ware_id);
CREATE INDEX idx_product_warehouse ON Product (ware_id);
CREATE INDEX idx_attribute_product ON Attribute (prod_id);
CREATE INDEX idx_users_status ON Users (record_status);
CREATE INDEX idx_warehouse_status ON Warehouse (record_status);
CREATE INDEX idx_branch_status ON Branch_Warehouse (record_status);
CREATE INDEX idx_product_status ON Product (record_status);
CREATE INDEX idx_attribute_status ON Attribute (record_status);
CREATE INDEX idx_data_change_log ON Data_Change_Log (table_name, record_id);

-- tạo các trigger cho mỗi bảng để tự động ghi lại các thay đổi
CREATE
OR REPLACE TRIGGER trg_users_audit
AFTER INSERT OR
UPDATE OR
DELETE
ON Users
    FOR EACH ROW
DECLARE
v_action VARCHAR2(10);
BEGIN
    IF
INSERTING THEN
        v_action := 'INSERT';
    ELSIF
UPDATING THEN
        v_action := 'UPDATE';
    ELSIF
DELETING THEN
        v_action := 'DELETE';
END IF;

    -- Log cho mỗi cột được thay đổi
    IF
INSERTING OR DELETING THEN
        INSERT INTO Data_Change_Log (table_name, record_id, action, changed_column, old_value, new_value, changed_by)
        VALUES ('Users', :NEW.user_id, v_action, 'ALL', NULL, NULL, SYS_CONTEXT('USERENV', 'SESSION_USER'));
ELSE
        IF UPDATING('user_username') THEN
            INSERT INTO Data_Change_Log (table_name, record_id, action, changed_column, old_value, new_value, changed_by)
            VALUES ('Users', :OLD.user_id, v_action, 'user_username', :OLD.user_username, :NEW.user_username, SYS_CONTEXT('USERENV', 'SESSION_USER'));
END IF;

        IF
UPDATING('user_password') THEN
            INSERT INTO Data_Change_Log (table_name, record_id, action, changed_column, old_value, new_value, changed_by)
            VALUES ('Users', :OLD.user_id, v_action, 'user_password', :OLD.user_password, :NEW.user_password, SYS_CONTEXT('USERENV', 'SESSION_USER'));
END IF;

        IF
UPDATING('user_role') THEN
            INSERT INTO Data_Change_Log (table_name, record_id, action, changed_column, old_value, new_value, changed_by)
            VALUES ('Users', :OLD.user_id, v_action, 'user_role', :OLD.user_role, :NEW.user_role, SYS_CONTEXT('USERENV', 'SESSION_USER'));
END IF;

        IF
UPDATING('record_status') THEN
            INSERT INTO Data_Change_Log (table_name, record_id, action, changed_column, old_value, new_value, changed_by)
            VALUES ('Users', :OLD.user_id, v_action, 'record_status', :OLD.record_status, :NEW.record_status, SYS_CONTEXT('USERENV', 'SESSION_USER'));
END IF;
END IF;
END;
/

-- Xem lịch sử thay đổi của một bản ghi cụ thể
SELECT * FROM Data_Change_Log
WHERE table_name = 'Users' AND record_id = 'U001'
ORDER BY change_date DESC;

-- Xem tất cả các thay đổi trong một khoảng thời gian
SELECT * FROM Data_Change_Log
WHERE change_date BETWEEN TO_TIMESTAMP('2023-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')
          AND TO_TIMESTAMP('2023-12-31 23:59:59', 'YYYY-MM-DD HH24:MI:SS')
ORDER BY change_date DESC;

-- Xem tất cả các thao tác xóa
SELECT * FROM Data_Change_Log
WHERE action = 'DELETE'
ORDER BY change_date DESC;
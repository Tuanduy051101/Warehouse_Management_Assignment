-- 1. User
INSERT INTO "User" ("user_id", "user_username", "user_password", "user_role") VALUES ('U001', 'admin_user', 'password123', 'admin');
INSERT INTO "User" ("user_id", "user_username", "user_password", "user_role") VALUES ('U002', 'regular_user', 'password456', 'user');

-- 2. Warehouse
INSERT INTO "Warehouse" ("ware_id", "ware_name", "ware_location", "user_id") VALUES ('W001', 'Main Warehouse', '123 Main St', 'U001');
INSERT INTO "Warehouse" ("ware_id", "ware_name", "ware_location", "user_id") VALUES ('W002', 'Secondary Warehouse', '456 Secondary St', 'U002');

-- 3. BranchWarehouse
INSERT INTO "BranchWarehouse" ("branchW_id", "branchW_name", "branchW_location", "ware_id") VALUES ('BW001', 'Branch 1', '789 Branch St', 'W001');
INSERT INTO "BranchWarehouse" ("branchW_id", "branchW_name", "branchW_location", "ware_id") VALUES ('BW002', 'Branch 2', '101 Branch St', 'W002');

-- 4. Product
INSERT INTO "Product" ("prod_id", "prod_name", "prod_quantity", "prod_cost", "ware_id") VALUES ('P001', 'Product 1', 100, 10.50, 'W001');
INSERT INTO "Product" ("prod_id", "prod_name", "prod_quantity", "prod_cost", "ware_id") VALUES ('P002', 'Product 2', 200, 20.75, 'W002');

-- 5. Attribute
INSERT INTO "Attribute" ("attr_id", "attr_name", "prod_id") VALUES ('A001', 'Color', 'P001');
INSERT INTO "Attribute" ("attr_id", "attr_name", "prod_id") VALUES ('A002', 'Size', 'P002');
4e1bc08c-f8c9-406e-8ff6-6b1cc8bd56f4
c820962a-6641-47a8-96a6-71dc9d12cf17
        a78306bd-34b1-49db-869e-987a189129cb
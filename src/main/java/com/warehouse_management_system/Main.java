package com.warehouse_management_system;

import com.warehouse_management_system.controller.AttributeController;
import com.warehouse_management_system.controller.ProductController;
import com.warehouse_management_system.controller.UserController;
import com.warehouse_management_system.controller.WarehouseController;
import com.warehouse_management_system.model.Attribute;
import com.warehouse_management_system.model.User;
import com.warehouse_management_system.model.Warehouse;
import com.warehouse_management_system.model.Product;
import com.warehouse_management_system.util.io.ExcelUtil;
import com.warehouse_management_system.util.io.InputValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    // Khai báo các đối tượng controller cho các thành phần khác nhau của hệ thống
    private static final ProductController productController = new ProductController();
    private static final UserController userController = new UserController();
    private static final WarehouseController warehouseController = new WarehouseController();
    private static final AttributeController attributeController = new AttributeController();

    // Khai báo đối tượng người dùng đăng nhập
    private static User user_login = new User();

    public static void main(String[] args) {
        // Kiểm tra đăng nhập
        if (login()) {
            // Nếu đăng nhập thành công và người dùng không null
            if(user_login != null) {
                // Hiển thị giao diện chính
                displayMain();
            }
        }
    }

    private static void displayMain() {
        // Biến để kiểm soát vòng lặp hiển thị menu chính
        boolean isDisplayMain = true;

        // Vòng lặp để hiển thị menu chính cho đến khi người dùng chọn thoát
        while (isDisplayMain) {
            // Hiển thị tiêu đề và các tùy chọn của hệ thống quản lý kho
            System.out.println("WAREHOUSE MANAGEMENT SYSTEM");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Warehouses");
            System.out.println("3. Manage Warehouse Branches");
            System.out.println("4. Manage Products");
            System.out.println("5. Manage Attributes");
            System.out.println("6. Logout");
            System.out.println("0. Exit");

            // Nhận lựa chọn của người dùng và kiểm tra tính hợp lệ
            int choice = InputValidator.getValidInt("Choose a function: ");

            // Xử lý lựa chọn của người dùng
            switch (choice) {
                case 1:
                    // Nếu người dùng có quyền admin, hiển thị menu quản lý người dùng
                    if (user_login.getRole().equals("admin")) {
                        displayUserMenu();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 2:
                    // Hiển thị menu quản lý kho
                    displayWarehouseMenu();
                    break;
                case 3:
                    // Hiển thị menu quản lý chi nhánh kho
                    displayWarehouseBranchMenu();
                    break;
                case 4:
                    // Hiển thị menu quản lý sản phẩm
                    displayProductMenu();
                    break;
                case 5:
                    // Hiển thị menu quản lý thuộc tính
                    displayAttributeMenu();
                    break;
                case 6:
                    // Đăng xuất
                    logout();
                    break;
                case 0:
                    // Thoát khỏi hệ thống
                    System.out.println("Exiting the system. Goodbye!");
                    isDisplayMain = false;
                    break;
                default:
                    // Thông báo lựa chọn không hợp lệ
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void logout() {
        // Đặt đối tượng người dùng đăng nhập thành null để đăng xuất
        user_login = null;
        System.out.println("Logout successfully.");

        // Thực hiện đăng nhập lại
        if (login()) {
            // Nếu đăng nhập thành công và người dùng không null
            if(user_login != null) {
                // Hiển thị giao diện chính
                displayMain();
            }
        }
    }

    private static void displayUserMenu() {
        // Biến để kiểm soát vòng lặp hiển thị menu quản lý người dùng
        boolean isDisplayUserMenu = true;

        // Vòng lặp để hiển thị menu quản lý người dùng cho đến khi người dùng chọn quay lại
        while (isDisplayUserMenu) {
            // Hiển thị tiêu đề và các tùy chọn của chức năng quản lý người dùng
            System.out.println("You are performing the function: Manage Users");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Edit User");
            System.out.println("4. Delete User");
            System.out.println("0. Back");

            // Nhận lựa chọn của người dùng và kiểm tra tính hợp lệ
            int choice = InputValidator.getValidInt("Choose a function: ");

            // Xử lý lựa chọn của người dùng
            switch (choice) {
                case 1:
                    // Thêm người dùng mới
                    addUser();
                    break;
                case 2:
                    // Xem danh sách người dùng
                    viewUsers();
                    break;
                case 3:
                    // Chỉnh sửa thông tin người dùng
                    editUser();
                    break;
                case 4:
                    // Xóa người dùng
                    deleteUser();
                    break;
                case 0:
                    // Quay lại menu chính
                    isDisplayUserMenu = false;
                    break;
                default:
                    // Thông báo lựa chọn không hợp lệ
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayWarehouseMenu() {
        // Biến để kiểm soát vòng lặp hiển thị menu quản lý kho
        boolean isDisplayWarehouseMenu = true;

        // Vòng lặp để hiển thị menu quản lý kho cho đến khi người dùng chọn quay lại
        while (isDisplayWarehouseMenu) {
            // Hiển thị tiêu đề và các tùy chọn của chức năng quản lý kho
            System.out.println("You are performing the function: Manage Warehouses");
            System.out.println("1. Add Warehouse");
            System.out.println("2. View Warehouses");
            System.out.println("3. Edit Warehouse");
            System.out.println("4. Delete Warehouse");
            System.out.println("0. Back");

            // Nhận lựa chọn của người dùng và kiểm tra tính hợp lệ
            int choice = InputValidator.getValidInt("Choose a function: ");

            // Xử lý lựa chọn của người dùng
            switch (choice) {
                case 1:
                    // Nếu người dùng có quyền admin, thêm kho mới
                    if (user_login.getRole().equals("admin")) {
                        addWarehouse();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 2:
                    // Xem danh sách kho
                    viewWarehouses();
                    break;
                case 3:
                    // Nếu người dùng có quyền admin, chỉnh sửa thông tin kho
                    if (user_login.getRole().equals("admin")) {
                        editWarehouse();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 4:
                    // Nếu người dùng có quyền admin, xóa kho
                    if (user_login.getRole().equals("admin")) {
                        deleteWarehouse();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 0:
                    // Quay lại menu chính
                    isDisplayWarehouseMenu = false;
                    break;
                default:
                    // Thông báo lựa chọn không hợp lệ
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayWarehouseBranchMenu() {
        // Biến để kiểm soát vòng lặp hiển thị menu quản lý chi nhánh kho
        boolean isDisplayWarehouseBranchMenu = true;

        // Vòng lặp để hiển thị menu quản lý chi nhánh kho cho đến khi người dùng chọn quay lại
        while (isDisplayWarehouseBranchMenu) {
            // Hiển thị tiêu đề và các tùy chọn của chức năng quản lý chi nhánh kho
            System.out.println("You are performing the function: Manage Warehouse Branches");
            System.out.println("1. Add Warehouse Branch");
            System.out.println("2. View Warehouse Branches");
            System.out.println("3. Edit Warehouse Branch");
            System.out.println("4. Delete Warehouse Branch");
            System.out.println("0. Back");

            // Nhận lựa chọn của người dùng và kiểm tra tính hợp lệ
            int choice = InputValidator.getValidInt("Choose a function: ");

            // Xử lý lựa chọn của người dùng
            switch (choice) {
                case 1:
                    // Thêm chi nhánh kho mới
                    addWarehouseBranch();
                    break;
                case 2:
                    // Xem danh sách chi nhánh kho
                    viewWarehouseBranches();
                    break;
                case 3:
                    // Chỉnh sửa thông tin chi nhánh kho
                    editWarehouseBranch();
                    break;
                case 4:
                    // Xóa chi nhánh kho
                    deleteWarehouseBranch();
                    break;
                case 0:
                    // Quay lại menu chính
                    isDisplayWarehouseBranchMenu = false;
                    break;
                default:
                    // Thông báo lựa chọn không hợp lệ
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayProductMenu() {
        // Biến để kiểm soát vòng lặp hiển thị menu quản lý sản phẩm
        boolean isDisplayProductMenu = true;

        // Vòng lặp để hiển thị menu quản lý sản phẩm cho đến khi người dùng chọn quay lại
        while (isDisplayProductMenu) {
            // Hiển thị tiêu đề và các tùy chọn của chức năng quản lý sản phẩm
            System.out.println("You are performing the function: Manage Products");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Edit Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Add products.");
            System.out.println("6. Total products quantity for User.");
            System.out.println("7. Total products quantity for Admin.");
            System.out.println("0. Back");

            // Nhận lựa chọn của người dùng và kiểm tra tính hợp lệ
            int choice = InputValidator.getValidInt("Choose a function: ");

            // Xử lý lựa chọn của người dùng
            switch (choice) {
                case 1:
                    // Nếu người dùng có quyền admin, thêm sản phẩm mới
                    if (user_login.getRole().equals("admin")) {
                        addProduct();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 2:
                    // Xem danh sách sản phẩm
                    viewProducts();
                    break;
                case 3:
                    // Nếu người dùng có quyền admin, chỉnh sửa thông tin sản phẩm
                    if (user_login.getRole().equals("admin")) {
                        editProduct();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 4:
                    // Nếu người dùng có quyền admin, xóa sản phẩm
                    if (user_login.getRole().equals("admin")) {
                        deleteProduct();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 5:
                    // Thêm nhiều sản phẩm
                    addProducts();
                    break;
                case 6:
                    // Nếu người dùng có quyền user, báo cáo số lượng sản phẩm trong kho
                    if(user_login.getRole().equals("user")) {
                        reportProductWarehouse();
                    } else {
                        // Nếu không có quyền user, thông báo yêu cầu quyền user
                        System.out.println("Requires user rights.");
                    }
                    break;
                case 7:
                    // Nếu người dùng có quyền admin, báo cáo số lượng sản phẩm trong tất cả các kho
                    if(user_login.getRole().equals("admin")) {
                        reportProductAllWarehouse();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 0:
                    // Quay lại menu chính
                    isDisplayProductMenu = false;
                    break;
                default:
                    // Thông báo lựa chọn không hợp lệ
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayAttributeMenu() {
        // Biến để kiểm soát vòng lặp hiển thị menu quản lý thuộc tính
        boolean isDisplayAttributeMenu = true;

        // Vòng lặp để hiển thị menu quản lý thuộc tính cho đến khi người dùng chọn quay lại
        while (isDisplayAttributeMenu) {
            // Hiển thị tiêu đề và các tùy chọn của chức năng quản lý thuộc tính
            System.out.println("You are performing the function: Manage Attributes");
            System.out.println("1. Add Attribute");
            System.out.println("2. View Attributes");
            System.out.println("3. Edit Attribute");
            System.out.println("4. Delete Attribute");
            System.out.println("0. Back");

            // Nhận lựa chọn của người dùng và kiểm tra tính hợp lệ
            int choice = InputValidator.getValidInt("Choose a function: ");

            // Xử lý lựa chọn của người dùng
            switch (choice) {
                case 1:
                    // Nếu người dùng có quyền admin, thêm thuộc tính mới
                    if (user_login.getRole().equals("admin")) {
                        addAttribute();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 2:
                    // Xem danh sách thuộc tính
                    viewAttributes();
                    break;
                case 3:
                    // Nếu người dùng có quyền admin, chỉnh sửa thông tin thuộc tính
                    if (user_login.getRole().equals("admin")) {
                        editAttribute();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 4:
                    // Nếu người dùng có quyền admin, xóa thuộc tính
                    if (user_login.getRole().equals("admin")) {
                        deleteAttribute();
                    } else {
                        // Nếu không có quyền admin, thông báo yêu cầu quyền admin
                        System.out.println("Requires administrative rights.");
                    }
                    break;
                case 0:
                    // Quay lại menu chính
                    isDisplayAttributeMenu = false;
                    break;
                default:
                    // Thông báo lựa chọn không hợp lệ
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void addUser() {
        // Tạo đối tượng người dùng mới
        User user = new User();
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        roleList.add("user");
        System.out.println("You are performing the function: Add menu");

        // Nhập thông tin người dùng
        user.setUser_username(InputValidator.getValidString("Enter username: "));
        user.setUser_password(InputValidator.getValidString("Enter password: "));
        user.setRole(InputValidator.getValidSelect("Select role: ", roleList));

        // Tạo người dùng mới thông qua controller
        userController.createUser(user);
    }

    private static void viewUsers() {
        System.out.println("You are performing the function: View Users");

        // Lấy danh sách tất cả người dùng
        List<User> users = userController.getAllUsers();
        System.out.println("List of users: ");
        System.out.println("[");
        System.out.println("Total records: " + users.size());

        // Hiển thị danh sách người dùng
        users.forEach(System.out::println);
        System.out.println("]");
    }

    private static void editUser() {
        System.out.println("You are performing the function: Edit User");
        String userId = InputValidator.getValidString("Enter userId: ");

        // Lấy thông tin người dùng theo userId
        User user = userController.getUser(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        // Hiển thị thông tin hiện tại của người dùng
        System.out.println("Current user information:");
        System.out.println("User ID: " + user.getUser_id());
        System.out.println("Username: " + user.getUser_username());
        System.out.println("Password: " + user.getUser_password());
        System.out.println("Role: " + user.getRole());

        boolean isDisplayEditUser = true;
        while (isDisplayEditUser) {
            System.out.println("List of fields you can edit: ");
            System.out.println("1. Edit username");
            System.out.println("2. Edit password");
            System.out.println("3. Edit role");
            System.out.println("0. Back");

            int choice = InputValidator.getValidInt("Choose a field: ");

            switch (choice) {
                case 1: {
                    String newUsername = InputValidator.getValidString("Enter new username: ");
                    if (!newUsername.isEmpty()) {
                        user.setUser_username(newUsername);
                        System.out.println("Username updated successfully.");
                    } else {
                        System.out.println("Username not changed.");
                    }
                    break;
                }
                case 2: {
                    String newPassword = InputValidator.getValidString("Enter new password: ");
                    if (!newPassword.isEmpty()) {
                        user.setUser_password(newPassword);
                        System.out.println("Password updated successfully.");
                    } else {
                        System.out.println("Password not changed.");
                    }
                    break;
                }
                case 3: {
                    String newRole = InputValidator.getValidString("Enter new role: ");
                    if (!newRole.isEmpty()) {
                        user.setRole(newRole);
                        System.out.println("Role updated successfully.");
                    } else {
                        System.out.println("Role not changed.");
                    }
                    break;
                }
                case 0: {
                    isDisplayEditUser = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Cập nhật thông tin người dùng
        userController.updateUser(user);
        System.out.println("User information updated successfully.");
    }

    private static void deleteUser() {
        System.out.println("You are performing the function: Delete User");
        String userId = InputValidator.getValidString("Enter userId: ");

        // Lấy thông tin người dùng theo userId
        User user = userController.getUser(userId);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        // Xóa người dùng
        userController.deleteUser(userId);
        System.out.println("User deleted successfully.");
    }

    private static void addWarehouse() {
        // Tạo đối tượng kho mới
        Warehouse warehouse = new Warehouse();
        System.out.println("You are performing the function: Add Warehouse");

        // Nhập thông tin kho
        warehouse.setWare_name(InputValidator.getValidString("Enter warehouse name: "));
        warehouse.setWare_location(InputValidator.getValidString("Enter warehouse location: "));
        warehouse.setUser_id(InputValidator.getValidString("Enter user ID: "));

        // Tạo kho mới thông qua controller
        warehouseController.createWarehouse(warehouse);
    }

    private static void viewWarehouses() {
        System.out.println("You are performing the function: View Warehouses");

        // Lấy danh sách tất cả các kho
        List<Warehouse> warehouses = warehouseController.getAllWarehouses();
        List<Warehouse> warehousesFiltered = new ArrayList<>();

        // Lọc danh sách kho theo quyền của người dùng
        if (user_login.getRole().equals("user")) {
            warehousesFiltered = warehouses.stream().filter(e -> e.getUser_id().equals(user_login.getUser_id())).toList();
        } else {
            warehousesFiltered = warehouses;
        }

        System.out.println("List of warehouses: ");
        System.out.println("[");
        System.out.println("Total records: " + warehousesFiltered.size());

        // Hiển thị danh sách kho
        warehousesFiltered.forEach(System.out::println);
        System.out.println("]");
    }

    private static void editWarehouse() {
        System.out.println("You are performing the function: Edit Warehouse");
        String wareId = InputValidator.getValidString("Enter warehouse ID: ");

        // Lấy thông tin kho theo wareId
        Warehouse warehouse = warehouseController.getWarehouse(wareId);

        if (warehouse == null) {
            System.out.println("Warehouse not found.");
            return;
        }

        // Hiển thị thông tin hiện tại của kho
        System.out.println("Current warehouse information:");
        System.out.println("Warehouse ID: " + warehouse.getWare_id());
        System.out.println("Warehouse Name: " + warehouse.getWare_name());
        System.out.println("Warehouse Location: " + warehouse.getWare_location());
        System.out.println("User ID: " + warehouse.getUser_id());

        boolean isDisplayEditWarehouse = true;
        while (isDisplayEditWarehouse) {
            System.out.println("List of fields you can edit: ");
            System.out.println("1. Edit warehouse name");
            System.out.println("2. Edit warehouse location");
            System.out.println("3. Edit user ID");
            System.out.println("0. Back");

            int choice = InputValidator.getValidInt("Choose a field: ");

            switch (choice) {
                case 1: {
                    String newName = InputValidator.getValidString("Enter new warehouse name: ");
                    if (!newName.isEmpty()) {
                        warehouse.setWare_name(newName);
                        System.out.println("Warehouse name updated successfully.");
                    } else {
                        System.out.println("Warehouse name not changed.");
                    }
                    break;
                }
                case 2: {
                    String newLocation = InputValidator.getValidString("Enter new warehouse location: ");
                    if (!newLocation.isEmpty()) {
                        warehouse.setWare_location(newLocation);
                        System.out.println("Warehouse location updated successfully.");
                    } else {
                        System.out.println("Warehouse location not changed.");
                    }
                    break;
                }
                case 3: {
                    String newUserId = InputValidator.getValidString("Enter new user ID: ");
                    if (!newUserId.isEmpty()) {
                        warehouse.setUser_id(newUserId);
                        System.out.println("User ID updated successfully.");
                    } else {
                        System.out.println("User ID not changed.");
                    }
                    break;
                }
                case 0: {
                    isDisplayEditWarehouse = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Cập nhật thông tin kho
        warehouseController.updateWarehouse(warehouse);
        System.out.println("Warehouse information updated successfully.");
    }

    private static void deleteWarehouse() {
        System.out.println("You are performing the function: Delete Warehouse");
        String wareId = InputValidator.getValidString("Enter warehouse ID: ");

        // Lấy thông tin kho theo wareId
        Warehouse warehouse = warehouseController.getWarehouse(wareId);

        if (warehouse == null) {
            System.out.println("Warehouse not found.");
            return;
        }

        // Kiểm tra xem kho có chứa sản phẩm không
        List<Product> products = productController.getProductsByWarehouseId(wareId);
        if (!products.isEmpty()) {
            System.out.println("Have " + products.size() + " in warehouse. Please move these products.");
            String wareIdNew = InputValidator.getValidString("Enter new warehouse: ");

            // Di chuyển sản phẩm sang kho mới
            products.forEach((e) -> {
                Product product = productController.getProduct(e.getProd_id());
                product.setWare_id(wareIdNew);
                productController.updateProduct(product);
            });
        }

        // Xóa kho
        warehouseController.deleteWarehouse(wareId);
        System.out.println("Warehouse deleted successfully.");
    }

    private static void addWarehouseBranch() {  }

    private static void viewWarehouseBranches() {  }

    private static void editWarehouseBranch() {  }

    private static void deleteWarehouseBranch() {  }

    private static void addProduct() {
        // Tạo đối tượng sản phẩm mới
        Product product = new Product();
        System.out.println("You are performing the function: Add Product");

        // Nhập thông tin sản phẩm
        product.setProd_name(InputValidator.getValidString("Enter product name: "));
        product.setProd_quantity(InputValidator.getValidInt("Enter product quantity: "));
        product.setProd_cost(InputValidator.getValidDouble("Enter product cost: "));
        product.setWare_id(InputValidator.getValidString("Enter warehouse ID: "));

        // Tạo sản phẩm mới thông qua controller
        productController.createProduct(product);
    }

    private static void viewProducts() {
        System.out.println("You are performing the function: View Products");

        // Lấy danh sách tất cả các sản phẩm
        List<Product> products = productController.getAllProducts();
        List<Product> productsFiltered = new ArrayList<>();

        // Lọc danh sách sản phẩm theo quyền của người dùng
        if (user_login.getRole().equals("user")) {
            productsFiltered = products.stream().filter(e -> e.getUser_id().equals(user_login.getUser_id())).toList();
        } else {
            productsFiltered = products;
        }

        System.out.println("List of products: ");
        System.out.println("[");
        System.out.println("Total records: " + productsFiltered.size());

        // Hiển thị danh sách sản phẩm
        productsFiltered.forEach(System.out::println);
        System.out.println("]");
    }

    private static void editProduct() {
        System.out.println("You are performing the function: Edit Product");
        String prodId = InputValidator.getValidString("Enter product ID: ");

        // Lấy thông tin sản phẩm theo prodId
        Product product = productController.getProduct(prodId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        // Hiển thị thông tin hiện tại của sản phẩm
        System.out.println("Current product information:");
        System.out.println("Product ID: " + product.getProd_id());
        System.out.println("Product Name: " + product.getProd_name());
        System.out.println("Product Quantity: " + product.getProd_quantity());
        System.out.println("Product Cost: " + product.getProd_cost());
        System.out.println("Warehouse ID: " + product.getWare_id());

        boolean isDisplayEditProduct = true;
        while (isDisplayEditProduct) {
            System.out.println("List of fields you can edit: ");
            System.out.println("1. Edit product name");
            System.out.println("2. Edit product quantity");
            System.out.println("3. Edit product cost");
            System.out.println("4. Edit warehouse ID");
            System.out.println("0. Back");

            int choice = InputValidator.getValidInt("Choose a field: ");

            switch (choice) {
                case 1: {
                    String newName = InputValidator.getValidString("Enter new product name: ");
                    if (!newName.isEmpty()) {
                        product.setProd_name(newName);
                        System.out.println("Product name updated successfully.");
                    } else {
                        System.out.println("Product name not changed.");
                    }
                    break;
                }
                case 2: {
                    int newQuantity = InputValidator.getValidInt("Enter new product quantity: ");
                    product.setProd_quantity(newQuantity);
                    System.out.println("Product quantity updated successfully.");
                    break;
                }
                case 3: {
                    double newCost = InputValidator.getValidDouble("Enter new product cost: ");
                    product.setProd_cost(newCost);
                    System.out.println("Product cost updated successfully.");
                    break;
                }
                case 4: {
                    String newWareId = InputValidator.getValidString("Enter new warehouse ID: ");
                    if (!newWareId.isEmpty()) {
                        product.setWare_id(newWareId);
                        System.out.println("Warehouse ID updated successfully.");
                    } else {
                        System.out.println("Warehouse ID not changed.");
                    }
                    break;
                }
                case 0: {
                    isDisplayEditProduct = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Cập nhật thông tin sản phẩm
        productController.updateProduct(product);
        System.out.println("Product information updated successfully.");
    }

    private static void deleteProduct() {
        System.out.println("You are performing the function: Delete Product");
        String prodId = InputValidator.getValidString("Enter product ID: ");

        // Lấy thông tin sản phẩm theo prodId
        Product product = productController.getProduct(prodId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        // Xóa sản phẩm
        productController.deleteProduct(prodId);
        System.out.println("Product deleted successfully.");
    }

    private static void addProducts() {
        System.out.println("You are performing the function: Add Products");
        String fileName = InputValidator.getValidString("Enter file name: ");
        String warehouseID = InputValidator.getValidString("Enter warehouseID: ");

        // Lấy thông tin kho theo warehouseID
        Warehouse warehouse = warehouseController.getWarehouse(warehouseID);
        if (warehouse.getUser_id() == null) {
            System.out.println("Warehouse doesn't have userID.");
            return;
        }
        if (user_login.getRole().equals("user")) {
            if (warehouse == null || !warehouse.getUser_id().equals(user_login.getUser_id())) {
                System.out.println("warehouseID not valid.");
                return;
            }
        }
        File file = new File(fileName);
        String excelFilePath = file.getAbsolutePath();

        // Đọc dữ liệu sản phẩm và thuộc tính từ file Excel
        List<Product> products = ExcelUtil.readProductsFromExcelFile(excelFilePath);
        List<Attribute> attributes = ExcelUtil.readAttributesFromExcelFile(excelFilePath);

        // Tạo sản phẩm và thuộc tính từ dữ liệu đọc được
        products.forEach((e) -> {
            Product product = new Product();
            product.setProd_name(e.getProd_name());
            product.setProd_quantity(e.getProd_quantity());
            product.setProd_cost(e.getProd_cost());
            product.setWare_id(warehouseID);
            String productID = productController.createProduct(product);
            attributes.forEach((e1) -> {
                if (e.getProd_id().equals(e1.getProd_id())) {
                    Attribute attribute = new Attribute();
                    attribute.setAttr_name(e1.getAttr_name());
                    attribute.setProd_id(productID);
                    attributeController.createAttribute(attribute);
                }
            });
        });
        System.out.println("Created successfully.");
    }

    private static void addAttribute() {
        // Tạo đối tượng thuộc tính mới
        Attribute attribute = new Attribute();
        System.out.println("You are performing the function: Add Attribute");

        // Nhập thông tin thuộc tính
        attribute.setAttr_name(InputValidator.getValidString("Enter attribute name: "));
        attribute.setProd_id(InputValidator.getValidString("Enter product ID: "));

        // Tạo thuộc tính mới thông qua controller
        attributeController.createAttribute(attribute);
    }

    private static void viewAttributes() {
        System.out.println("You are performing the function: View Attributes");

        // Lấy danh sách tất cả các thuộc tính
        List<Attribute> attributes = attributeController.getAllAttributes();
        List<Attribute> attributesFiltered = new ArrayList<>();

        // Lọc danh sách thuộc tính theo quyền của người dùng
        if (user_login.getRole().equals("user")) {
            attributesFiltered = attributes.stream().filter(e -> e.getUser_id().equals(user_login.getUser_id())).toList();
        } else {
            attributesFiltered = attributes;
        }

        System.out.println("List of attributes: ");
        System.out.println("[");
        System.out.println("Total records: " + attributesFiltered.size());

        // Hiển thị danh sách thuộc tính
        attributesFiltered.forEach(System.out::println);
        System.out.println("]");
    }

    private static void editAttribute() {
        System.out.println("You are performing the function: Edit Attribute");
        String attrId = InputValidator.getValidString("Enter attribute ID: ");

        // Lấy thông tin thuộc tính theo attrId
        Attribute attribute = attributeController.getAttribute(attrId);

        if (attribute == null) {
            System.out.println("Attribute not found.");
            return;
        }

        // Hiển thị thông tin hiện tại của thuộc tính
        System.out.println("Current attribute information:");
        System.out.println("Attribute ID: " + attribute.getAttr_id());
        System.out.println("Attribute Name: " + attribute.getAttr_name());
        System.out.println("Product ID: " + attribute.getProd_id());

        boolean isDisplayEditAttribute = true;
        while (isDisplayEditAttribute) {
            System.out.println("List of fields you can edit: ");
            System.out.println("1. Edit attribute name");
            System.out.println("2. Edit product ID");
            System.out.println("0. Back");

            int choice = InputValidator.getValidInt("Choose a field: ");

            switch (choice) {
                case 1: {
                    String newName = InputValidator.getValidString("Enter new attribute name: ");
                    if (!newName.isEmpty()) {
                        attribute.setAttr_name(newName);
                        System.out.println("Attribute name updated successfully.");
                    } else {
                        System.out.println("Attribute name not changed.");
                    }
                    break;
                }
                case 2: {
                    String newProdId = InputValidator.getValidString("Enter new product ID: ");
                    if (!newProdId.isEmpty()) {
                        attribute.setProd_id(newProdId);
                        System.out.println("Product ID updated successfully.");
                    } else {
                        System.out.println("Product ID not changed.");
                    }
                    break;
                }
                case 0: {
                    isDisplayEditAttribute = false;
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Cập nhật thông tin thuộc tính
        attributeController.updateAttribute(attribute);
        System.out.println("Attribute information updated successfully.");
    }

    private static void deleteAttribute() {
        System.out.println("You are performing the function: Delete Attribute");
        String attrId = InputValidator.getValidString("Enter attribute ID: ");

        // Lấy thông tin thuộc tính theo attrId
        Attribute attribute = attributeController.getAttribute(attrId);

        if (attribute == null) {
            System.out.println("Attribute not found.");
            return;
        }

        // Xóa thuộc tính
        attributeController.deleteAttribute(attrId);
        System.out.println("Attribute deleted successfully.");
    }

    private static void reportProductWarehouse() {
        // Báo cáo tổng số lượng sản phẩm cho người dùng
        System.out.println("Total Product Quantity For User: " + productController.getAllProductQuantityForUser(user_login.getUser_id()));
    }

    private static void reportProductAllWarehouse() {
        // Báo cáo tổng số lượng sản phẩm cho admin
        Map<String, Integer> reports = productController.getProductsQuantityForAdmin();
        for (Map.Entry<String, Integer> entry : reports.entrySet()) {
            System.out.println("warehouseId: " + entry.getKey());
            System.out.println("total product quantity: " + entry.getValue());
        }
    }

    private static boolean login() {
        while (true) {
            System.out.println("Login");
            String username = InputValidator.getValidString("Enter username: ");
            String password = InputValidator.getValidString("Enter password: ");

            // Thực hiện đăng nhập
            User userLogin = userController.login(username, password);
            user_login = userLogin;

            if (userLogin != null) {
                System.out.println("Login successfully.");
                return true;
            } else {
                System.out.println("Error: username or password not valid.");
            }
        }
    }
}

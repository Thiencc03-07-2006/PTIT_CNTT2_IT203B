package flashsaleengine;

import flashsaleengine.dao.ProductDAO;
import flashsaleengine.dao.UserDAO;
import flashsaleengine.entity.Product;
import flashsaleengine.entity.User;
import flashsaleengine.service.FlashSaleService;
import flashsaleengine.util.DatabaseConnectionManager;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Khởi tạo database
            DatabaseConnectionManager dbManager = DatabaseConnectionManager.getInstance();
            dbManager.initializeDatabase();
            System.out.println("Database initialized successfully!\n");

            // 2. Test CRUD
            testCRUD();

            // 3. Test Flash Sale Order với Transaction
            testFlashSaleOrder();

            // 4. Test Reports với Stored Procedures
            testReports();

            // 5. Test Concurrent 50 threads (chứng minh không overselling)
            testConcurrentPurchases();

            dbManager.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testCRUD() throws SQLException {
        System.out.println("=== TEST CRUD OPERATIONS ===");
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDAO = new UserDAO();

        Product product = new Product("IP15", "iPhone 15", "Electronics", new BigDecimal("20000000"), 100);
        productDAO.createProduct(product);
        System.out.println("Created product: " + product.getProductName());

        User user = new User("flashuser", "flash@example.com", "Flash User");
        userDAO.createUser(user);
        System.out.println("Created user: " + user.getUsername());

        productDAO.updateProductStock(product.getId(), 50);
        System.out.println("Updated product stock to 50\n");
    }

    private static void testFlashSaleOrder() {
        System.out.println("=== TEST FLASH SALE ORDER (WITH TRANSACTION) ===");
        FlashSaleService service = new FlashSaleService();
        List<FlashSaleService.OrderItem> items = new ArrayList<>();
        items.add(new FlashSaleService.OrderItem(1, 2));
        items.add(new FlashSaleService.OrderItem(2, 1));

        boolean success = service.placeOrder(1, items);
        System.out.println(success ? "Order placed successfully!\n" : "Order failed!\n");
    }

    private static void testReports() {
        System.out.println("=== TEST REPORTS (USING STORED PROCEDURES) ===");
        FlashSaleService service = new FlashSaleService();
        service.getTopBuyers();
        service.getCategoryRevenue();
        System.out.println();
    }

    private static void testConcurrentPurchases() {
        System.out.println("=== TEST CONCURRENT (50 THREADS, STOCK=10) ===");
        try {
            ProductDAO productDAO = new ProductDAO();
            Product testProduct = new Product("TEST_FLASH", "Test Product", "Test", new BigDecimal("1000000"), 10);
            productDAO.createProduct(testProduct);
            System.out.println("Test product created with stock: 10");

            int threadCount = 50;
            Thread[] threads = new Thread[threadCount];
            FlashSaleService service = new FlashSaleService();
            AtomicInteger successCount = new AtomicInteger(0);

            for (int i = 0; i < threadCount; i++) {
                final int userId = (i % 5) + 1;
                final int productId = testProduct.getId();
                threads[i] = new Thread(() -> {
                    List<FlashSaleService.OrderItem> items = new ArrayList<>();
                    items.add(new FlashSaleService.OrderItem(productId, 1));
                    if (service.placeOrder(userId, items)) {
                        successCount.incrementAndGet();
                    }
                });
                threads[i].start();
            }

            for (Thread t : threads) t.join();

            Product finalProduct = productDAO.getProductById(testProduct.getId());
            System.out.println("Successful purchases: " + successCount.get());
            System.out.println("Final stock: " + finalProduct.getStockQuantity());

            if (successCount.get() <= 10 && finalProduct.getStockQuantity() >= 0) {
                System.out.println("TEST PASSED: No overselling!\n");
            } else {
                System.out.println("TEST FAILED: Overselling detected!\n");
            }

            productDAO.deleteProduct(testProduct.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
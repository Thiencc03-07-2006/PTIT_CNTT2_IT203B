package flashsaleengine.service;

import flashsaleengine.dao.OrderDAO;
import flashsaleengine.dao.ProductDAO;
import flashsaleengine.entity.Order;
import flashsaleengine.entity.OrderDetail;
import flashsaleengine.entity.Product;
import flashsaleengine.util.DatabaseConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlashSaleService {
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;
    private final DatabaseConnectionManager dbManager;
    private static final Logger logger = Logger.getLogger(FlashSaleService.class.getName());

    public FlashSaleService() {
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
        this.dbManager = DatabaseConnectionManager.getInstance();
    }

    /**
     * Xử lý đơn hàng flash sale với transaction management
     * @param userId ID của người dùng
     * @param items Danh sách sản phẩm và số lượng mua
     * @return true nếu đặt hàng thành công, false nếu thất bại
     */
    public boolean placeOrder(int userId, List<OrderItem> items) {
        Connection conn = null;

        try {
            conn = dbManager.getConnection();

            // Set transaction isolation level to REPEATABLE_READ to prevent lost updates
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            // Disable auto-commit to start transaction
            conn.setAutoCommit(false);

            // Validate all products and check stock availability
            List<Product> productsToUpdate = new ArrayList<>();
            BigDecimal totalAmount = BigDecimal.ZERO;

            for (OrderItem item : items) {
                Product product = productDAO.getProductById(item.getProductId());
                if (product == null) {
                    throw new SQLException("Product not found: " + item.getProductId());
                }

                if (product.getStockQuantity() < item.getQuantity()) {
                    throw new SQLException("Insufficient stock for product: " +
                            product.getProductName() +
                            ". Available: " + product.getStockQuantity() +
                            ", Requested: " + item.getQuantity());
                }

                productsToUpdate.add(product);
                totalAmount = totalAmount.add(product.getPrice().multiply(
                        BigDecimal.valueOf(item.getQuantity())));
            }

            // Create order
            Order order = new Order(userId, totalAmount);
            int orderId = orderDAO.createOrder(order, conn);

            // Create order details
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                OrderItem item = items.get(i);
                Product product = productsToUpdate.get(i);

                OrderDetail detail = new OrderDetail(orderId, item.getProductId(),
                        item.getQuantity(), product.getPrice());
                orderDetails.add(detail);
            }

            orderDAO.createOrderDetails(orderDetails, conn);

            // Update product stock
            for (int i = 0; i < items.size(); i++) {
                OrderItem item = items.get(i);
                Product product = productsToUpdate.get(i);
                int newStock = product.getStockQuantity() - item.getQuantity();
                updateProductStockWithConnection(conn, product.getId(), newStock);
            }

            // Commit transaction
            conn.commit();
            logger.info("Order placed successfully - User ID: " + userId +
                    ", Order ID: " + orderId +
                    ", Total: " + totalAmount);
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error placing order", e);
            if (conn != null) {
                try {
                    conn.rollback();
                    logger.info("Transaction rolled back successfully");
                } catch (SQLException rollbackEx) {
                    logger.log(Level.SEVERE, "Error rolling back transaction", rollbackEx);
                }
            }
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error resetting auto-commit", e);
                }
            }
        }
    }

    private void updateProductStockWithConnection(Connection conn, int productId, int newStock)
            throws SQLException {
        String sql = "UPDATE products SET stock_quantity = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newStock);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        }
    }

    /**
     * Lấy top 5 người dùng mua nhiều nhất (sử dụng CallableStatement)
     */
    public void getTopBuyers() {
        String sql = "{CALL SP_GetTopBuyers()}";

        try (Connection conn = dbManager.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            System.out.println("\n=== TOP 5 NGƯỜI DÙNG MUA NHIỀU NHẤT ===");
            System.out.printf("%-5s %-20s %-25s %-15s %-15s%n",
                    "ID", "Username", "Full Name", "Total Orders", "Total Spent");
            System.out.println("----------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-25s %-15d %-15s%n",
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getInt("total_orders"),
                        formatCurrency(rs.getBigDecimal("total_spent")));
            }
            System.out.println();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting top buyers", e);
        }
    }

    /**
     * Thống kê doanh thu theo danh mục (sử dụng CallableStatement)
     */
    public void getCategoryRevenue() {
        String sql = "{CALL SP_GetCategoryRevenue()}";

        try (Connection conn = dbManager.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql);
             ResultSet rs = cstmt.executeQuery()) {

            System.out.println("\n=== THỐNG KÊ DOANH THU THEO DANH MỤC ===");
            System.out.printf("%-25s %-20s %-20s%n", "Category", "Total Items Sold", "Total Revenue");
            System.out.println("--------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-25s %-20d %-20s%n",
                        rs.getString("category"),
                        rs.getInt("total_items_sold"),
                        formatCurrency(rs.getBigDecimal("total_revenue")));
            }
            System.out.println();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting category revenue", e);
        }
    }

    private String formatCurrency(BigDecimal amount) {
        if (amount == null) return "0 VND";
        return String.format("%,.0f VND", amount);
    }

    public static class OrderItem {
        private int productId;
        private int quantity;

        public OrderItem(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int getProductId() { return productId; }
        public int getQuantity() { return quantity; }
    }
}
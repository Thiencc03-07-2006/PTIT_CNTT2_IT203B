package bai2;

import bai1.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Tại sao việc chỉ dùng System.out.println() để in ra lỗi trong khối catch là vi phạm nguyên tắc của Transaction? Hành động thiết yếu nào đã bị lập trình viên bỏ quên khi xảy ra SQLException?

Dữ liệu bị "treo" (Dangling Transaction): Khi gọi conn.setAutoCommit(false), database sẽ giữ các thay đổi của Thao tác 1 (Trừ tiền) trong một bộ nhớ đệm tạm thời. Nếu Thao tác 2 lỗi và nhảy vào catch mà không có lệnh hủy, các thay đổi này vẫn nằm chờ đó. Kết nối (Connection) lúc này vẫn đang giữ "lock" trên các hàng dữ liệu, khiến các tiến trình khác không thể truy cập hoặc cập nhật số dư của bệnh nhân đó, dẫn đến hiện tượng nghẽn hệ thống.

Vi phạm tính nhất quán: Dù dữ liệu chưa được commit, nhưng nếu Connection đó không được đóng hoặc rollback đúng cách mà lại được tái sử dụng (trong Connection Pool), các lệnh SQL của giao dịch tiếp theo có thể vô tình "commit" luôn cả phần dữ liệu lỗi của giao dịch trước đó.
 */
public class Consistency {

    public void thanhToanVienPhi(int patientId, int invoiceId, double amount) {
        // Sử dụng try-with-resources để tự động đóng Connection và PreparedStatements
        try (Connection conn = DatabaseManager.getConnection()) {

            conn.setAutoCommit(false);

            try {
                String sqlDeductWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
                try (PreparedStatement ps1 = conn.prepareStatement(sqlDeductWallet)) {
                    ps1.setDouble(1, amount);
                    ps1.setInt(2, patientId);
                    ps1.executeUpdate();
                }

                String sqlUpdateInvoice = "UPDATE Invoicesss SET status = 'PAID' WHERE invoice_id = ?";
                try (PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice)) {
                    ps2.setInt(1, invoiceId);
                    ps2.executeUpdate();
                }

                conn.commit();
                System.out.println("Thanh toán hoàn tất!");

            } catch (SQLException e) {
                System.err.println("Lỗi hệ thống: Đang thực hiện hoàn tác (Rollback)...");
                if (conn != null) {
                    try {
                        conn.rollback();
                        System.out.println("Đã khôi phục trạng thái dữ liệu an toàn.");
                    } catch (SQLException ex) {
                        System.err.println("Lỗi khi thực hiện Rollback: " + ex.getMessage());
                    }
                }
                System.err.println("Chi tiết lỗi nghiệp vụ: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

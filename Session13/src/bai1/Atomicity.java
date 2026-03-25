package bai1;

/*
Dựa vào kiến thức về nguyên lý hoạt động của Transaction (Lesson 01) và chế độ Auto-Commit mặc định của JDBC (Lesson 02), hãy giải thích tại sao khi xảy ra lỗi ở giữa dòng code, thuốc trong kho vẫn bị trừ mà dữ liệu không bị hủy bỏ?

Thao tác 1 (Trừ kho): Khi ps1.executeUpdate() chạy xong, dữ liệu trong bảng Medicine_Inventory đã được cập nhật và chốt (commit) ngay lập tức vào ổ đĩa.
Lỗi phát sinh: Dòng code int x = 10 / 0; gây ra ArithmeticException. Luồng xử lý bị ngắt và nhảy thẳng xuống khối catch.
Hệ quả: Thao tác 2 (Ghi lịch sử) chưa bao giờ được thực hiện. Tuy nhiên, vì Thao tác 1 đã được commit trước đó, Database không thể tự động quay lại trạng thái cũ. Kết quả là thuốc mất nhưng lịch sử không có.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Atomicity {

    public void capPhatThuoc(int medicineId, int patientId) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnection();

            conn.setAutoCommit(false);

            String sqlUpdateInventory = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlUpdateInventory);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();

            String sqlInsertHistory = "INSERT INTO Prescription_History (patient_id, medicine_id, date) VALUES (?, ?, GETDATE())";
            PreparedStatement ps2 = conn.prepareStatement(sqlInsertHistory);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.executeUpdate();

            conn.commit();
            System.out.println("Cấp phát thuốc thành công!");

        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Đã xảy ra lỗi, hệ thống đã hoàn tác dữ liệu (Rollback).");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Chi tiết lỗi: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

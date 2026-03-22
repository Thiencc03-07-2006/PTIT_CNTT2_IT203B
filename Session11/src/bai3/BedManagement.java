package bai3;

/*
Giá trị trả về của executeUpdate() có ý nghĩa gì? Làm thế nào để dùng giá trị này nhằm phản hồi chính xác cho y tá rằng "Mã giường này không tồn tại"?

Phương thức executeUpdate() trả về một số nguyên (int) đại diện cho số lượng dòng dữ liệu bị tác động bởi câu lệnh SQL.

Cách phản hồi chính xác:

Nếu trả về bằng 0: Nghĩa là không có dòng nào thỏa mãn điều kiện WHERE (Mã giường không tồn tại). Ta cần dùng giá trị này để in thông báo lỗi.
Nếu trả về lớn hơn 0: Cập nhật thành công.
*/

import java.sql.SQLException;
import java.sql.Statement;

public class BedManagement {
    public static void updateStatus(String inputId,Statement stmt) throws SQLException {
        String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = '" + inputId + "'";
        int rowsAffected = stmt.executeUpdate(sql);
        if (rowsAffected > 0) {
            System.out.println("Thành công: Đã cập nhật trạng thái giường " + inputId + " thành 'Đang sử dụng'.");
        } else {
            System.err.println("Lỗi: Không tìm thấy giường bệnh với mã: " + inputId);
        }
    }
}

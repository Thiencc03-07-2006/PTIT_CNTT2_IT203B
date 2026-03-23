package bai2;

/*
Tại sao các phương thức setDouble(), setInt() của PreparedStatement lại giúp lập trình viên không cần lo lắng về định dạng dấu chấm hay dấu phẩy của hệ điều hành?

Truyền dữ liệu nguyên bản (Native Type): Thay vì biến đổi mọi thứ thành chuỗi (String) rồi nối vào câu lệnh SQL, các phương thức này gửi dữ liệu dưới dạng kiểu dữ liệu thuần túy (binary/native format) mà Driver JDBC hiểu được.
Tách biệt tầng hiển thị và tầng lưu trữ: Dấu phẩy (,) hay dấu chấm (.) chỉ là cách con người nhìn thấy số thực trên màn hình tùy theo cài đặt hệ điều hành. Khi dùng setDouble(index, value), giá trị 37.5 được giữ nguyên cấu trúc số thực trong bộ nhớ máy tính.
Driver tự động xử lý: Driver JDBC chịu trách nhiệm giao tiếp với Database. Nó biết cách định dạng con số đó sao cho phù hợp với quy chuẩn của hệ quản trị cơ sở dữ liệu (thường là chuẩn ISO/ANSI với dấu chấm động), bất kể máy tính của y tá đang đặt ngôn ngữ là tiếng Việt, tiếng Pháp hay tiếng Anh.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TypeHandling {
    public void updatePatientVitals(Connection conn) {
        double temp = 37.5;
        int heartRate = 85;
        String patientId = "BN123";

        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, temp);
            pstmt.setInt(2, heartRate);
            pstmt.setString(3, patientId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Thành công: Đã cập nhật chỉ số sinh tồn cho bệnh nhân " + patientId);
            } else {
                System.err.println("Lỗi: Không tìm thấy mã bệnh nhân " + patientId);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống khi cập nhật dữ liệu: " + e.getMessage());
        }
    }
}

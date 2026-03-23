package bai1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Tại sao PreparedStatement lại được coi là "tấm khiên" chống lại SQL Injection? Cơ chế "Pre-compiled" (biên dịch trước) giúp ích gì trong việc bảo vệ tham số đầu vào?

Cơ chế Pre-compiled (Biên dịch trước):

Khi bạn khởi tạo một PreparedStatement với các dấu hỏi chấm (?), câu lệnh SQL sẽ được gửi lên Database Server để kiểm tra cú pháp và lập kế hoạch thực thi (execution plan) ngay lập tức, trước khi dữ liệu thực tế được đưa vào.
Database đã xác định sẵn cấu trúc của câu lệnh: "Tôi sẽ chọn dữ liệu từ bảng Doctors nơi mã số là X và mật khẩu là Y".

Bảo vệ tham số đầu vào:

Khi bạn truyền dữ liệu vào các dấu ? (ví dụ: ' OR '1'='1), Database sẽ đối xử với chuỗi này như một giá trị thuần túy (literal value), chứ không phải là một phần của lệnh SQL.
Hệ thống sẽ tìm kiếm một bác sĩ có mật khẩu đúng bằng chuỗi ký tự ' OR '1'='1. Vì không có bác sĩ nào đặt mật khẩu như vậy, cuộc tấn công sẽ thất bại hoàn toàn.
 */

public class InjectionShield {

    public void loginDocter(Connection conn, String inputCode, String inputPassword) {
        String sql = "SELECT * FROM Doctors WHERE doctor_code = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, inputCode);
            pstmt.setString(2, inputPassword);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Đăng nhập thành công! Chào bác sĩ: " + rs.getString("full_name"));
            } else {
                System.out.println("Lỗi: Sai mã số bác sĩ hoặc mật khẩu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

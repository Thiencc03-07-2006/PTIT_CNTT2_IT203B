package bai1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Tại sao việc khởi tạo kết nối liên tục mà không có cơ chế đóng (Close) hoặc quản lý tập trung lại gây nguy hiểm cho hệ thống y tế (vốn cần hoạt động 24/7)?

Cạn kiệt tài nguyên (Connection Leak): Mỗi khi phương thức getHospitalConn() được gọi, một "ống dẫn" mới được mở tới MySQL. Nếu không đóng, các kết nối này sẽ ở trạng thái treo. MySQL có giới hạn max_connections (mặc định thường là 151). Khi đạt giới hạn, hệ thống sẽ từ chối mọi yêu cầu truy xuất hồ sơ bệnh nhân mới, gây ra lỗi "Communications link failure".
Treo hệ thống (System Freezing): Khi bộ nhớ và số lượng thread xử lý kết nối tăng quá cao, máy chủ sẽ bị quá tải, dẫn đến hiện tượng phần mềm bị "đơ" như nhân viên kỹ thuật đã báo cáo.
Rủi ro bảo mật & Tính sẵn sàng: Trong y tế, việc không thể truy cập bệnh án kịp thời có thể ảnh hưởng đến tính mạng. Việc "khai báo cứng" (hard-coded) thông tin đăng nhập trong mã nguồn cũng làm tăng nguy cơ lộ thông tin quản trị (admin/med123).
 */

public class DBContext {
    private static final String URL = "jdbc:mysql://192.168.1.10:3306/Hospital_DB";
    private static final String USER = "admin";
    private static final String PASS = "med123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi Driver: " + e.getMessage());
            throw new SQLException(e);
        }
    }

    public static void exampleQuery() {
        Connection conn = null;
        try {
            conn = getConnection();
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

package bai4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Phân tích luồng thực thi của câu lệnh SQL sau khi bị nối chuỗi. Tại sao mệnh đề WHERE lại luôn đúng (true) trong trường hợp này?

Khi thực hiện nối chuỗi trực tiếp, câu lệnh SQL cuối cùng gửi đến cơ sở dữ liệu sẽ trở thành:
SELECT * FROM Patients WHERE full_name = '' OR '1'='1'

Tại sao mệnh đề WHERE luôn đúng?

Cấu trúc logic: Câu lệnh bị chia làm hai phần bởi toán tử OR.
Độ ưu tiên: Hệ thống kiểm tra điều kiện đầu tiên (full_name = '') – thường là sai. Tuy nhiên, điều kiện thứ hai là '1'='1'.
Kết quả: Vì '1'='1' là một biểu thức toán học luôn trả về giá trị True, nên toàn bộ mệnh đề WHERE trở thành True cho mọi dòng dữ liệu trong bảng.
Hệ lụy: Cơ sở dữ liệu sẽ trả về toàn bộ bản ghi của bảng Patients, gây rò rỉ thông tin nghiêm trọng.
 */
public class SQLInjection {
    public void searchPatient(String patientName, Statement stmt) throws SQLException {
        if (patientName != null) {
            patientName = patientName.replace("'", "''")
                    .replace("--", "")
                    .replace(";", "");
        }
        String sql = "SELECT * FROM Patients WHERE full_name = '" + patientName + "'";
        ResultSet rs = stmt.executeQuery(sql);
        boolean hasData = false;
        while (rs.next()) {
            hasData = true;
            System.out.println("Hồ sơ bệnh nhân: " + rs.getString("full_name"));
        }
        if (!hasData) {
            System.out.println("Không tìm thấy bệnh nhân nào khớp với từ khóa.");
        }
    }
}

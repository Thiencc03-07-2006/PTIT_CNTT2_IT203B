package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementDemo {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement call = conn.prepareCall("{CALL getStudentNameById(?, ?)}")) {

            // 1. Set tham số IN
            call.setString(1, "SV003");

            // 2. Đăng ký tham số OUT
            call.registerOutParameter(2, Types.VARCHAR);

            // 3. Thực thi
            call.execute();

            // 4. Lấy giá trị OUT
            String name = call.getString(2);
            System.out.println("Tên sinh viên: " + name);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
package rikkei.edu.util;

import java.sql.*;

public class DBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3307/student_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public static Connection openConnection(){
//        B1:  Khai baos Driver
        try {
            Class.forName(DRIVER);
            // mor kets noi
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Chua cai dat mysql driver");
        } catch (SQLException e) {
            System.err.println("Loi SQL : ket noi that bai");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        try(
            // B1 :  Mo ket noi
            Connection conn = openConnection();
            // B2 : Tạo câu lệnh Statement
            Statement stm = conn.createStatement();
        ) {
            // Tạo bảng :
            String ddl = """
                    create table Student(
                        id int auto_increment primary key,student
                        name varchar(255) not null,
                        gpa decimal(10,2) check(gpa>0)
                    )
                    """;
            String sql = """
                       SELECT name, gpa, id FROM student
                   """;
            // B3 : truyền tham số nếu có
            // B4 : thực thi câu lênh
            boolean isResultSet = stm.execute(sql); // true : có bản ghi trả về(ResultSet - SELECT) / false : ko có bản ghi trả về
            // B5 : xử lý kết quả trả về nếu có
            ResultSet rs = stm.getResultSet();
            System.out.println(isResultSet);
            while (rs.next()){
                System.out.println("Name : "+rs.getString("name"));
                System.out.println("GPA : "+rs.getString("gpa"));
                System.out.println("ID : "+rs.getString("id"));
                System.out.println("--------------------------------------");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        // B6 : Đóng kết nối
    }
}

package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatemenDemo {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pre = conn.prepareStatement("select *from student where id =?");) {
            pre.setString(1, "SV003");
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString("id") + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

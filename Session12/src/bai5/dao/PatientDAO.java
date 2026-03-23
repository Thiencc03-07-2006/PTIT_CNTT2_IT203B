package bai5.dao;

import bai5.model.Patient;
import bai5.util.DBConnection;

import java.sql.*;

public class PatientDAO {
    public void getAllPatients() {
        String sql = "SELECT * FROM patient";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                System.out.printf("%-5s | %-20s | %-4s | %-20s\n", "ID", "NAME", "AGE", "DEPARTMENT");
                System.out.println("-".repeat(58));
                while (rs.next()) {
                    System.out.printf("%-5d | %-20s | %-4d | %-20s\n", rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("department"));
                }
            } else {
                System.out.printf("Danh sách trống");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPatient(Patient p) {
        String sql = "INSERT INTO patient(name, age, department, disease, admission_date) VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getDepartment());
            ps.setString(4, p.getDisease());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDisease(int id, String disease) {
        String sql = "UPDATE patient SET disease = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, disease);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dischargePatient(int id) {
        String sql = "{CALL CALCULATE_DISCHARGE_FEE(?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();
            double fee = cs.getDouble(2);
            System.out.println("Tổng viện phí: " + fee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

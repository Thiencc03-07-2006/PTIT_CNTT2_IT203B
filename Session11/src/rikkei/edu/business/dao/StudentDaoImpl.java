package rikkei.edu.business.dao;

import rikkei.edu.model.Gender;
import rikkei.edu.model.Student;
import rikkei.edu.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao{
    private Student mapToStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getString("student_id"),
                resultSet.getString("full_name"),
                resultSet.getDate("dob").toLocalDate(),
                Gender.valueOf(resultSet.getString("gender")),
                resultSet.getString("phone"),
                resultSet.getString("email")
        );
    }
    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = """
                SELECT * FROM student
                """;

        try(
                Connection conn = DBConnection.openConnection();
                Statement stm = conn.createStatement();
                ){
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()){
                Student s = mapToStudent(rs);
                list.add(s);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student findById(String id) {
        String sql = """
                SELECT * FROM student where student_id = 
                """;

        try(
                Connection conn = DBConnection.openConnection();
                Statement stm = conn.createStatement();
        ){
            ResultSet rs = stm.executeQuery(sql+id);
            if (rs.next()){
                return mapToStudent(rs);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Student s) {
        String sql = String.format("INSERT INTO student VALUES ('%s','%s','%s','%s','%s','%s')",
                s.getStudentId(),s.getFullName(),s.getDob(),s.getGender(),s.getPhone(),s.getEmail()
                );
        System.out.println(sql);

        try(
                Connection conn = DBConnection.openConnection();
                Statement stm = conn.createStatement();
        ){
            int count = stm.executeUpdate(sql);
            return count>0;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = """
                DELETE FROM student where student_id = 
                """;

        try(
                Connection conn = DBConnection.openConnection();
                Statement stm = conn.createStatement();
        ){
            int count = stm.executeUpdate(sql+id);
            return count > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}

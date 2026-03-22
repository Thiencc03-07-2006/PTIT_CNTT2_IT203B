package rikkei.edu.business.dao;

import rikkei.edu.model.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> findAll();
    Student findById(String id);
    boolean insert(Student student);
    boolean update();
    boolean delete(String id);
}

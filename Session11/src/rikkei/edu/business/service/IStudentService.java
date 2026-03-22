package rikkei.edu.business.service;

import rikkei.edu.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getAllStudents();
    boolean addNewStudent(Student s);
}

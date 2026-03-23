package bai5.model;

public class Patient {
    private int id;
    private String name;
    private int age;
    //Khoa đang điều trị
    private String department;
    //Bệnh
    private String disease;

    public Patient(int id, String name, int age, String department, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.disease = disease;
    }

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}

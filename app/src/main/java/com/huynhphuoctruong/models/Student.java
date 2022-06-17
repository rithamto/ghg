package com.huynhphuoctruong.models;

public class Student {
    private String StudentId;
    private String StudentName;
    private String StudentClass;

    public Student(String studentId, String studentName, String studentClass) {
        StudentId = studentId;
        StudentName = studentName;
        StudentClass = studentClass;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }
}

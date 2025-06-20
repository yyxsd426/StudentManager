package com.student.management.controller;

import com.student.management.model.Student;
import com.student.management.model.StudentDAO;

import java.util.List;

public class StudentController {
    private StudentDAO studentDAO;

    public StudentController() {
        studentDAO = StudentDAO.getInstance();
    }

    public boolean saveStudent(Student student) {
        if (student == null || student.getStudentId() == null) {
            return false;
        }

        // 检查学号是否已存在（新增时）
        Student existingStudent = studentDAO.findStudentById(student.getStudentId());
        if (existingStudent != null && existingStudent != student) {
            return false; // 学号已存在且不是当前编辑的学生
        }

        if (existingStudent == null) {
            // 新增学生
            return studentDAO.addStudent(student);
        } else {
            // 更新学生
            return studentDAO.updateStudent(student);
        }
    }

    public boolean deleteStudent(String studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    public Student findStudentById(String studentId) {
        return studentDAO.findStudentById(studentId);
    }

    public List<Student> findStudentsByName(String name) {
        return studentDAO.findStudentsByName(name);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public String generateStudentId() {
        return studentDAO.generateStudentId();
    }
}
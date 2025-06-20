package com.student.management.model;

import com.student.management.util.FileUtil;

import java.io.File;
import java.util.*;

public class StudentDAO {
    private static final String FILE_PATH = "students.dat";
    private Map<String, Student> studentMap;
    private static StudentDAO instance;

    private StudentDAO() {
        studentMap = new TreeMap<>();
        loadData();
    }

    public static synchronized StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            Object obj = FileUtil.readObject(FILE_PATH);
            if (obj != null && obj instanceof Map) {
                studentMap = (Map<String, Student>) obj;
            }
        }
    }

    public void saveData() {
        FileUtil.writeObject(FILE_PATH, studentMap);
    }

    public boolean addStudent(Student student) {
        if (student == null || student.getStudentId() == null || studentMap.containsKey(student.getStudentId())) {
            return false;
        }
        studentMap.put(student.getStudentId(), student);
        saveData();
        return true;
    }

    public boolean deleteStudent(String studentId) {
        if (studentId == null || !studentMap.containsKey(studentId)) {
            return false;
        }
        studentMap.remove(studentId);
        saveData();
        return true;
    }

    public boolean updateStudent(Student student) {
        if (student == null || student.getStudentId() == null || !studentMap.containsKey(student.getStudentId())) {
            return false;
        }
        studentMap.put(student.getStudentId(), student);
        saveData();
        return true;
    }

    public Student findStudentById(String studentId) {
        if (studentId == null) {
            return null;
        }
        return studentMap.get(studentId);
    }

    public List<Student> findStudentsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ArrayList<>(studentMap.values());
        }

        List<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getName().contains(name)) {
                result.add(student);
            }
        }
        return result;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    public String generateStudentId() {
        return "STU" + System.currentTimeMillis() + (int)(Math.random() * 1000);
    }
}
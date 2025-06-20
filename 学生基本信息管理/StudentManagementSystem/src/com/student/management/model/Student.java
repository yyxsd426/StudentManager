package com.student.management.model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String studentId;
    private String name;
    private String gender;
    private Date birthDate;
    private String politicalStatus;
    private String address;
    private String phone;
    private String dormitory;

    // 构造方法
    public Student() {}

    public Student(String studentId, String name, String gender, Date birthDate,
                   String politicalStatus, String address, String phone, String dormitory) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.politicalStatus = politicalStatus;
        this.address = address;
        this.phone = phone;
        this.dormitory = dormitory;
    }

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public String getPoliticalStatus() { return politicalStatus; }
    public void setPoliticalStatus(String politicalStatus) { this.politicalStatus = politicalStatus; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDormitory() { return dormitory; }
    public void setDormitory(String dormitory) { this.dormitory = dormitory; }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", dormitory='" + dormitory + '\'' +
                '}';
    }
}
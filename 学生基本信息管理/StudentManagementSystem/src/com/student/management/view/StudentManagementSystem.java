package com.student.management.view;

import com.student.management.controller.StudentController;

import javax.swing.*;
import java.awt.*;

public class StudentManagementSystem extends JFrame {
    private StudentController controller;

    public StudentManagementSystem() {
        controller = new StudentController();
        initUI();
    }

    private void initUI() {
        setTitle("学生信息管理系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 创建标签页
        JTabbedPane tabbedPane = new JTabbedPane();

        // 添加学生列表面板
        StudentListPanel listPanel = new StudentListPanel(controller);
        tabbedPane.addTab("学生列表", listPanel);

        // 添加学生表单面板
        StudentFormPanel formPanel = new StudentFormPanel(controller);
        tabbedPane.addTab("添加学生", formPanel);

        // 添加搜索面板
        StudentSearchPanel searchPanel = new StudentSearchPanel(controller);
        tabbedPane.addTab("搜索学生", searchPanel);

        // 添加标签页到主窗口
        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentManagementSystem app = new StudentManagementSystem();
                app.setVisible(true);
            }
        });
    }
}
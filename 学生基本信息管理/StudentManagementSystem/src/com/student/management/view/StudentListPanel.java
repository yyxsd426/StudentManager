package com.student.management.view;

import com.student.management.controller.StudentController;
import com.student.management.model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentListPanel extends JPanel {
    private StudentController controller;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton deleteButton, editButton;

    public StudentListPanel(StudentController controller) {
        this.controller = controller;
        initUI();
        refreshTable();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // 表格列名
        String[] columnNames = {"学号", "姓名", "性别", "出生日期", "政治面貌", "家庭住址", "电话", "宿舍号"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 滚动面板
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("刷新列表");
        deleteButton = new JButton("删除选中");
        editButton = new JButton("编辑选中");

        deleteButton.setEnabled(false);
        editButton.setEnabled(false);

        refreshButton.addActionListener(e -> refreshTable());
        deleteButton.addActionListener(e -> handleDelete());
        editButton.addActionListener(e -> handleEdit());

        buttonPanel.add(refreshButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // 表格选择事件
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            deleteButton.setEnabled(selectedRow != -1);
            editButton.setEnabled(selectedRow != -1);
        });
    }

    void refreshTable() {
        tableModel.setRowCount(0);
        List<Student> students = controller.getAllStudents();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Student student : students) {
            Object[] row = {
                    student.getStudentId(),
                    student.getName(),
                    student.getGender(),
                    student.getBirthDate() != null ? sdf.format(student.getBirthDate()) : "",
                    student.getPoliticalStatus(),
                    student.getAddress(),
                    student.getPhone(),
                    student.getDormitory()
            };
            tableModel.addRow(row);
        }
    }

    private void handleDelete() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) return;

        String studentId = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this, "确定要删除学号为 " + studentId + " 的学生吗?",
                "确认删除", JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (controller.deleteStudent(studentId)) {
                JOptionPane.showMessageDialog(this, "删除成功");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "删除失败");
            }
        }
    }

    private void handleEdit() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) return;

        String studentId = (String) tableModel.getValueAt(selectedRow, 0);
        Student student = controller.findStudentById(studentId);

        if (student != null) {
            JTabbedPane tabbedPane = (JTabbedPane) getParent();
            StudentFormPanel formPanel = (StudentFormPanel) tabbedPane.getComponent(1);
            formPanel.editStudent(student);
            tabbedPane.setSelectedIndex(1);
        }
    }
}
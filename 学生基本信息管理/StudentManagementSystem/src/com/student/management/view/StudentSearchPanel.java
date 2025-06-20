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

public class StudentSearchPanel extends JPanel {
    private StudentController controller;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JRadioButton idRadio, nameRadio;
    private JButton searchButton;

    public StudentSearchPanel(StudentController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 搜索条件面板
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        idRadio = new JRadioButton("按学号搜索");
        nameRadio = new JRadioButton("按姓名搜索");
        nameRadio.setSelected(true);

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(idRadio);
        searchGroup.add(nameRadio);

        searchField = new JTextField(20);
        searchButton = new JButton("搜索");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        searchPanel.add(idRadio);
        searchPanel.add(nameRadio);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // 结果表格
        String[] columnNames = {"学号", "姓名", "性别", "出生日期", "政治面貌", "家庭住址", "电话", "宿舍号"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable);

        // 添加组件
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void performSearch() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入搜索关键词", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Student> results;
        if (idRadio.isSelected()) {
            // 按学号精确搜索
            Student student = controller.findStudentById(keyword);
            results = student != null ? List.of(student) : List.of();
        } else {
            // 按姓名模糊搜索
            results = controller.findStudentsByName(keyword);
        }

        displayResults(results);
    }

    private void displayResults(List<Student> students) {
        tableModel.setRowCount(0);

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "未找到匹配的学生信息", "搜索结果", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Student student : students) {
            Object[] rowData = {
                    student.getStudentId(),
                    student.getName(),
                    student.getGender(),
                    student.getBirthDate() != null ? sdf.format(student.getBirthDate()) : "",
                    student.getPoliticalStatus(),
                    student.getAddress(),
                    student.getPhone(),
                    student.getDormitory()
            };
            tableModel.addRow(rowData);
        }
    }
}
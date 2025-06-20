package com.student.management.view;

import com.student.management.controller.StudentController;
import com.student.management.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentFormPanel extends JPanel {
    private StudentController controller;
    private Student editingStudent;

    private JTextField idField, nameField, birthDateField, addressField, phoneField, dormitoryField;
    private JRadioButton maleRadio, femaleRadio;
    private JComboBox<String> politicalStatusCombo;
    private JButton saveButton, clearButton;

    public StudentFormPanel(StudentController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 表单面板
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 学号
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("学号:"), gbc);

        idField = new JTextField(20);
        // 允许手动输入学号（新增时）
        // idField.setEditable(false); // 注释掉这行

        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        // 姓名
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("姓名:"), gbc);

        nameField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // 性别
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("性别:"), gbc);

        JPanel genderPanel = new JPanel();
        maleRadio = new JRadioButton("男");
        femaleRadio = new JRadioButton("女");
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        maleRadio.setSelected(true);

        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);

        // 出生日期
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("出生日期 (yyyy-MM-dd):"), gbc);

        birthDateField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(birthDateField, gbc);

        // 政治面貌
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("政治面貌:"), gbc);

        String[] politicalStatus = {"群众", "团员", "党员", "其他"};
        politicalStatusCombo = new JComboBox<>(politicalStatus);
        gbc.gridx = 1;
        formPanel.add(politicalStatusCombo, gbc);

        // 家庭住址
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("家庭住址:"), gbc);

        addressField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        // 电话
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("电话:"), gbc);

        phoneField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        // 宿舍号
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(new JLabel("宿舍号:"), gbc);

        dormitoryField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(dormitoryField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("保存");
        clearButton = new JButton("清空");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudent();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        // 添加到主面板
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        clearForm();
    }

    public void editStudent(Student student) {
        if (student == null) return;

        this.editingStudent = student;
        idField.setText(student.getStudentId());

        // 编辑时禁止修改学号
        idField.setEditable(false);

        nameField.setText(student.getName());
        maleRadio.setSelected("男".equals(student.getGender()));
        femaleRadio.setSelected("女".equals(student.getGender()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        birthDateField.setText(
                student.getBirthDate() != null ?
                        sdf.format(student.getBirthDate()) : ""
        );

        politicalStatusCombo.setSelectedItem(student.getPoliticalStatus());
        addressField.setText(student.getAddress());
        phoneField.setText(student.getPhone());
        dormitoryField.setText(student.getDormitory());
    }

    private void saveStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String gender = maleRadio.isSelected() ? "男" : "女";
        String birthDateStr = birthDateField.getText().trim();
        String politicalStatus = (String) politicalStatusCombo.getSelectedItem();
        String address = addressField.getText().trim();
        String phone = phoneField.getText().trim();
        String dormitory = dormitoryField.getText().trim();

        // 验证输入
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "姓名不能为空", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = null;
        try {
            if (!birthDateStr.isEmpty()) {
                birthDate = sdf.parse(birthDateStr);
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "出生日期格式不正确 (yyyy-MM-dd)", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student student;
        if (editingStudent != null) {
            // 编辑现有学生
            student = editingStudent;
            student.setName(name);
            student.setGender(gender);
            student.setBirthDate(birthDate);
            student.setPoliticalStatus(politicalStatus);
            student.setAddress(address);
            student.setPhone(phone);
            student.setDormitory(dormitory);
        } else {
            // 添加新学生
            if (id.isEmpty()) {
                id = controller.generateStudentId();
                idField.setText(id);
            }
            student = new Student(id, name, gender, birthDate, politicalStatus, address, phone, dormitory);
        }

        // 保存到数据库
        if (controller.saveStudent(student)) {
            JOptionPane.showMessageDialog(this, "保存成功");
            clearForm();

            // 刷新列表
            JTabbedPane tabbedPane = (JTabbedPane) getParent();
            StudentListPanel listPanel = (StudentListPanel) tabbedPane.getComponent(0);
            listPanel.refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "保存失败：学号可能已存在", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        idField.setText("");
        nameField.setText("");
        maleRadio.setSelected(true);
        birthDateField.setText("");
        politicalStatusCombo.setSelectedIndex(0);
        addressField.setText("");
        phoneField.setText("");
        dormitoryField.setText("");
        editingStudent = null;

        // 新增时允许输入学号
        idField.setEditable(true);
    }
}
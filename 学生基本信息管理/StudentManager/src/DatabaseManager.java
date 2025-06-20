import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:students.db";

    // 创建学生表
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "gender TEXT NOT NULL," +
                "birthdate TEXT NOT NULL," +
                "political_status TEXT," +
                "address TEXT," +
                "phone TEXT," +
                "dormitory TEXT" +
                ")";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("学生表创建成功或已存在");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 添加学生
    public static void addStudent(Student student) {
        String sql = "INSERT INTO students(id, name, gender, birthdate, political_status, address, phone, dormitory) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGender());
            pstmt.setString(4, student.getBirthdate());
            pstmt.setString(5, student.getPoliticalStatus());
            pstmt.setString(6, student.getAddress());
            pstmt.setString(7, student.getPhone());
            pstmt.setString(8, student.getDormitory());
            pstmt.executeUpdate();
            System.out.println("学生添加成功");
        } catch (SQLException e) {
            System.out.println("添加失败: " + e.getMessage());
        }
    }

    // 查询所有学生
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("birthdate"),
                        rs.getString("political_status"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("dormitory")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    // 根据学号查询学生
    public static Student getStudentById(String id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("birthdate"),
                        rs.getString("political_status"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("dormitory")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    // 更新学生信息
    public static void updateStudent(Student student) {
        String sql = "UPDATE students SET " +
                "name = ?, " +
                "gender = ?, " +
                "birthdate = ?, " +
                "political_status = ?, " +
                "address = ?, " +
                "phone = ?, " +
                "dormitory = ? " +
                "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getBirthdate());
            pstmt.setString(4, student.getPoliticalStatus());
            pstmt.setString(5, student.getAddress());
            pstmt.setString(6, student.getPhone());
            pstmt.setString(7, student.getDormitory());
            pstmt.setString(8, student.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("学生信息更新成功");
            } else {
                System.out.println("未找到该学号的学生");
            }
        } catch (SQLException e) {
            System.out.println("更新失败: " + e.getMessage());
        }
    }

    // 删除学生
    public static void deleteStudent(String id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("学生删除成功");
            } else {
                System.out.println("未找到该学号的学生");
            }
        } catch (SQLException e) {
            System.out.println("删除失败: " + e.getMessage());
        }
    }
}

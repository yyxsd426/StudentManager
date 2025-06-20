import java.util.List;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args) {
        // 创建数据库表
        DatabaseManager.createTable();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== 学生信息管理系统 =====");
            System.out.println("1. 添加学生");
            System.out.println("2. 查询所有学生");
            System.out.println("3. 根据学号查询学生");
            System.out.println("4. 更新学生信息");
            System.out.println("5. 删除学生");
            System.out.println("6. 退出");
            System.out.print("请选择操作: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    displayStudentById(scanner);
                    break;
                case 4:
                    updateStudent(scanner);
                    break;
                case 5:
                    deleteStudent(scanner);
                    break;
                case 6:
                    System.out.println("感谢使用学生信息管理系统，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("\n=== 添加学生 ===");
        System.out.print("学号: ");
        String id = scanner.nextLine();
        System.out.print("姓名: ");
        String name = scanner.nextLine();
        System.out.print("性别: ");
        String gender = scanner.nextLine();
        System.out.print("出生日期 (YYYY-MM-DD): ");
        String birthdate = scanner.nextLine();
        System.out.print("政治面貌: ");
        String politicalStatus = scanner.nextLine();
        System.out.print("家庭住址: ");
        String address = scanner.nextLine();
        System.out.print("电话: ");
        String phone = scanner.nextLine();
        System.out.print("宿舍号: ");
        String dormitory = scanner.nextLine();

        Student student = new Student(id, name, gender, birthdate,
                politicalStatus, address, phone, dormitory);
        DatabaseManager.addStudent(student);
    }

    private static void displayAllStudents() {
        System.out.println("\n=== 所有学生信息 ===");
        List<Student> students = DatabaseManager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("暂无学生信息");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void displayStudentById(Scanner scanner) {
        System.out.println("\n=== 根据学号查询学生 ===");
        System.out.print("请输入学号: ");
        String id = scanner.nextLine();

        Student student = DatabaseManager.getStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("未找到该学号的学生");
        }
    }

    private static void updateStudent(Scanner scanner) {
        System.out.println("\n=== 更新学生信息 ===");
        System.out.print("请输入要更新的学生学号: ");
        String id = scanner.nextLine();

        Student student = DatabaseManager.getStudentById(id);
        if (student == null) {
            System.out.println("未找到该学号的学生");
            return;
        }

        System.out.println("当前学生信息:");
        System.out.println(student);

        System.out.println("\n请输入新信息（直接回车保持原值）");
        System.out.printf("姓名 [%s]: ", student.getName());
        String name = scanner.nextLine();
        if (!name.isEmpty()) student.setName(name);

        System.out.printf("性别 [%s]: ", student.getGender());
        String gender = scanner.nextLine();
        if (!gender.isEmpty()) student.setGender(gender);

        System.out.printf("出生日期 [%s]: ", student.getBirthdate());
        String birthdate = scanner.nextLine();
        if (!birthdate.isEmpty()) student.setBirthdate(birthdate);

        System.out.printf("政治面貌 [%s]: ", student.getPoliticalStatus());
        String politicalStatus = scanner.nextLine();
        if (!politicalStatus.isEmpty()) student.setPoliticalStatus(politicalStatus);

        System.out.printf("家庭住址 [%s]: ", student.getAddress());
        String address = scanner.nextLine();
        if (!address.isEmpty()) student.setAddress(address);

        System.out.printf("电话 [%s]: ", student.getPhone());
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) student.setPhone(phone);

        System.out.printf("宿舍号 [%s]: ", student.getDormitory());
        String dormitory = scanner.nextLine();
        if (!dormitory.isEmpty()) student.setDormitory(dormitory);

        DatabaseManager.updateStudent(student);
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.println("\n=== 删除学生 ===");
        System.out.print("请输入要删除的学生学号: ");
        String id = scanner.nextLine();

        DatabaseManager.deleteStudent(id);
    }
}

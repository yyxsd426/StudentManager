public class Student {
    private String id;
    private String name;
    private String gender;
    private String birthdate;
    private String politicalStatus;
    private String address;
    private String phone;
    private String dormitory;

    // 构造函数
    public Student(String id, String name, String gender, String birthdate,
                   String politicalStatus, String address, String phone, String dormitory) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.politicalStatus = politicalStatus;
        this.address = address;
        this.phone = phone;
        this.dormitory = dormitory;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

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
        return "学号: " + id +
                ", 姓名: " + name +
                ", 性别: " + gender +
                ", 出生日期: " + birthdate +
                ", 政治面貌: " + politicalStatus +
                ", 家庭住址: " + address +
                ", 电话: " + phone +
                ", 宿舍号: " + dormitory;
    }
}

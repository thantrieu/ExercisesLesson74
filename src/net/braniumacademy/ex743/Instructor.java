package net.braniumacademy.ex743;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Instructor extends Person {
    private String instructorId;      // mã nhân viên
    private String major;   // chuyên môn
    private float salary;   // lương
    private float experience; // số năm kinh nghiệm

    public Instructor() {
    }

    public Instructor(String id) {
        this.instructorId = id;
    }

    public Instructor(String id, String major, float salary, float experience)
            throws InvalidSalaryException {
        this.instructorId = id;
        this.major = major;
        this.setSalary(salary);
        this.experience = experience;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) throws InvalidSalaryException {
        var regex = "(([0-7]\\d?).\\d{1,2})|(80.[0]{1,2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(salary + "");
        if (matcher.matches()) {
            this.salary = salary;
        } else {
            this.salary = 0;
            var msg = "Mức lương không hợp lệ: " + salary
                    + ", giá trị hợp lệ phải nằm trong đoạn [0.00, 80.00]";
            throw new InvalidSalaryException(msg, salary);
        }
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    @Override
    public void work() {
        System.out.println("Giảng viên đang làm việc...");
    }

    // chuẩn bị bài
    public void prepareLesson(String subject) {
        System.out.println("Giảng viên "
                + getFullNameString() + " đang soạn bài môn " + subject);
    }

    // dạy học
    public void teach(String subject) {
        System.out.println("Giảng viên "
                + getFullNameString() + " đang giảng bài môn " + subject);
    }

    // chấm bài ktra
    public void markTheTest(String subject) {
        System.out.println("Giảng viên "
                + getFullNameString() + " đang giảng bài môn " + subject);
    }
}

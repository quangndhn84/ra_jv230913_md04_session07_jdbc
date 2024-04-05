package ra.entity;

public class Student {
    private int studentId;
    private String studentName;
    private int age;
    private boolean status;

    public Student() {
    }

    public Student(int studentId, String studentName, int age, boolean status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String toString() {
        return "Mã SV: " + this.studentId + " - Tên SV: " + this.studentName
                + " - Tuổi: " + this.age + " - Trạng thái: " + this.status;
    }
}

package ra.presentation;

import ra.buissiness.StudentBuissiness;
import ra.entity.Student;

import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**************MENU****************");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayListStudent();
                    break;
                case 2:
                    createNewStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (true);
    }

    public static void displayListStudent() {
        List<Student> listStudent = StudentBuissiness.getAllStudent();
        listStudent.stream().forEach(System.out::println);
    }

    public static void createNewStudent(Scanner scanner) {
        Student student = new Student();
        System.out.println("Nhập vào tên sinh viên:");
        student.setStudentName(scanner.nextLine());
        System.out.println("Nhập vào tuổi sinh viên:");
        student.setAge(Integer.parseInt(scanner.nextLine()));
        boolean result = StudentBuissiness.createStudent(student);
        if (result) {
            System.out.println("Thêm mới sinh viên thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thực hiện, vui lòng chờ trong giây lát");
        }
    }

    public static void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật");
        int studentId = Integer.parseInt(scanner.nextLine());
        boolean isExist = StudentBuissiness.isExistStudent(studentId);
        if (isExist) {
            //Thực hiện cập nhật
            Student student = new Student();
            student.setStudentId(studentId);
            System.out.println("Nhập vào tên sinh viên:");
            student.setStudentName(scanner.nextLine());
            System.out.println("Nhập vào tuổi sinh viên:");
            student.setAge(Integer.parseInt(scanner.nextLine()));
            System.out.println("Nhập vào trạng thái sinh viên:");
            student.setStatus(Boolean.parseBoolean(scanner.nextLine()));
            boolean result = StudentBuissiness.udpateStudent(student);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần xóa: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        boolean isExist = StudentBuissiness.isExistStudent(studentId);
        if (isExist) {
            boolean result = StudentBuissiness.delete(studentId);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.err.println("Xóa thất bại");
            }
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
    }
}

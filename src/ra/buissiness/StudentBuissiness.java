package ra.buissiness;

import ra.entity.Student;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class StudentBuissiness {
    //1. Get All Student
    public static List<Student> getAllStudent() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> listStudent = null;
        try {
            //1. Mở connection
            conn = ConnectionDB.openConnection();
            //2. Khởi tạo đối tượng CallableStatement
            callSt = conn.prepareCall("{call get_all_student()}");
            //3. Gọi procedure --> chứa kêt quả ở ResultSet
            ResultSet rs = callSt.executeQuery();
            //4. Duyệt ResultSet đẩy dữ liệu ra List<Student>
            listStudent = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentName(rs.getString("student_name"));
                student.setAge(rs.getInt("age"));
                student.setStatus(rs.getBoolean("student_status"));
                listStudent.add(student);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listStudent;
    }

    public static boolean createStudent(Student student) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            //1. Khởi tạo đối tượng Connection
            conn = ConnectionDB.openConnection();
            //2. Khởi tạo đối tượng CallableStatement
            callSt = conn.prepareCall("{call insert_student(?,?)}");
            //3. Gọi procedure và thực hiện procedure
            //3.1. set giá trị tương ứng các tham số vào
            callSt.setString(1, student.getStudentName());
            callSt.setInt(2, student.getAge());
            //3.2. Đăng ký kiểu dữ liệu cho các tham số trả ra
            //3.3. Thực hiện procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean isExistStudent(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        int cntStudent = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_cnt_by_id(?,?)}");
            //set giá trị cho tham số vào
            callSt.setInt(1, studentId);
            //đăng ký kiểu dữ liệu cho các tham số ra
            callSt.registerOutParameter(2, Types.INTEGER);
            //Thực hiện procedure
            callSt.execute();
            //Lấy giá trị tham số trả ra
            cntStudent = callSt.getInt(2);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        if (cntStudent == 0) {
            return false;
        }
        return true;
    }

    public static boolean udpateStudent(Student student) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            //1. Khởi tạo đối tượng Connection
            conn = ConnectionDB.openConnection();
            //2. Khởi tạo đối tượng CallableStatement
            callSt = conn.prepareCall("{call update_student(?,?,?,?)}");
            //3. Gọi procedure và thực hiện procedure
            //3.1. set giá trị tương ứng các tham số vào
            callSt.setInt(1,student.getStudentId());
            callSt.setString(2, student.getStudentName());
            callSt.setInt(3, student.getAge());
            callSt.setBoolean(4,student.isStatus());
            //3.2. Đăng ký kiểu dữ liệu cho các tham số trả ra
            //3.3. Thực hiện procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean delete(int studentId) {
        boolean result = false;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            //1. Khởi tạo đối tượng Connection
            conn = ConnectionDB.openConnection();
            //2. Khởi tạo đối tượng CallableStatement
            callSt = conn.prepareCall("{call delete_student(?)}");
            //3. Gọi procedure và thực hiện procedure
            //3.1. set giá trị tương ứng các tham số vào
            callSt.setInt(1,studentId);
            //3.2. Đăng ký kiểu dữ liệu cho các tham số trả ra
            //3.3. Thực hiện procedure
            callSt.executeUpdate();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}

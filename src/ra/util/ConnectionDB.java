package ra.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234$";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            //1. Set Driver
            Class.forName(DRIVER);
            //2. Khởi tạo đối tượng connection từ DriverManager
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement callSt){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (callSt!=null){
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

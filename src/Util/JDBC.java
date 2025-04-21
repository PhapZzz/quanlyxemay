package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBC {
    // Thông tin kết nối
    private static final String URL = "jdbc:mysql://localhost:3306/qlybanxemay?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Hàm lấy kết nối
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } 
         catch (SQLException e) {
            System.err.println("Lỗi kết nối đến MySQL: " + e.getMessage());
        }
        return conn;
    }

    // Hàm đóng kết nối
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
}

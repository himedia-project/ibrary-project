package db;

import java.sql.*;

public class DBConnectionUtil {

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/ibrary?serverTimeZone=UTC";
    private static final String id = "root";
    private static final String pw = "1234";


    /**
     * DB 초기작업
     */
    public static Connection getDBConnect() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, id, pw);
        } catch (ClassNotFoundException e) {
            // Class.forName(driver); 예외처리
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * DB 연결 해제
     * @param conn
     * @param pstmt
     * @param rs
     */
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}

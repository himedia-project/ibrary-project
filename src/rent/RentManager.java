package rent;


import java.sql.Connection;
<<<<<<< HEAD
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;	
import user.UserManager;

public class RentManager {
    
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/ibrary?serverTimeZone=UTC";
    private String id = "root";
    private String pw = "1234";
    
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    
    public RentManager() {

    }
    
    // DB 연결 초기화
    public void initDBConnect() {
        try {
            Class.forName(driver);
            this.conn = DriverManager.getConnection(this.url, this.id, this.pw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void rented() {
    	
    }
    
    // 책 대출
    public void insertRent(String bookId, String userId) {
        String rentSql = "INSERT INTO rent VALUES (null, ?, ?, ?, ?)";
        String updateBookSql = "UPDATE book SET rented = ? WHERE id = ?";

        // 현재 날짜 및 반납 기한 자동 계산
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusWeeks(1);
        

        try {  
            // rent 테이블에 대출 정보 추가
            pstmt = conn.prepareStatement(rentSql);
            pstmt.setString(1, bookId);
            pstmt.setString(2, userId);
            pstmt.setDate(3, Date.valueOf(today)); // 오늘 날짜
            pstmt.setDate(4, Date.valueOf(returnDate)); // 반납 기한 (1주일 뒤)
            pstmt.executeUpdate();
            
            // book 테이블에서 해당 책의 상태를 rented = true로 업데이트
            pstmt = conn.prepareStatement(updateBookSql);
            pstmt.setBoolean(1, true);
            pstmt.setString(2, bookId);
            pstmt.executeUpdate();

            // 반납 기한 출력
            System.out.println("책이 성공적으로 대출되었습니다.");
            System.out.println("반납 예정 날짜: " + returnDate);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // DB 연결 종료
    public void releaseDB() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
=======

import static db.DBConnectionUtil.*;

public class RentManager {

    private RentRepository rentRepository = null;

    public RentManager() {
        rentRepository = new RentRepository();
    }


    /**
     * 렌트하기
     * 트랜잭션 처리
     * @param bookId
     * @param userId
     */
    public void saveAndUpdateRent(String bookId, String userId) {
        Connection conn = null;
        try {
            conn = getDBConnect();
            // 트랜잭션 시작
            conn.setAutoCommit(false);
            insertAndUpdateRent(conn, bookId, userId);
            conn.commit();  // 성공시 커밋
        } catch (Exception e) {
                // 실패시 롤백
            try {
                conn.rollback();
            } catch (Exception rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            close(conn, null, null);
        }
    }

    public void insertAndUpdateRent(Connection conn, String bookId, String userId){
        rentRepository.insertRent(conn, bookId, userId);
        rentRepository.updateRented(conn, bookId);
        System.out.println("책이 성공적으로 대출되었습니다.");
    }

>>>>>>> 700d95a6c27731fdce5dd65ddf182ff6ba78421b
}
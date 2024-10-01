package rent;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static db.DBConnectionUtil.close;
import static db.DBConnectionUtil.getDBConnect;

public class RentRepository {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    //	rentlist 렌트 리스트 메서드
    public List<Rent> rentListByUserId(String userId) { //렌트 리스트 만드는 메서드
        List<Rent> rentList = new ArrayList<>();
        String sql = "select * from rent where user_id = ?";
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                rentList.add(new Rent(
                                rs.getLong("id"),
                                rs.getString("book_id"),
                                rs.getString("user_id"),
                                rs.getDate("start_date"),
                                rs.getDate("end_date")
                        )
                );
            }
        } catch (SQLException e) {
            System.out.println("대여 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            close(conn, pstmt, rs);
        }
        return rentList;
    }

    // 책 대출
    public void insertRent(Connection conn, String bookId, String userId) {
        String rentSql = "INSERT INTO rent VALUES (null, ?, ?, ?, ?)";

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

            // 반납 기한 출력
            System.out.println("반납 예정 날짜: " + returnDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRented(Connection conn, String bookId) {
        String sql = "update book set rented = true where id = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bookId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

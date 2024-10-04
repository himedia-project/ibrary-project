package favorite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.DBConnectionUtil.*;
import static db.DBConnectionUtil.close;

public class FavoriteRepository {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;


    public int recordCount() {
        String sql = "select count(*) as cnt from favorites";
        //sql 문자열 변수에 favorites 테이블의 레코드수를 센다.
        //as cnt :결과값을 'cnt'라는 이름으로 변환
        int recount = 0;//레코드 수를 저장할 정수형 변수 (초기화)
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            //쿼리를 실행한 결과값을 'rs'라는 객체에 담는다.
            if (rs.next()) { //rs(쿼리의 결과값)
                recount = rs.getInt("cnt");
            } //레코드수를 저장할 변수 =쿼리를 실행한 결과값(데이터의 갯수)(정수형) 선언
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return recount;//쿼리 실행후 나온 결과값(정수형) 출력
    }

    public List<String> findFavoriteBookIdListByUserId(String userId) { //객체의 배열
        //테이블의 총 레코드 수를 recount 변수에 정수형으로 저장한다
        List<String> bookIdList = new ArrayList<>();
        //FavoriteList 클래스 생성자를 recount 크기의 일차원 배열 생성
        String sql = "select f.book_id from favorites f where user_id = ? ";
        // sql = favorite 테이블의 레코드를 출력한 문자열
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            //rs :쿼리 실행결과

            while (rs.next()) { // 쿼리 실행 결과에 맞는 값을
                //while문을 통해 반복하여 전부 각각의 위치에 넣어준다

                String bookId = rs.getString("book_id");
                bookIdList.add(bookId);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(conn, pstmt, rs);
        }
        return bookIdList; //배열 출력
    }

    public int countByUserIdAndBookId(String userId, String bookId) {
        String sql = "select count(*) as cnt from favorites where user_id = ? and book_id = ?";
        int count = 0;
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, bookId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        return count;
    }


    public void save(String userId, String bookId) {
        String sql = "insert into favorites values(null,?,?)";
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);
            pstmt.setString(2, bookId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }

    }


    public void delete(String userId, String bookId) {
        String sql = "delete from favorites where user_id = ? and book_id =? ";
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userId);
            pstmt.setString(2, bookId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }

    }


}
	


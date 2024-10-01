package rent;


import java.sql.Connection;

import static db.DBConnectionUtil.*;

public class RentManager {

    private RentRepository rentRepository = null;

    public RentManager() {
        rentRepository = new RentRepository();
    }


    public void insertAndUpdateRent(Connection conn, String bookId, String userId){
        rentRepository.insertRent(conn, bookId, userId);
        rentRepository.updateRented(conn, bookId, userId);
        System.out.println("책이 성공적으로 대출되었습니다.");
    }

    /**
     * 렌트하기
     * 트랜잭션 처리
     * @param bookId
     * @param userId
     */
    public void insertAndUpdateRent(String bookId, String userId) {
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


}
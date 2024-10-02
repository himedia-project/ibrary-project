package rent;


import java.sql.Connection;

import static db.DBConnectionUtil.*;

public class RentManager {

    private RentRepository rentRepository = null;

    public RentManager() {
        rentRepository = new RentRepository();
    }


    /**
     * (트랜잭션 처리)렌트하기
     * @param bookId 대출할 책 아이디
     * @param userId 대출할 유저 아이디
     */
    public void saveAndUpdateRent(String bookId, String userId) {

        Connection conn = null;
        try {
            conn = getDBConnect();
            // 트랜잭션 시작
            conn.setAutoCommit(false);
            this.insertAndUpdateRent(conn, bookId, userId);
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

    /**
     * 렌트하기
     * @param conn Transaction 처리한 Connection 객체
     * @param bookId 대출할 책 아이디
     * @param userId 대출할 유저 아이디
     */
    private void insertAndUpdateRent(Connection conn, String bookId, String userId){
        rentRepository.insertRent(conn, bookId, userId);
        rentRepository.updateRented(conn, bookId);
        System.out.println("책이 성공적으로 대출되었습니다.");
    }

}
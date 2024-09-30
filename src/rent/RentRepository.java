package rent;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.DBConnectionUtil.close;
import static db.DBConnectionUtil.getDBConnect;


public class RentRepository {

    public List<String> getRentBookIdListByUserId(String userId) {

        String sql = "select book_id from rent where user_id = ?";
        List<String> rentBookIdList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String bookId = rs.getString("book_id");
                rentBookIdList.add(bookId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return rentBookIdList;
    }

}

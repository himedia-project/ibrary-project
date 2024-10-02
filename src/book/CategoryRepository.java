package book;

import db.DBConnectionUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class CategoryRepository {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public List<Book> findCategoryBookList() {

        String sql = "SELECT b.id, c.name AS 카테고리, b.title AS 타이틀, b.writer AS 지은이, b.publisher AS 출판사 " +
                "FROM book b JOIN category c ON b.category_id = c.id ORDER BY c.id";

        List<Book> bookList = new ArrayList<>();

        try  {
            conn = DBConnectionUtil.getDBConnect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String category = rs.getString("카테고리");
                String title = rs.getString("타이틀");
                String writer = rs.getString("지은이");
                String publisher = rs.getString("출판사");

                bookList.add(new Book(id, category, title, writer, publisher));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.close(conn, pstmt, rs);
        }
        return bookList;

    }

}

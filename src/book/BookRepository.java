package book;

import db.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static db.DBConnectionUtil.close;

public class BookRepository {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;


    public List<Book> findbBookList() {

        String sql = "select b.id, c.name, b.title, b.writer, b.publisher, b.rented " +
                "from book b join category c on b.category_id = c.id";

        List<Book> bookList = new ArrayList<>();

        try {
            conn = DBConnectionUtil.getDBConnect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("publisher")
                );
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }

        return bookList;
    }

    public List<Book> findBookListByIdList(List<String> bookIdList) {
        if (bookIdList == null || bookIdList.isEmpty()) {
            return new ArrayList<>();
        }

        String placeholders = String.join(",", Collections.nCopies(bookIdList.size(), "?"));
        String sql = "select b.id, c.name, b.title, b.writer, b.publisher, b.rented " +
                "from book b join category c on b.category_id = c.id " +
                "where b.id in (" + placeholders + ")";

        List<Book> bookList = new ArrayList<>();

        try {
            conn = DBConnectionUtil.getDBConnect();
            pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < bookIdList.size(); i++) {
                pstmt.setString(i + 1, bookIdList.get(i));
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Book book = new Book(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("writer"),
                        rs.getString("publisher")
                );
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(conn, pstmt, rs);
        }
        return bookList;
    }


}

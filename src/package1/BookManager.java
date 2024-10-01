package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.sql.PreparedStatement;

public class BookManager {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/ibrary?serverTimeZone=UTC";
	private String id = "root";
	private String pw = "1234";

	private Connection conn = null;
	private Statement stmt = null;
	
	 public BookManager() {
	        initDBConnect();
	    }

	public void initDBConnect() {
		try {
			Class.forName(driver);
			this.conn = DriverManager.getConnection(this.url, this.id, this.pw);
			this.stmt = conn.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void searchBooks(int selectn, String searchTerm) {
        String sql = "SELECT b.id, b.title, b.writer, b.publisher, c.name AS 카테고리, "
                + "CASE WHEN b.rented = 0 THEN '대여 가능' ELSE '대여 불가능' END AS '대여 여부' "
                + "FROM book b JOIN category c ON b.category_id = c.id " + "WHERE ";

        switch (selectn) {
            case 1:
                sql += "b.title LIKE CONCAT('%', ?, '%')";
                break;
            case 2:
                sql += "b.writer LIKE CONCAT('%', ?, '%')";
                break;
            case 3:
                sql += "b.publisher LIKE CONCAT('%', ?, '%')";
                break;
            case 4:
                sql += "c.name LIKE CONCAT('%', ?, '%')";
                break;
            default:
                System.out.println("잘못된 검색 옵션입니다.");
                return;
        }

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, searchTerm);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean hasResults = false;

            while (resultSet.next()) {
                hasResults = true; 
                String id = resultSet.getString("id");
                String title = resultSet.getString("title");
                String writer = resultSet.getString("writer");
                String publisher = resultSet.getString("publisher");
                String category = resultSet.getString("카테고리");
                String rentedStatus = resultSet.getString("대여 여부");

                System.out.printf("ISBN: %s, 타이틀: %s, 지은이: %s, 출판사: %s, 카테고리: %s, 대여 여부: %s%n", 
                                  id, title, writer, publisher, category, rentedStatus);
            }
            if (!hasResults) {
                System.out.println("일치하는 항목이 없습니다."); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (conn != null)
                conn.close();
            System.out.println("MySQL 연결 종료.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
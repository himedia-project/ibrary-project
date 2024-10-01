package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryManager {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/ibrary?serverTimeZone=UTC";
    private String id = "root";
    private String pw = "1234";

    private Connection conn = null;
    private Statement stmt = null;

    public CategoryManager() {
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
    public void displayBookList() {
        String sql = "SELECT b.id, c.name AS 카테고리, b.title AS 타이틀, b.writer AS 지은이, b.publisher AS 출판사 " +
                     "FROM book b JOIN category c ON b.category_id = c.id ORDER BY c.id";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // 헤더 출력
            System.out.println(String.format("%-15s %-25s %-50s %-20s %-20s", "ID", "카테고리", "타이틀", "지은이", "출판사"));
            System.out.println("-------------------------------------------------------------------------------");

            // 데이터 출력
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String category = resultSet.getString("카테고리");
                String title = resultSet.getString("타이틀");
                String writer = resultSet.getString("지은이");
                String publisher = resultSet.getString("출판사");

                // 각 필드를 고정된 너비로 출력
                System.out.println(String.format("%-15s %-25s %-50s %-20s %-20s", id, category, title, writer, publisher));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
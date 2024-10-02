package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import db.DBConnectionUtil;
import favorite.FavoriteManager;
import rent.RentManager;
import user.UserManager;

public class BookManager {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private Scanner scan;

	public static String selectedId;

	private FavoriteManager favoriteManager = null;
	private RentManager rentManager = null;

	public BookManager() {
		scan = new Scanner(System.in);
		conn = DBConnectionUtil.getDBConnect();
		favoriteManager = new FavoriteManager();
		rentManager = new RentManager();
	}

//----------------------------북 검색 메뉴
	public void searchBooks() {
		while (true) {
			int selectnumber = 0;

			while (true) {
				System.out.println("<< 북 검색 메뉴 >>");
				System.out.println("1. 타이틀");
				System.out.println("2. 지은이");
				System.out.println("3. 출판사");
				System.out.println("4. 카테고리");
				System.out.println("0. 메인화면");
				System.out.print("선택: ");
				selectnumber = scan.nextInt();
				scan.nextLine(); // 개행 문자 제거

				if (selectnumber >= 0 && selectnumber <= 4) {
					break;
				} else {
					System.out.println("잘못된 입력값입니다.");
				}
			}

			if (selectnumber == 0) {
				System.out.println("메인화면으로 돌아갑니다.");
				break;
			}

			System.out.println("검색어를 입력하세요: ");
			String searchTerm = scan.nextLine(); // 변수 선언

			executeSearch(selectnumber, searchTerm);
		}
	}

//----------------------------검색 키워드 입력 및 검색 프로세스
	private void executeSearch(int selectn, String searchTerm) {
		String sql = "SELECT b.id, b.title, b.writer, b.publisher, c.name AS 카테고리, "
				+ "CASE WHEN b.rented = 0 THEN '대여 가능' ELSE '대여 불가능' END AS '대여 여부' "
				+ "FROM book b JOIN category c ON b.category_id = c.id WHERE ";

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

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchTerm);
			ResultSet resultSet = pstmt.executeQuery();

			List<String> results = new ArrayList<>();
			int index = 1;

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String title = resultSet.getString("title");
				String writer = resultSet.getString("writer");
				String publisher = resultSet.getString("publisher");
				String category = resultSet.getString("카테고리");
				String rentedStatus = resultSet.getString("대여 여부");

				System.out.printf("%d. ISBN: %s, 타이틀: %s, 지은이: %s, 출판사: %s, 카테고리: %s, 대여 여부: %s%n", index++, id, title,
						writer, publisher, category, rentedStatus);
				results.add(id); // id를 리스트에 저장
			}

			if (results.isEmpty()) {
				System.out.println("일치하는 항목이 없습니다.");
			} else {
				System.out.println("원하는 항목의 번호를 선택하세요: ");
				int choice = scan.nextInt();
				scan.nextLine(); // 개행 문자 제거

				if (choice > 0 && choice <= results.size()) {
					selectedId = results.get(choice - 1); // 선택한 ISBN을 selectedId에 저장
					System.out.println("선택한 ISBN: " + selectedId);
					showOptions(selectedId);
				} else {
					System.out.println("잘못된 선택입니다.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// PreparedStatement와 ResultSet은 여기서 close하지 않고, 필요할 때 close합니다.
		}
	}

//----------------------------걷색 완료후 선택된 책 추가작업
	private void showOptions(String selectedId) {
		while (true) {
			System.out.println("1. 대여하기");
			System.out.println("2. 즐겨찾기에 추가하기");
			System.out.println("3. 검색 옵션으로 돌아가기");
			System.out.print("원하는 작업의 번호를 선택하세요: ");
			int action = scan.nextInt();
			scan.nextLine(); // 개행 문자 제거

			switch (action) {
			case 1:
				String userId = UserManager.currentUserEmail;
				String bookId = this.selectedId; // 현재 로그인한 사용자 ID 가져오기
				System.out.println("userId :" + userId);
				System.out.println("bookId :" + bookId);
				rentManager.saveAndUpdateRent(bookId, userId);
				break;
			case 2:
				String userId2 = UserManager.currentUserEmail;
				String bookId2 = this.selectedId;
				System.out.println("userid :" + userId2);
				System.out.println("bookid :" + bookId2);
				favoriteManager.addBook(userId2, bookId2);
				System.out.println("즐겨찾기에 추가하기 기능이 실행되었습니다. ISBN: " + selectedId);
				break;
			case 3:
				System.out.println("검색 옵션으로 돌아갑니다.");
				return; // 메서드를 종료하여 검색 옵션으로 돌아갑니다.
			default:
				System.out.println("잘못된 선택입니다.");
			}
		}
	}

//----------------------------DB연결 해제
	public void closeConnection() {
		DBConnectionUtil.close(conn, pstmt, null);
	}
}
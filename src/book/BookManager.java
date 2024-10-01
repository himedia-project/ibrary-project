//package book;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.PreparedStatement;
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BookManager {
//
//	private String driver = "com.mysql.cj.jdbc.Driver";
//	private String url = "jdbc:mysql://127.0.0.1:3306/ibrary?serverTimeZone=UTC";
//	private String id = "root";
//	private String pw = "1234";
//
//	private Connection conn = null;
//	private Statement stmt = null;
//	private Scanner scan;
//
//	RentManager rentmanager = new RentManager();
//
//	public BookManager() {
//		initDBConnect();
//		scan = new Scanner(System.in);
//	}
//
//	public void initDBConnect() {
//		try {
//			Class.forName(driver);
//			this.conn = DriverManager.getConnection(this.url, this.id, this.pw);
//			this.stmt = conn.createStatement();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void searchBooks() {
//		while (true) {
//			int selectnumber = 0;
//
//			while (true) {
//				System.out.println("검색 옵션을 선택하세요 (1: 타이틀, 2: 지은이, 3: 출판사, 4: 카테고리, 0: 메인화면): ");
//				selectnumber = scan.nextInt();
//				scan.nextLine(); // 개행 문자 제거
//
//				if (selectnumber >= 0 && selectnumber <= 4) {
//					break;
//				} else {
//					System.out.println("잘못된 입력값입니다.");
//				}
//			}
//
//			if (selectnumber == 0) {
//				System.out.println("메인화면으로 돌아갑니다.");
//				MenuManager menuManager = new MenuManager();
//				menuManager.iBraryMenu();
//				break;
//			}
//
//			System.out.println("검색어를 입력하세요: ");
//			String searchTerm = scan.nextLine(); // 변수 선언
//
//			// 검색 메서드 호출
//			executeSearch(selectnumber, searchTerm);
//		}
//	}
//
//	private void executeSearch(int selectn, String searchTerm) {
//		String sql = "SELECT b.id, b.title, b.writer, b.publisher, c.name AS 카테고리, "
//				+ "CASE WHEN b.rented = 0 THEN '대여 가능' ELSE '대여 불가능' END AS '대여 여부' "
//				+ "FROM book b JOIN category c ON b.category_id = c.id WHERE ";
//
//		switch (selectn) {
//		case 1:
//			sql += "b.title LIKE CONCAT('%', ?, '%')";
//			break;
//		case 2:
//			sql += "b.writer LIKE CONCAT('%', ?, '%')";
//			break;
//		case 3:
//			sql += "b.publisher LIKE CONCAT('%', ?, '%')";
//			break;
//		case 4:
//			sql += "c.name LIKE CONCAT('%', ?, '%')";
//			break;
//		default:
//			System.out.println("잘못된 검색 옵션입니다.");
//			return;
//		}
//
//		try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//			preparedStatement.setString(1, searchTerm);
//			ResultSet resultSet = preparedStatement.executeQuery();
//
//			List<String> results = new ArrayList<>();
//			int index = 1;
//
//			while (resultSet.next()) {
//				String id = resultSet.getString("id");
//				String title = resultSet.getString("title");
//				String writer = resultSet.getString("writer");
//				String publisher = resultSet.getString("publisher");
//				String category = resultSet.getString("카테고리");
//				String rentedStatus = resultSet.getString("대여 여부");
//
//				System.out.printf("%d. ISBN: %s, 타이틀: %s, 지은이: %s, 출판사: %s, 카테고리: %s, 대여 여부: %s%n", index++, id, title,
//						writer, publisher, category, rentedStatus);
//				results.add(id); // id를 리스트에 저장
//			}
//
//			if (results.isEmpty()) {
//				System.out.println("일치하는 항목이 없습니다.");
//			} else {
//				System.out.println("원하는 항목의 번호를 선택하세요: ");
//				int choice = scan.nextInt();
//				scan.nextLine();
//
//				if (choice > 0 && choice <= results.size()) {
//					String selectedId = results.get(choice - 1);
//					System.out.println("선택한 ISBN: " + selectedId);
//					showOptions(selectedId);
//				} else {
//					System.out.println("잘못된 선택입니다.");
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void showOptions(String selectedId) {
//		while (true) {
//			System.out.println("1. 대여하기");
//			System.out.println("2. 즐겨찾기에 추가하기");
//			System.out.println("3. 검색 옵션으로 돌아가기");
//			System.out.print("원하는 작업의 번호를 선택하세요: ");
//			int action = scan.nextInt();
//			scan.nextLine(); // 개행 문자 제거
//
//			switch (action) {
//			case 1:
//				System.out.println("대여하기 기능이 실행되었습니다. ISBN: " + selectedId);
//
//				break;
//			case 2:
//				// 즐겨찾기에 추가하기 로직 추가
//				System.out.println("즐겨찾기에 추가하기 기능이 실행되었습니다. ISBN: " + selectedId);
//				// 여기에 즐겨찾기 추가 로직을 추가하세요.
//				break;
//			case 3:
//				System.out.println("검색 옵션으로 돌아갑니다.");
//				return; // 메서드를 종료하여 검색 옵션으로 돌아갑니다.
//			default:
//				System.out.println("잘못된 선택입니다.");
//			}
//		}
//	}
//
//	public void closeConnection() {
//		try {
//			if (conn != null) {
//				conn.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//}
/////////////////////////////////////////////////////////////////////////////////////////////////
package book;

import rent.RentManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import rent.RentManager;
import db.DBConnectionUtil;

import menumanager.MenuManager;
import user.UserManager;

public class BookManager {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Scanner scan;

    public static String selectedId;

    public BookManager() {
        scan = new Scanner(System.in);
    }


    public void searchBooks() {
        while (true) {
            int selectnumber = 0;

            while (true) {
                System.out.println("검색 옵션을 선택하세요 (1: 타이틀, 2: 지은이, 3: 출판사, 4: 카테고리, 0: 메인화면): ");
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
                MenuManager menuManager = new MenuManager();
                menuManager.iBraryMenu();
                break;
            }

            System.out.println("검색어를 입력하세요: ");
            String searchTerm = scan.nextLine(); // 변수 선언

            // 검색 메서드 호출
            executeSearch(selectnumber, searchTerm);
        }
    }

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

        try  {
        	Connection conn = DBConnectionUtil.getDBConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
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

                System.out.printf("%d. ISBN: %s, 타이틀: %s, 지은이: %s, 출판사: %s, 카테고리: %s, 대여 여부: %s%n",
                        index++, id, title, writer, publisher, category, rentedStatus);
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
                    showOptions(); // 선택한 항목에 대한 옵션 표시
                } else {
                    System.out.println("잘못된 선택입니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showOptions() {
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
                    RentManager rentManager = new RentManager();
                    rentManager.initDBConnect(); // DB 연결 초기화
                    rentManager.insertRent(bookId, userId);
                    rentManager.releaseDB(); // DB 연결 종료
                  
                    break;
                case 2:
                    // 즐겨찾기에 추가하기 로직 추가
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

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
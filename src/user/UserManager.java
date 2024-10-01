package user;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

import menumanager.MenuManager;
import address.AddressManager;
import address.Address;
import rent.Rent;
import rent.RentRepository;

import static db.DBConnectionUtil.*;


public class UserManager {
    public static String currentUserEmail = null;
    private Scanner scanner = new Scanner(System.in);
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private RentRepository rentRepository = null;

    public UserManager() {
        rentRepository = new RentRepository();
    }

	/**
	 * 로그인을 실행시키기 위한 메서드
	 * @return
	 */

    public boolean loginProcess() {
        MenuManager menu = new MenuManager();
        System.out.print("이메일: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();

        boolean success = loginProcess(id, password);
        if (success) {
            System.out.println("환영합니다.");
            menu.iBraryMenu();
            return true;
        } else {
            System.out.println("로그인에 실패하였습니다. 이메일과 비밀번호를 확인하세요.");
            return false;
        }
    }

    /**
     * db에 있는 id,password가 일치하는지 확인하는 메서드
     * @param id
     * @param password
     * @return
     */
    
    public boolean loginProcess(String id, String password) {
        String sql = "SELECT * FROM user WHERE id = ? AND password = ?";

        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    currentUserEmail = id;
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            System.out.println("로그인 처리 중 오류가 발생했습니다: " + e.getMessage());
            return false;
        } finally {
            close(conn, pstmt, rs);
        }
    }

//  register 회원가입 메서드
    
    /**
     * 중복값 체크 메서드
     * @param id
     * @return
     */

    public boolean isIdDuplicate(String id) { 
        String sql = "select count(*) from user where id = ?";
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("오류 발생" + e.getMessage());
        } finally {
            close(conn, pstmt, rs);
        }
        return false;
    }

    /**
     * db에 적절한 값을 넣었는지 확인하기 위한 메서드
     * @param user
     * @return
     */
    

    public boolean registerUser(User user) { 
        String sql = "insert into user (id, name, password, phone, address_id, birth_date) values (?, ?, ?, ?, ?, ?)";
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            pstmt.setLong(5, user.getAddressId());
            pstmt.setDate(6, user.getBirthDate());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("사용자 등록 중 오류가 발생했습니다: " + e.getMessage());
            return false;
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /**
     *  회원가입 메서드
     */
    
    public void registerUser() { 
        AddressManager addressManager = new AddressManager();
        while (true) {
            try {
                System.out.print("이메일: ");
                String id = scanner.nextLine();

                if (isIdDuplicate(id)) {
                    System.out.println("중복 아이디입니다. 다른 아이디를 입력하십시오.");
                    continue;
                }

                if (!id.contains("@")) {
                    System.out.println("이메일 형식이 아닙니다. 다시 입력해주세요.");
                    continue;
                }

                System.out.print("비밀번호: ");
                String password = scanner.nextLine();

                System.out.print("이름: ");
                String name = scanner.nextLine();

                System.out.print("전화번호 (010xxxxxxxx or 010-xxxx-xxxx): ");
                String phone = scanner.nextLine();

                System.out.print("생년월일 (YYYY-MM-DD): ");
                String birthStr = scanner.nextLine();
                Date birth_date = Date.valueOf(birthStr);

                if (!birthStr.contains("-")) {
                    System.out.println("잘못된 형식입니다. 다시 입력해주세요.");
                    continue;
                }

                System.out.println("주소를 입력해주세요 (5자리 우편번호): ");
                Address address = addressManager.registAddress();
                long address_id = address.getId();


                User user = new User(id, password, name, phone, address_id, birth_date);
                boolean success = registerUser(user);
                if (success) {
                    System.out.println("회원가입이 완료되었습니다.");
                    return;
                } else {
                    System.out.println("회원가입이 실패했습니다. 다시 시도해 주세요.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("잘못 입력하셨습니다: " + e.getMessage());
                System.out.println("다시 입력해 주세요.");
            }
        }
    }

    /**
     * 유저정보 출력하는 메서드
     */

    public void showUserInfo() { 

        String sql = "select u.id, u.name, u.phone, u.birth_date, a.addr1, a.addr2, a.zip_code \r\n"
                + "from user u\r\n"
                + "join address a\r\n"
                + "on u.address_id = a.id\r\n"
                + "where u.id = ?;";
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentUserEmail);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String addr1 = rs.getString("addr1");
                String addr2 = rs.getString("addr2");
                int zipcode = rs.getInt("zip_code");
                Date birthDate = rs.getDate("birth_date");
                System.out.println("===== 마이페이지 =====");
                System.out.println("이메일: " + id);
                System.out.println("이름: " + name);
                System.out.println("전화번호: " + phone);
                System.out.println("주소: " + addr1 + " " + addr2);
                System.out.println("우편번호: " + zipcode);
                System.out.println("생년월일: " + birthDate);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("오류가 발생했습니다. 다시 시도해주세요.");
        }
    }

    /**
     * 렌트 리스트 보여주는 메서드
     */
    
    public void showRentList() { 
        List<Rent> rentList = this.rentRepository.rentListByUserId(currentUserEmail);
        if (rentList.isEmpty()) {
            System.out.println("대여한 책이 없습니다.");
        } else {
            System.out.println("대여 목록:");
            for (Rent rent : rentList) {
                System.out.println("책 ID: " + rent.getBookId() +
                        ", 대여 시작일: " + rent.getStartDate() +
                        ", 반납 예정일: " + rent.getEndDate());
            }
        }
    }

    public void logout() {
        currentUserEmail = null;
        System.out.println("로그아웃되었습니다.");
    }
}

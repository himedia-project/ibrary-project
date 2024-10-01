package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import menumanager.MenuManager;
import rent.RentRepository;
import db.DBConnectionUtil;
import address.AddressManager;
import address.Address;

public class UserManager {
    public static String currentUserEmail = null;
    private Scanner scanner = new Scanner(System.in);
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    
    public UserManager() {}

//  login 로그인 메서드
    
    public boolean loginProcess() {
        MenuManager menu = new MenuManager();
        System.out.print("이메일: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String password = scanner.nextLine();
        
        boolean success = loginProcess(id, password);
        if(success) {
            System.out.println("환영합니다.");
            menu.iBraryMenu();
            return true;
        } else {
            System.out.println("로그인에 실패하였습니다. 이메일과 비밀번호를 확인하세요.");
            return false;
        }
    }
    
    public boolean loginProcess(String id, String password) {
        String sql = "SELECT * FROM user WHERE id = ? AND password = ?";
        
        try  {
        	Connection conn = DBConnectionUtil.getDBConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    currentUserEmail = id;
                    return true;
                }
                return false;
            }
        } catch(SQLException e) {
            System.out.println("로그인 처리 중 오류가 발생했습니다: " + e.getMessage());
            return false;
        }
    }
    
//  register 회원가입 메서드
    
    public boolean isIdDuplicate(String id) { // 중복값 체크 메서드 
    	String sql = "select count(*) from user where id = ?";
    	try {
        	Connection conn = DBConnectionUtil.getDBConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
            	return rs.getInt(1)>0;
            }
    	} catch(SQLException e){
    		System.out.println("오류 발생" + e.getMessage());
    	}
    	return false;
    }
    
    
    
    public boolean registerUser(User user) { // 적절히 값을 넣었는지 확인하는 메서드
        String sql = "insert into user (id, name, password, phone, address_id, birth_date) values (?, ?, ?, ?, ?, ?)";
        try {
        	Connection conn = DBConnectionUtil.getDBConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            pstmt.setLong(5, user.getAddressId());
            pstmt.setDate(6, user.getBirthDate());
            pstmt.executeUpdate();
            return true;
        } catch(SQLException e) {
            System.out.println("사용자 등록 중 오류가 발생했습니다: " + e.getMessage());
            return false;
        }
    }

    public void registerUser() { // 회원가입 메서드
    	AddressManager addressManager = new AddressManager();
        while(true) {
            try {
                System.out.print("이메일: ");
                String id = scanner.nextLine();
                
                if(isIdDuplicate(id)) {
                	System.out.println("중복 아이디입니다. 다른 아이디를 입력하십시오.");
                	continue;
                }
                
                if(!id.contains("@")) {
                	System.out.println("이메일 형식이 아닙니다. 다시 입력해주세요.");
                	continue;
                }
                
                System.out.print("비밀번호: ");
                String password = scanner.nextLine();
                
                System.out.print("이름: ");
                String name = scanner.nextLine();

                System.out.print("전화번호: ");
                String phone = scanner.nextLine();

                System.out.print("생년월일 (YYYY-MM-DD): ");
                String birthStr = scanner.nextLine();
                Date birth_date = Date.valueOf(birthStr);
                
                if(!birthStr.contains("-")) {
                	System.out.println("잘못된 형식입니다. 다시 입력해주세요.");
                	continue;
                }
                
                System.out.println("주소를 입력해주세요: ");
                Address address =  addressManager.registAddress();
                long address_id = address.getId();

                
                User user = new User(id, password, name, phone, address_id, birth_date);
                boolean success = registerUser(user);
                if(success) {
                    System.out.println("회원가입이 완료되었습니다.");
                    return;
                } else {
                    System.out.println("회원가입이 실패했습니다. 다시 시도해 주세요.");
                }
            } catch(IllegalArgumentException e) {
                System.out.println("잘못 입력하셨습니다: " + e.getMessage());
                System.out.println("다시 입력해 주세요.");
            }
        }
    }

//    userinformation 유저 정보 메서드
    
	public void UserInformation() { //유저정보 출력하는 메서드
		
		String sql = "select u.id, u.name, u.phone, u.birth_date, a.addr1, a.addr2, a.zip_code \r\n"
					  + "from user u\r\n"
					  + "join address a\r\n"
					  + "on u.address_id = a.id\r\n"
					  + "where u.id = ?;";
		try {
			conn = DBConnectionUtil.getDBConnect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, currentUserEmail);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
		} catch(SQLException e){
			System.out.println("오류가 발생했습니다. 다시 시도해주세요.");
		}
	}
    
//	rentlist 렌트 리스트 메서드
	
    public List<RentRepository> RentList() { //렌트 리스트 만드는 메서드
        List<RentRepository> rentList = new ArrayList<>();
        String sql = "select * from rent where user_id = ?";
        try  {
        	Connection conn = DBConnectionUtil.getDBConnect();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, currentUserEmail);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { 
				String id = rs.getString("id");
				String book_id = rs.getString("book_id");
				String user_id = rs.getString("user_id");
				Date start_date = rs.getDate("start_date");
				Date end_date = rs.getDate("end_date");
				rentList.add(new RentRepository(id, book_id, user_id, start_date, end_date));
			}
        } catch(SQLException e) {
            System.out.println("대여 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
        return rentList;
    }
    
    public void ShowRentList() { //렌트 리스트 보여주는 리스트
        List<RentRepository> rentList = this.RentList();
        if (rentList.isEmpty()) {
            System.out.println("대여한 책이 없습니다.");
        } else {
            System.out.println("대여 목록:");
            for(RentRepository rent : rentList) {
                System.out.println("책 ID: " + rent.getBook_id() +
                                   ", 대여 시작일: " + rent.getStart_date() +
                                   ", 반납 예정일: " + rent.getEnd_date());               
            }
        }
    }

    public void logout() {
        currentUserEmail = null;
        System.out.println("로그아웃되었습니다.");
    }
}

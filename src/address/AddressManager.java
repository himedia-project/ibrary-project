package address;
import java.sql.*;
import java.util.Scanner;
import db.DBConnectionUtil;

public class AddressManager {
	private Scanner scanner = new Scanner(System.in);
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    
	public AddressManager() {
		
	}

    public int registAddress(Address address) {
        String sql = "insert into address (id,addr1, addr2, zip_code) values (null,?, ?, ?)";
        try {
        	conn = DBConnectionUtil.getDBConnect();
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, address.getAddr1());
            pstmt.setString(2, address.getAddr2());
            pstmt.setInt(3, address.getZip_code());
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                address.setId(generatedId);
                return generatedId;
            }
        } catch(SQLException e) {
            System.out.println("사용자 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
        return -1;
    }
    
    public Address registAddress() {
			while(true) {
				System.out.print("주소 (도/특별시/광역시, 시/군/구, 읍/면/동): ");
				String addr1 = scanner.nextLine();
				System.out.print("상세 주소 (동/층/호수): ");
				String addr2 = scanner.nextLine();
				System.out.print("우편번호 :");
				int zip_code = scanner.nextInt();
				scanner.nextLine();
				
				Address address = new Address(0,addr1,addr2,zip_code);
				int addressId = registAddress(address);
                if(addressId != -1) {
                	System.out.println("주소가 성공적으로 등록되었습니다.");
                    return address;
                } else {
                    System.out.println("주소를 잘못 입력했습니다. 다시 입력해주세요.");
                }
			}		
    }
	
}

package address;

import db.DBConnectionUtil;

import java.sql.*;

public class AddressRepository {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     * DB에 있는 address에 정보를 집어 넣는 메서드 
     * @param address
     * @return 정보를 제대로 입력 하였다면 ai값이 입력됨, 잘못 넣었으면 -1
     */
    
    public int insertAddress(Address address) {
        String sql = "insert into address (id,addr1, addr2, zip_code) values (null,?, ?, ?)";
        try {
            conn = DBConnectionUtil.getDBConnect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, address.getAddr1());
            pstmt.setString(2, address.getAddr2());
            pstmt.setInt(3, address.getZip_code());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                address.setId(generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            System.out.println("사용자 등록 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            DBConnectionUtil.close(conn, pstmt, rs);
        }
        return -1;
    }


}

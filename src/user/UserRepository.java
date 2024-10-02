package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.DBConnectionUtil.close;
import static db.DBConnectionUtil.getDBConnect;

public class UserRepository {
	
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    /**
     * 로그인 정보 확인 메서드
     * @param id
     * @param password
     * @return id,pw를 제대로 입력했다면 true, 잘못 들어갔으면 false
     */
    public boolean login(String id, String password) {
        String sql = "SELECT * FROM user WHERE id = ? AND password = ?";

        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
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
    
    public int findCountUserById(String userId) {
        String sql = "select count(*) from user where id = ?";
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("오류 발생" + e.getMessage());
        } finally {
            close(conn, pstmt, rs);
        }
        return 0;
    }

    /**
     * 유저 정보를 DB에 제대로 집어넣는지 확인하는 메서드
     * @param user
     * @return	제대로 넣었으면 true, 아니면 false
     */
    public boolean saveUser(User user) {
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
     * DB에 있는 유저의 렌트 정보를 리스트에 저장하는 메서드
     * @param userId
     * @return true면 리스트에 add, false면 오류발생
     */
    List<User> findUserListById(String userId) {
        String sql = "select u.id, u.name, u.phone, u.birth_date, a.addr1, a.addr2, a.zip_code \r\n"
                + "from user u\r\n"
                + "join address a\r\n"
                + "on u.address_id = a.id\r\n"
                + "where u.id = ?;";

        List<User> userList = new ArrayList<>();
        try {
            conn = getDBConnect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String addr1 = rs.getString("addr1");
                String addr2 = rs.getString("addr2");
                String zipcode = rs.getString("zip_code");
                Date birthDate = rs.getDate("birth_date");

                userList.add(new User(id, name, phone, addr1, addr2, zipcode, birthDate));
            }

        } catch (SQLException e) {
            System.out.println("오류가 발생했습니다. 다시 시도해주세요.");
        } finally {
            close(conn, pstmt, rs);
        }
        return userList;
    }
}

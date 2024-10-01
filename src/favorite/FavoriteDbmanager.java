package favorite;

import java.util.Scanner;
import java.sql.Connection; //데이터베이스와의 연결 클래스
import java.sql.DriverManager;//데이터베이스 드라이버 관리, 연결 설정

import java.sql.ResultSet;//sql쿼리를 저장함
import java.sql.Statement;//sql문 실행시 사용됨

import com.mysql.cj.xdevapi.Result;

import java.sql.SQLException;//오류를 처리하는 클래스

import java.sql.PreparedStatement;//sql쿼리를 컴파일링

public class FavoriteDbmanager {

	static private String driver="com.mysql.cj.jdbc.Driver";
	// driver: MySQL JDBC 드라이버의 클래스 이름 저장 변수
	static private String url="jdbc:mysql://127.0.0.1:3306/ibrary?serverTimeZone=UTC";
	//데이터베이스에 연결하기 위한 주소값
	static private String id="root";
	//내가설정한 아이디 비번
	static private String pw="jys0917@";
	
	static private Connection conn=null; //데이터베이스와의 연결을 뜻하는 객체, null로 초기화
	static private Statement stmt=null;//sql쿼리 실행시 사용되는 객체
    
	
	
	public static void initDBConnect() { //데이터베이스와 연결하여 객체를 생성하는 메서드 
		try {
			Class.forName(driver);//위에 선언한 드라이버
			conn=DriverManager.getConnection(url,id,pw);
			//설정한 아이디 비번으로 데이터베이스 연결
			stmt=conn.createStatement();
			//sq 쿼리 실행 'Statement'객체 생성
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();	
		}catch(SQLException e) {
			e.printStackTrace();
		}//오류 출력문
	}
	
	public static int recordCount() {
		String sql="select count(*) as cnt from favorites";
		//sql 문자열 변수에 favorites 테이블의 레코드수를 센다.
		//as cnt :결과값을 'cnt'라는 이름으로 변환
		int recount=0;//레코드 수를 저장할 정수형 변수 (초기화)
		try {
			ResultSet rs=stmt.executeQuery(sql);
			//쿼리를 실행한 결과값을 'rs'라는 객체에 담는다.
			if(rs.next()) { //rs(쿼리의 결과값) 
			recount=rs.getInt("cnt");	
			} //레코드수를 저장할 변수 =쿼리를 실행한 결과값(데이터의 갯수)(정수형) 선언
			rs.close();			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return recount;//쿼리 실행후 나온 결과값(정수형) 출력
	}	
	
	public static Favorite[] allFetch(String userId) { //객체의 배열
		int recount=recordCount();//위에서 만든 메서드 호출 , 
		//테이블의 총 레코드 수를 recount 변수에 정수형으로 저장한다
		Favorite[] favoriteList = new Favorite[recount];
		
		//FavoriteList 클래스 생성자를 recount 크기의 일차원 배열 생성
		int userCount=0; // 배열의 갯수를 담는다. 변수 초기화
		String sql="select * from favorites where user_id = ? "; 
		// sql = favorite 테이블의 레코드를 출력한 문자열 
		try {			
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			//rs :쿼리 실행결과 
			
		        while(rs.next()) { // 쿼리 실행 결과에 맞는 값을 
				//while문을 통해 반복하여 전부 각각의 위치에 넣어준다
				
				Long id=rs.getLong("id");//() 안의 내용은 테이블의 행 이름
				String userid=rs.getString("user_id");//각 행에 맞는 값을 변수에 저장
				String bookid=rs.getString("book_id");
				//아아ㄷ; 입력에 따라 다른 책 코드 출력하게 만들디 
				
				favoriteList[userCount++]=new Favorite(id, userid, bookid);	
				// 위에 만든배열에 저장할 객체 생성 ,배열의 갯수 증가
			
			} 
			
		
			rs.close();				
		}catch(SQLException e) {
			e.printStackTrace();
		
		}
		
		return favoriteList; //배열 출력
	}
		

public static void inputFavorite(String userId, String bookId) {
		String sql="insert into favorites values(null,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, bookId);
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
public static void deleteFavorite(String userId, String bookId) {
	String sql="delete from favorites where user_id = ? and book_id =? ";
	try {
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		pstmt.setString(2, bookId);
		pstmt.executeUpdate();

	}catch(SQLException e) {
		e.printStackTrace();
	}

}



	public static void releaseDB() {
		try {
			if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


	}	
	


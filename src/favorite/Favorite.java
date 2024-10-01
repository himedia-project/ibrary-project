package favorite;

//내가 입력한 책의 정보를 저장함

public class Favorite {
	
	private Long id;  //입력받을 문자열 형식 객체
	private String user_id;
	private String book_id;
	
	

	public Favorite(Long id, String puserId, String pbookId) {
		this.id=id;
		this.user_id=puserId;
		this.book_id=pbookId;
	} // 생성자선언 , (매개변수) 



	//읽기접근, 쓰기 접근 설정
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getuserId() {
		return user_id;
	}

	public void setuserId(String userid) {
		this.user_id = user_id;
	}

	public String getbookId() {
		return book_id;
	}

	public void setbookId(String bookid) {
		this.book_id = book_id;
	}


}


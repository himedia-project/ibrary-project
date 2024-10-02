package favorite;

//내가 입력한 책의 정보를 저장함
public class Favorite {

    private Long id;  //입력받을 문자열 형식 객체

    //읽기접근, 쓰기 접근 설정
    private String userId;
    private String bookId;


    public Favorite(Long id, String userId, String bookId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}


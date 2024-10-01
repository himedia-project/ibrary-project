package rent;

<<<<<<< HEAD
import user.UserManager;
import java.util.Scanner;

public class Rent {
    public static void main(String[] args) {
        RentManager rentManager = new RentManager();
    	
        // 데이터베이스 연결 초기화
        rentManager.initDBConnect();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 메인 메뉴 출력
            System.out.println("*******************렌트 메뉴******************");
            System.out.println("1. 책 대출하기");
            System.out.println("0. 메인으로 돌아가기");
            System.out.print("선택하세요: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 처리

            if (mainChoice == 0) {
                System.out.println("메인으로 돌아갑니다.");	
                break;
            } else if (mainChoice == 1) {
                // 책 대출 과정 (책을 선택했다고 가정)
                String bookId = "책ID"; // 이미 선택된 책 ID (bookId)
               
//                String userId = "ID"; // 사용자 ID는 미리 설정되어 있다고 가정 (userId)
                
                String userId = "ID";

                // 대출 처리 (자동으로 오늘부터 1주일 뒤까지 대출)
                rentManager.insertRent(bookId, userId);
            } else {
                System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }

        scanner.close();
        rentManager.releaseDB(); // DB 연결 종료
=======
import java.sql.Date;

public class Rent {

    private Long id;
    private String userId;
    private String bookId;
    private Date startDate;
    private Date endDate;

    public Rent(Long id, String bookId, String userId, Date startDate, Date endDate) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
>>>>>>> 700d95a6c27731fdce5dd65ddf182ff6ba78421b
    }
}
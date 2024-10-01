package rent;

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
    }
}
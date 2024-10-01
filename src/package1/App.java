package package1;

import java.util.Locale.Category;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        CategoryManager categoryManager = new CategoryManager();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("도서 목록:");
        categoryManager.displayBookList();

        while (true) {
            int selectnumber = 0;

            while (true) {
                System.out.println("검색 옵션을 선택하세요 (1: 타이틀, 2: 지은이, 3: 출판사, 4: 카테고리, 0: 메인화면): ");
                selectnumber = scan.nextInt();
                scan.nextLine();

                if (selectnumber >= 0 && selectnumber <= 4) {
                    break; 
                } else {
                    System.out.println("잘못된 입력값입니다.");
                }
            }

            if (selectnumber == 0) {
                System.out.println("메인화면으로 돌아갑니다.");
                break; 
            }

            System.out.println("검색어를 입력하세요: ");
            String searchTerm = scan.nextLine();

            bookManager.searchBooks(selectnumber, searchTerm);
        }

        scan.close(); 
    }
}
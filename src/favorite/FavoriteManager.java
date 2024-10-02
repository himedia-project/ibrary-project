package favorite;


import book.Book;
import book.BookRepository;
import rent.RentManager;

import java.util.List;
import java.util.Scanner;

public class FavoriteManager {

    private FavoriteRepository favoriteRepository = null;
    private BookRepository bookRepository = null;

    private RentManager rentManager = null;

    private static String selectedId = null;

    public FavoriteManager() {
        favoriteRepository = new FavoriteRepository();
        bookRepository = new BookRepository();
        rentManager = new RentManager();
    }


    public void searchBooks(String userId) {

        Scanner scan = new Scanner(System.in);

        System.out.println("\n\n");
        System.out.println("   =============================================================================");
        System.out.println("   =                                  즐겨찾기                                    =");
        System.out.println("   =                                                                           =");
        System.out.println("   =   ███████╗ █████╗ ██╗   ██╗ ██████╗ ██████╗ ██╗████████╗███████╗ ███████╗ =");
        System.out.println("   =   ██╔════╝██╔══██╗██║   ██║██╔═══██╗██╔══██╗██║╚══██╔══╝██╔════╝ ██╔════╝ =");
        System.out.println("   =   █████╗  ███████║██║   ██║██║   ██║██████╔╝██║   ██║   █████╗   ███████╗ =");
        System.out.println("   =   ██╔══╝  ██╔══██║╚██╗ ██╔╝██║   ██║██╔══██╗██║   ██║   ██╔══╝   ╚════██║ =");
        System.out.println("   =   ██║     ██║  ██║ ╚████╔╝ ╚██████╔╝██║  ██║██║   ██║   ███████╗ ███████║ =");
        System.out.println("   =   ╚═╝     ╚═╝  ╚═╝  ╚═══╝   ╚═════╝ ╚═╝  ╚═╝╚═╝   ╚═╝   ╚══════╝ ╚══════╝ =");
        System.out.println("\n   ===========================================================================");
        System.out.println("   =                                즐겨찾기 목록                                  =");
        System.out.println("\n   ===========================================================================");
        showFavoritesBookList(userId);
        System.out.println("\n   ===========================================================================");
        System.out.println("   =============================================================================");
        System.out.println("   =                                                                           =");
        System.out.println("   =                              1. 즐겨찾기 삭제                                 =");
        System.out.println("   =                              2. 대여하기                                     =");
        System.out.println("   =                              3. 메인 메뉴로 돌아가기                            =");
        System.out.println("   =                                                                           =");
        System.out.println("   =============================================================================");
        System.out.print("   선택: ");
        int action = scan.nextInt();
        scan.nextLine(); // 개행 문자 제거

        if (action < 1 || action > 3) {
            System.out.println("   잘못된 선택입니다.");
            return;
        }

        while (true) {
            switch (action) {
                case 1:
                    inputBookId(userId);

                    removeItem(userId, selectedId);
                    return;
                case 2:
                    inputBookId(userId);

                    rentManager.saveAndUpdateRent(selectedId, userId);
                    return;
                case 3:
                    System.out.println("   검색 옵션으로 돌아갑니다.");
                    return;
                default:
                    System.out.println("   잘못된 선택입니다.");
            }
        }

    }

    private void inputBookId(String userId) {
        Scanner scan = new Scanner(System.in);
        List<Book> bookList = getFavoritesBookListByUserId(userId);

        System.out.print("   책 아이디를 입력하세요: ");
        selectedId = scan.nextLine();

        if (bookList.stream().noneMatch(book -> book.getId().equals(selectedId))) {
            System.out.println("   해당 아이디의 책이 즐겨찾기에 없습니다.");
            return;
        }

    }

    /**
     * 즐겨찾기에 추가
     *
     * @param userId 사용자 아이디
     * @param bookId 책 아이디
     */
    public void addBook(String userId, String bookId) {

        favoriteRepository.save(userId, bookId);
        System.out.println("   즐겨찾기에 추가되었습니다.");

    }

    /**
     * 즐겨찾기에서 삭제
     *
     * @param userId 사용자 아이디
     * @param bookId 책 아이디
     */
    public void removeItem(String userId, String bookId) {

        favoriteRepository.delete(userId, bookId);
        System.out.println("   삭제완료");
    }

    /**
     * 즐겨찾기 리스트 보기
     *
     * @param userId 사용자 아이디
     */
    public void showFavoritesBookList(String userId) {

        List<Book> bookList = getFavoritesBookListByUserId(userId);

        if(bookList.isEmpty()) {
            System.out.println("   즐겨찾기에 추가된 책이 없습니다.");
            return;
        }

        for (Book book : bookList) {
            System.out.printf("ISBN: %s, 타이틀: %s, 지은이: %s, 출판사: %s, 카테고리: %s",
                    book.getId(), book.getTitle(),
                    book.getTitle(), book.getWriter(), book.getCategory());
            System.out.println();
        }
    }

    public List<Book> getFavoritesBookListByUserId(String userId) {
        List<String> bookIdList = favoriteRepository.findFavoriteBookIdListByUserId(userId);

        return bookRepository.findBookListByIdList(bookIdList);
    }
}

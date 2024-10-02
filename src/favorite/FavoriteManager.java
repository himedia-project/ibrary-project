package favorite;


import book.Book;
import book.BookRepository;
import rent.RentManager;
import rent.RentRepository;

import java.util.List;
import java.util.Scanner;

public class FavoriteManager {

    private FavoriteRepository favoriteRepository = null;
    private BookRepository bookRepository = null;
    private RentRepository rentRepository = null;

    private RentManager rentManager = null;

    private static String selectedId = null;

    public FavoriteManager() {
        favoriteRepository = new FavoriteRepository();
        bookRepository = new BookRepository();
        rentManager = new RentManager();
        rentRepository = new RentRepository();
    }


    public void searchBooks(String userId) {

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

        while (true) {

            Scanner scan = new Scanner(System.in);
            System.out.println();
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
                System.out.println("   잘못된 선택입니다. 다시 선택해주세요.");
                continue;
            }

            switch (action) {
                case 1:
                    if (!checkFavoritesBookId(userId)) {
                        removeFavorite(userId, selectedId);
                    }
                    break;
                case 2:
                    if (!rentManager.checkRentBookId(userId)) {
                        rentManager.saveAndUpdateRent(selectedId, userId);
                    }
                    break;
                case 3:
                    System.out.println("   검색 옵션으로 돌아갑니다.");
                    return;
                default:
                    System.out.println("   잘못된 선택입니다. 다시 선택해주세요");
            }
        }

    }


    /**
     * 즐겨찾기에 책 아이디 체크
     *
     * @param userId 사용자 아이디
     * @return 책 아이디 체크 결과, 즐겨찾기에 없으면 false 있으면 true
     */
    public boolean checkFavoritesBookId(String userId) {
        Scanner scan = new Scanner(System.in);

        System.out.print("   책 아이디를 입력하세요: ");
        selectedId = scan.nextLine();
        if(countByUserIdAndBookId(userId, selectedId) >= 1) {
            System.out.println("   해당 아이디의 책이 이미 즐겨찾기에 있습니다.");
            return true;
        }
        return false;
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
    public void removeFavorite(String userId, String bookId) {

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

        if (bookList.isEmpty()) {
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

    /**
     * 즐겨찾기에 추가된 책 리스트 가져오기
     * @param userId 사용자 아이디
     * @return 즐겨찾기에 추가된 책 리스트
     */
    public List<Book> getFavoritesBookListByUserId(String userId) {
        List<String> bookIdList = favoriteRepository.findFavoriteBookIdListByUserId(userId);

        return bookRepository.findBookListByIdList(bookIdList);
    }

    /**
     * 즐겨찾기에 추가된 책 카운트
     *
     * @param userId 사용자 아이디
     * @return 즐겨찾기에 추가된 책 카운트
     */

    public int countByUserIdAndBookId(String userId, String bookId) {
        return favoriteRepository.countByUserIdAndBookId(userId, bookId);
    }
}

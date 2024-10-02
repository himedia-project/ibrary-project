package favorite;


import book.Book;
import book.BookRepository;

import java.util.List;

public class FavoriteManager {

    private FavoriteRepository favoriteRepository = null;
    private BookRepository bookRepository = null;

    public FavoriteManager() {
        favoriteRepository = new FavoriteRepository();
        bookRepository = new BookRepository();
    }


    /**
     * 즐겨찾기에 추가
     * @param userId 사용자 아이디
     * @param bookId 책 아이디
     */
    public void addBook(String userId, String bookId) {

        favoriteRepository.save(userId, bookId);
        System.out.println("즐겨찾기에 추가되었습니다.");

    }

    /**
     * 즐겨찾기에서 삭제
     * @param userId 사용자 아이디
     * @param bookId 책 아이디
     */
    public void removeItem(String userId, String bookId) {

        favoriteRepository.delete(userId, bookId);
        System.out.println("삭제완료");
    }

    /**
     * 즐겨찾기 리스트 보기
     * @param userId 사용자 아이디
     */
    public void viewCart(String userId) {

        List<String> bookIdList = favoriteRepository.findFavoriteBookIdListByUserId(userId);

        List<Book> bookList = bookRepository.findBookListByIdList(bookIdList);
        for (Book book : bookList) {
            System.out.printf("ISBN: %s, 타이틀: %s, 지은이: %s, 출판사: %s, 카테고리: %s",
                    book.getId(), book.getTitle(),
                    book.getTitle(), book.getWriter(), book.getCategory());
            System.out.println();
        }
    }
}

package favorite;


import java.util.List;

public class FavoriteManager {

    private FavoriteRepository favoriteRepository = null;

    public FavoriteManager() {
        favoriteRepository = new FavoriteRepository();
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
        // TODO: 즐겨찾기 북리스트 보기
        List<Favorite> favorites = favoriteRepository.allFetch(userId);
        for (Favorite favorite : favorites) {
            System.out.println(favorite);
        }
    }
}

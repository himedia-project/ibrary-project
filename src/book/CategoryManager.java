package book;

public class CategoryManager {


    private CategoryRepository categoryRepository = null;

    public CategoryManager() {
        categoryRepository = new CategoryRepository();
    }

    /**
     * 카테고리 북 목록을 출력한다.
     */
    public void displayBookList() {

        // 헤더 출력
        System.out.println(String.format("%-15s %-25s %-50s %-20s %-20s", "ID", "카테고리", "타이틀", "지은이", "출판사"));
        System.out.println("-------------------------------------------------------------------------------");

        // 데이터 출력
        for (Book book : categoryRepository.findCategoryBookList()) {
            // 각 필드를 고정된 너비로 출력
            System.out.println(String.format("%-15s %-25s %-50s %-20s %-20s",
                    book.getId(), book.getCategory(), book.getTitle(), book.getWriter(), book.getPublisher()));
        }

    }
}
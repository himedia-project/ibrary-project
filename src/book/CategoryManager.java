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
    
    public void BookList() {
        String[][] books = {
            {"9788935305223", "새로운 소프트웨어 공학\t\t\t", "최은만\t\t", "정익사\t", "프로그래밍"},
            {"9788966263745", "프론트엔드 성능 최적화 가이드\t\t\t", "유동균\t\t", "인사이트\t", "프로그래밍"},
            {"9788970504896", "알기 쉬운 알고리즘\t\t\t\t", "양성봉\t\t", "생능출판\t", "알고리즘"},
            {"9788970508290", "이해하기 쉬운 소프트웨어 공학 에센셜\t\t", "윤청\t\t", "생능출판\t", "소프트웨어공학"},
            {"9791138378147", "파이썬 프로그래밍\t\t\t\t", "박영권\t\t", "시대인\t", "프로그래밍"},
            {"9791156005032", "알고리즘 기초\t\t\t\t", "Richard Neapolitan", "홍릉\t", "알고리즘"},
            {"9791156640127", "MySQL로 배우는 데이터베이스 개론과 실습\t\t", "박우창\t\t", "한빛아카데미", "데이터베이스"},
            {"9791156645429", "쉽게 배우는 소프트웨어 공학\t\t\t", "김치수\t\t", "한빛아카데미", "소프트웨어공학"},
            {"9791158394592", "문제풀이로 완성하는 알고리즘+자료구조\t\t", "Yoneda Masataka\t", "위키북스\t", "알고리즘"},
            {"9791161757926", "개발자를 위한 레디스\t\t\t\t", "김가림\t\t", "에이콘출판사", "데이터베이스"},
            {"9791163036012", "Do it! 깡샘의 안드로이드 앱 프로그래밍 with 코틀린", "강성윤\t\t", "이지스퍼블리싱", "웹개발"},
            {"9791165219468", "코딩 자율학습 HTML + CSS + 자바스크립트\t", "김기수\t\t", "길벗\t", "웹개발"},
            {"9791169210911", "혼자 공부하는 C 언어\t\t\t\t", "서현우\t\t", "한빛미디어\t", "소프트웨어공학"},
            {"9791169212649", "처음 시작하는 FastAPI\t\t\t", "Bill Lubanovic\t", "한빛미디어\t", "웹개발"},
            {"9791192932163", "DB설계 입문자를 위한 데이터베이스 설계 및 구축\t", "오세종\t\t", "생능출판\t", "데이터베이스"}
        };

        System.out.println("ISBN\t\t제목\t\t\t\t\t\t저자\t\t\t출판사\t\t카테고리");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

        for (String[] book : books) {
            System.out.println(book[0] + "\t" + book[1] + "\t" + book[2] + "\t" + book[3] + "\t" + book[4]);
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
    }
}
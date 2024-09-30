import java.util.Scanner;

public class MenuManager {

    public final static int LOGIN = 1;
    public final static int EXIT = 2;

    public final static int BOOK_SEARCH = 1;
    public final static int USER_SEARCH = 2;
    public final static int FAVORITES = 3;
    public final static int LOGOUT = 4;

    public MenuManager() {
    }

    void initMenu(){
        System.out.println("<< 도서 대여관리 프로그램 >>");
        System.out.println("1. 로그인");
        System.out.println("2. 종료");
    }
    void iBraryMenu(){
        System.out.println("<< 도서 대여 관리 프로그램 >>");
        System.out.println("1. 도서 검색");
        System.out.println("2. 유저");
        System.out.println("3. 즐겨찾기");
        System.out.println("4. 로그아웃");
    }
    int selectInitMenu(){
        Scanner sc = new Scanner(System.in);
        int select = 0;
        while (true) {
            System.out.print("메뉴 선택 : ");
            select = sc.nextInt();
            if (select < LOGIN || select > EXIT) {
                System.out.println("잘못된 번호를 선택하였습니다! 다시 선택해주세요!");
                continue;
            } else {
                break;
            }
        }
        return select;
    }

    int selectIBraryMenu(){
        Scanner sc = new Scanner(System.in);
        int select = 0;
        while (true) {
            System.out.print("메뉴 선택 : ");
            select = sc.nextInt();
            if (select < BOOK_SEARCH || select > LOGOUT) {
                System.out.println("잘못된 번호를 선택하였습니다! 다시 선택해주세요!");
                continue;
            } else {
                break;
            }
        }
        return select;
    }

    public void iBraryProcess() {
        boolean endFlag = false;
        while (true) {
            this.iBraryMenu();
            int select = this.selectIBraryMenu();
            switch (select) {
                case BOOK_SEARCH:
//                    addressSearch();
                    System.out.println("book search");
                    break;
                case USER_SEARCH:
                    System.out.println("user search");
//                    userManager.userProcess();
                    break;
                case FAVORITES:
//                    allAddressSearch();
                    System.out.println("favorites search");
                    break;
                case LOGOUT:
                    System.out.println("logout");
//                    logout();
//                    userManager.logout();
                    endFlag = true;
                    break;
            }

            if (endFlag) {
                return;
            }
        }
    }

}

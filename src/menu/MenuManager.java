package menu;

import book.BookManager;
import book.CategoryManager;
import favorite.FavoriteManager;
import user.UserManager;
import java.util.Scanner;
import db.DBConnectionUtil;

public class MenuManager {

	public final static int REGIST = 1;
    public final static int LOGIN = 2;
    public final static int EXIT = 3;
    public final static int BOOK_SEARCH = 1;
    public final static int USER_SEARCH = 2;
    public final static int FAVORITES = 3;
    public final static int LOGOUT = 4;
    public final static int USER_INFO = 1;
    public final static int SHOW_LENTLIST = 2;
    public final static int MAINMENU = 3;
    private Scanner scanner = new Scanner(System.in);
    private UserManager user = new UserManager();
    private DBConnectionUtil db = new DBConnectionUtil();

	private UserManager userManager = null;
	private BookManager bookmanager = null;
	private CategoryManager categorymanager = null;

	private FavoriteManager favoriteManager = null;

    
    public MenuManager() {
		userManager = new UserManager();
		bookmanager = new BookManager();
		categorymanager = new CategoryManager();
		favoriteManager = new FavoriteManager();
    }

    public void initMenu(){
        System.out.println("\n\n");
        System.out.println("   =====================================================");
        System.out.println("   =                                                   =");
        System.out.println("   =   ██╗██████╗ ██████╗  █████╗ ██████╗ ██╗   ██╗    =");
        System.out.println("   =   ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝    =");
        System.out.println("   =   ██║██████╔╝██████╔╝███████║██████╔╝ ╚████╔╝     =");
        System.out.println("   =   ██║██╔══██╗██╔══██╗██╔══██║██╔══██╗  ╚██╔╝      =");
        System.out.println("   =   ██║██████╔╝██║  ██║██║  ██║██║  ██║   ██║       =");
        System.out.println("   =   ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝       =");
        System.out.println("   =                                                   =");
        System.out.println("   =       Welcome to Ibrary - Your Digital Library    =");
        System.out.println("   =                                                   =");
        System.out.println("   =====================================================");
        System.out.println("\n              Press Enter to continue...");
        scanner.nextLine();  // 사용자가 엔터를 누를 때까지 대기
        
        while(true) {
            db.getDBConnect();
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                                                   =");
            System.out.println("   =   ██╗██████╗ ██████╗  █████╗ ██████╗ ██╗   ██╗    =");
            System.out.println("   =   ██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝    =");
            System.out.println("   =   ██║██████╔╝██████╔╝███████║██████╔╝ ╚████╔╝     =");
            System.out.println("   =   ██║██╔══██╗██╔══██╗██╔══██║██╔══██╗  ╚██╔╝      =");
            System.out.println("   =   ██║██████╔╝██║  ██║██║  ██║██║  ██║   ██║       =");
            System.out.println("   =   ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝       =");
        System.out.println("   =                                                   =");
            System.out.println("   =          1. 회원가입    2. 로그인   3. 종료         =");
            System.out.println("   =                                                   =");
            System.out.println("   =               📚  📖  📚  📖  📚                  =");
            System.out.println("   =                                                   =");
            System.out.println("   =====================================================");
            System.out.print("   선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice){
                case REGIST:
                    user.registerUser();
                    break;
                case LOGIN:
                    user.loginProcess();
                    break;
                case EXIT:
                    System.out.println("\n   프로그램을 종료합니다. 안녕히 가세요!");
                    return;
                default:
                    System.out.println("\n   잘못된 번호를 선택하였습니다! 다시 선택해주세요!");
            }
        }  
    }
    
	public void iBraryMenu() {
		while(true) {
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                                                   =");
            System.out.println("   =   ███╗   ███╗ █████╗ ██╗███╗   ██╗    ███╗   ███╗ =");
            System.out.println("   =   ████╗ ████║██╔══██╗██║████╗  ██║    ████╗ ████║ =");
            System.out.println("   =   ██╔████╔██║███████║██║██╔██╗ ██║    ██╔████╔██║ =");
            System.out.println("   =   ██║╚██╔╝██║██╔══██║██║██║╚██╗██║    ██║╚██╔╝██║ =");
            System.out.println("   =   ██║ ╚═╝ ██║██║  ██║██║██║ ╚████║    ██║ ╚═╝ ██║ =");
            System.out.println("   =   ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝    ╚═╝     ╚═╝ =");
            System.out.println("   =                                                   =");
            System.out.println("   =        1. 도서 검색       2. 마이 페이지            =");
            System.out.println("   =        3. 즐겨찾기        4. 로그아웃               =");
            System.out.println("   =                                                   =");
            System.out.println("   =====================================================");
            System.out.print("   선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
			
			switch(choice) {
				case BOOK_SEARCH:
					bookmanager.searchBooks();
					break;
				case USER_SEARCH:
					MyPage();
					break;
				case FAVORITES:
					// TODO : 화면상 입력 로직 추가하기
					favoriteManager.viewCart(UserManager.currentUserEmail);
					break;
				case LOGOUT:
					userManager.logout();
					System.out.println("로그아웃되었습니다.");
					return;
				default:
					System.out.println("잘못된 번호를 선택하였습니다! 다시 선택해주세요!");
			}
		}
	}


    public void MyPage() { 
        while(true) {
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                   마이 페이지                      =");
            System.out.println("   =                                                   =");
            System.out.println("   =   ███╗   ███╗██╗   ██╗    ██████╗  █████╗  ██████╗ ███████╗ =");
            System.out.println("   =   ████╗ ████║╚██╗ ██╔╝    ██╔══██╗██╔══██╗██╔════╝ ██╔════╝ =");
            System.out.println("   =   ██╔████╔██║ ╚████╔╝     ██████╔╝███████║██║  ███╗█████╗   =");
            System.out.println("   =   ██║╚██╔╝██║  ╚██╔╝      ██╔═══╝ ██╔══██║██║   ██║██╔══╝   =");
            System.out.println("   =   ██║ ╚═╝ ██║   ██║       ██║     ██║  ██║╚██████╔╝███████╗ =");
            System.out.println("   =   ╚═╝     ╚═╝   ╚═╝       ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚══════╝ =");
            System.out.println("   =                                                   =");
            System.out.println("   =        1. 유저정보       2. 빌린 책 리스트           =");
            System.out.println("   =              3. 메인메뉴로 돌아가기                  =");
            System.out.println("   =                                                   =");
            System.out.println("   =====================================================");
            System.out.print("   선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice) {
                case USER_INFO:
                    user.showUserInfo();
                    break;
                case SHOW_LENTLIST:
                    user.showRentList();
                    break;
                case MAINMENU:
                    return;
                default:
                    System.out.println("\n   잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
	
    public void FavoritesMenu() {
        String userId = UserManager.currentUserEmail; // 현재 로그인한 사용자의 이메일을 사용
        while(true) {
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                    즐겨찾기                        =");
            System.out.println("   =                                                   =");
            System.out.println("   =   ███████╗ █████╗ ██╗   ██╗ ██████╗ ██████╗ ██╗████████╗███████╗ ███████╗ =");
            System.out.println("   =   ██╔════╝██╔══██╗██║   ██║██╔═══██╗██╔══██╗██║╚══██╔══╝██╔════╝ ██╔════╝ =");
            System.out.println("   =   █████╗  ███████║██║   ██║██║   ██║██████╔╝██║   ██║   █████╗   ███████╗ =");
            System.out.println("   =   ██╔══╝  ██╔══██║╚██╗ ██╔╝██║   ██║██╔══██╗██║   ██║   ██╔══╝   ╚════██║ =");
            System.out.println("   =   ██║     ██║  ██║ ╚████╔╝ ╚██████╔╝██║  ██║██║   ██║   ███████╗ ███████║ =");
            System.out.println("   =   ╚═╝     ╚═╝  ╚═╝  ╚═══╝   ╚═════╝ ╚═╝  ╚═╝╚═╝   ╚═╝   ╚══════╝ ╚══════╝ =");
            System.out.println("   =                                                   =");
            System.out.println("   =     1. 즐겨찾기 삭제                                =");
            System.out.println("   =     2. 대여하기                                =");
            System.out.println("   =     3. 메인 메뉴로 돌아가기                          =");
            System.out.println("   =                                                   =");
            System.out.println("   =====================================================");
            System.out.print("   선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    System.out.println("\n   =====================================================");
                    System.out.println("   =                  즐겨찾기 목록                      =");
                    System.out.println("   =====================================================");
                    favoriteManager.viewCart(userId);
                    System.out.println("   =====================================================");
                    break;
                case 2:
                    System.out.print("\n   추가할 도서 ID를 입력하세요: ");
                    String addBookId = scanner.nextLine();
                    favoriteManager.addBook(userId, addBookId);
                    break;
                case 3:
                    System.out.print("\n   삭제할 도서 ID를 입력하세요: ");
                    String removeBookId = scanner.nextLine();
                    favoriteManager.removeItem(userId, removeBookId);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\n   잘못된 선택입니다. 다시 선택해주세요.");
            }
            System.out.println("\n   계속하려면 Enter를 누르세요...");
            scanner.nextLine();
        }
    }
}
    
    


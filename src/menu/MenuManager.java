package menu;

import book.BookManager;
import book.CategoryManager;
import favorite.FavoriteManager;
import user.UserManager;

import java.util.Scanner;

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
            System.out.println("   =          1. 회원가입    2. 로그인   3. 종료             =");
            System.out.println("   =                                                   =");
            System.out.println("   =               📚  📖  📚  📖  📚                    =");
            System.out.println("   =                                                   =");
            System.out.println("   =====================================================");
            System.out.print("   선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
    		
    		switch(choice){
    			case REGIST:
    				userManager.registerUser();
    				break;
    			case LOGIN:
    				userManager.loginProcess();
    				break;
    			case EXIT:
    				displayExitMessage();
    				return;
    			default:
    				System.out.println("   잘못된 번호를 선택하였습니다! 다시 선택해주세요!");
    		}
    	}  
    }
	public void iBraryMenu() {
		while(true) {
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                                                   =");
            System.out.println("   =           ███╗   ███╗ █████╗ ██╗███╗   ██╗        =");
            System.out.println("   =           ████╗ ████║██╔══██╗██║████╗  ██║        =");
            System.out.println("   =           ██╔████╔██║███████║██║██╔██╗ ██║        =");
            System.out.println("   =           ██║╚██╔╝██║██╔══██║██║██║╚██╗██║        =");
            System.out.println("   =           ██║ ╚═╝ ██║██║  ██║██║██║ ╚████║        =");
            System.out.println("   =           ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝        =");
            System.out.println("   =                                                   =");
            System.out.println("   =             1. 도서 검색       2. 마이 페이지           =");
            System.out.println("   =             3. 즐겨찾기        4. 로그아웃             =");
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
					myPage();
					break;
				case FAVORITES:
					favoriteManager.searchBooks(UserManager.currentUserEmail);
					break;
				case LOGOUT:
					userManager.logout();
					return;
				default:
					System.out.println("   잘못된 번호를 선택하였습니다! 다시 선택해주세요!");
			}
		}
	}

	public void myPage() {

		while(true) {
            System.out.println("\n\n");
            System.out.println("   ================================================================");
            System.out.println("   =                         마이 페이지                             =");
            System.out.println("   =                                                              =");
            System.out.println("   =   ███╗   ███╗██╗   ██╗    ██████╗  █████╗  ██████╗ ███████╗  =");
            System.out.println("   =   ████╗ ████║╚██╗ ██╔╝    ██╔══██╗██╔══██╗██╔════╝ ██╔════╝  =");
            System.out.println("   =   ██╔████╔██║ ╚████╔╝     ██████╔╝███████║██║  ███╗█████╗    =");
            System.out.println("   =   ██║╚██╔╝██║  ╚██╔╝      ██╔═══╝ ██╔══██║██║   ██║██╔══╝    =");
            System.out.println("   =   ██║ ╚═╝ ██║   ██║       ██║     ██║  ██║╚██████╔╝███████╗  =");
            System.out.println("   =   ╚═╝     ╚═╝   ╚═╝       ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚══════╝  =");
            System.out.println("   =                                                              =");
            System.out.println("   =                1. 유저정보       2. 빌린 책 리스트                  =");
            System.out.println("   =                     3. 메인메뉴로 돌아가기                         =");
            System.out.println("   =                                                              =");
            System.out.println("   ================================================================");
            System.out.print("   선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
			
			switch(choice) {
				case USER_INFO:
					userManager.showUserInfo();
					break;
				case SHOW_LENTLIST:
					userManager.showRentList();
					break;
				case MAINMENU:
					return;
				default:
					System.out.println("   잘못된 선택입니다. 다시 선택해주세요.");
			}
		}
	}
	
    private void displayExitMessage() {
        System.out.println("\n\n");
        System.out.println("   =====================================================");
        System.out.println("   =                                                   =");
        System.out.println("   =             ██████╗ ██╗   ██╗███████╗             =");
        System.out.println("   =             ██╔══██╗╚██╗ ██╔╝██╔════╝             =");
        System.out.println("   =             ██████╔╝ ╚████╔╝ █████╗               =");
        System.out.println("   =             ██╔══██╗  ╚██╔╝  ██╔══╝               =");
        System.out.println("   =             ██████╔╝   ██║   ███████╗             =");
        System.out.println("   =             ╚═════╝    ╚═╝   ╚══════╝             =");
        System.out.println("   =                                                   =");
        System.out.println("   =                                                   =");
        System.out.println("   =        Thank you for using Ibrary. Goodbye!       =");
        System.out.println("   =                                                   =");
        System.out.println("   =====================================================");
        System.out.println("   프로그램을 종료합니다.");
    }
}

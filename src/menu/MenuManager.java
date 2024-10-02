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
<<<<<<< HEAD:src/menumanager/MenuManager.java
    private UserManager user = new UserManager();
    private DBConnectionUtil db = new DBConnectionUtil();
    private BookManager bookmanager = new BookManager();
    private CategoryManager categorymanager = new CategoryManager();


=======

	private UserManager userManager = null;
	private BookManager bookmanager = null;
	private CategoryManager categorymanager = null;
>>>>>>> c02a3a0c0f20421b317e0427c4619338def22d2a:src/menu/MenuManager.java
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
        System.out.println("   =    ___   _                           _____        =");
        System.out.println("   =   |_ _| | |__  _ __ __ _ _ __ _   _ |  ___|       =");
        System.out.println("   =    | |  | '_ \\| '__/ _` | '__| | | || |           =");
        System.out.println("   =    | |  | |_) | | | (_| | |  | |_| || |___        =");
        System.out.println("   =   |___| |_.__/|_|  \\__,_|_|   \\__, ||_____|       =");
        System.out.println("   =                               |___/               =");
        System.out.println("   =                                                   =");
        System.out.println("   =       Welcome to Ibrary - Your Digital Library    =");
        System.out.println("   =                                                   =");
        System.out.println("   =====================================================");
        System.out.println("\n              Press Enter to continue...");
        scanner.nextLine();  // 사용자가 엔터를 누를 때까지 대기
        
<<<<<<< HEAD:src/menumanager/MenuManager.java
        while(true) {
            db.getDBConnect();
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                                                   =");
            System.out.println("   =           << 도서 대여관리 프로그램 >>              =");
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
=======
    	while(true) {
    		System.out.println("<< 도서 대여관리 프로그램 >>");
    		System.out.println("1. 회원가입");
    		System.out.println("2. 로그인");
    		System.out.println("3. 종료");
    		System.out.print("선택: ");
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
    				System.out.println("프로그램을 종료합니다.");
    				return;
    			default:
    				System.out.println("잘못된 번호를 선택하였습니다! 다시 선택해주세요!");
    		}
    	}  
>>>>>>> c02a3a0c0f20421b317e0427c4619338def22d2a:src/menu/MenuManager.java
    }
    
	public void iBraryMenu() {
		while(true) {
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =    ___   _                           _____        =");
            System.out.println("   =   |_ _| | |__  _ __ __ _ _ __ _   _ |  ___|       =");
            System.out.println("   =    | |  | '_ \\| '__/ _` | '__| | | || |           =");
            System.out.println("   =    | |  | |_) | | | (_| | |  | |_| || |___        =");
            System.out.println("   =   |___| |_.__/|_|  \\__,_|_|   \\__, ||_____|       =");
            System.out.println("   =                               |___/               =");
            System.out.println("   =====================================================");
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
					myPage();
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

<<<<<<< HEAD:src/menumanager/MenuManager.java
    public void MyPage() { 
        while(true) {
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                   마이 페이지                      =");
            System.out.println("   =                                                   =");
            System.out.println("   =                     _______                       =");
            System.out.println("   =                    /       \\                      =");
            System.out.println("   =                   |  o   o  |                     =");
            System.out.println("   =                   |    ^    |                     =");
            System.out.println("   =                    \\  \\_/  /                      =");
            System.out.println("   =                     \\_____/                       =");
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
=======
	public void myPage() {

		while(true) {
	        System.out.println("\n\n");
	        System.out.println("   =====================================================");
	        System.out.println("   =                   마이 페이지                      =");
	        System.out.println("   =                                                   =");
	        System.out.println("   =                     _______                       =");
	        System.out.println("   =                    /       \\                      =");
	        System.out.println("   =                   |  o   o  |                     =");
	        System.out.println("   =                   |    ^    |                     =");
	        System.out.println("   =                    \\  \\_/  /                      =");
	        System.out.println("   =                     \\_____/                       =");
	        System.out.println("   =                    /       \\                      =");
	        System.out.println("   =                   /         \\                     =");
	        System.out.println("   =                                                   =");
	        System.out.println("   =====================================================");
			System.out.println("1. 유저정보");
			System.out.println("2. 빌린 책 리스트");
			System.out.println("3. 메인메뉴");
			System.out.print("선택: ");
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
					System.out.println("잘못된 선택입니다.");
			}
		}
	}
>>>>>>> c02a3a0c0f20421b317e0427c4619338def22d2a:src/menu/MenuManager.java
	
    public void FavoritesMenu() {
        String userId = "user123"; // 예시 사용자 ID. 실제로는 로그인한 사용자의 ID를 사용해야 합니다.
        while(true) {
            System.out.println("\n\n");
            System.out.println("   =====================================================");
            System.out.println("   =                    즐겨찾기                        =");
            System.out.println("   =                                                   =");
            System.out.println("   =            ★  ★  ★  ★  ★  ★  ★                =");
            System.out.println("   =           ☆   F A V O R I T E S   ☆              =");
            System.out.println("   =            ★  ★  ★  ★  ★  ★  ★                =");
            System.out.println("   =                                                   =");
            System.out.println("   =     1. 즐겨찾기 목록 보기                           =");
            System.out.println("   =     2. 즐겨찾기 추가                                =");
            System.out.println("   =     3. 즐겨찾기 삭제                                =");
            System.out.println("   =     4. 메인 메뉴로 돌아가기                          =");
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
    
    


package favorite;



import javax.management.loading.PrivateClassLoader;

public class WishListFavorite {

	private String[] userIds;// 위시리스트 책 이름을 저장할 배열
	private String[] bookIds; // 위시리스트 아이디 저장
	private int count; // count : 위시리스트에 저장된 항목의 갯수
	private String userId;


  
  
	
  
  
  public WishListFavorite(int wishBook) {
		// (int wishBook) :저장할 수 있는 데이터의 갯수리미트

		this.userIds = new String[wishBook]; // wishBook:추가된 책의 갯수
		this.bookIds = new String[wishBook];
		// 책 이름과 아이디를 문자열로 저장할 위시리스트, 책이름과 아이디가 한번에 저장되기에 일차원배열 사용

		this.count = 0; // manager.recordCount();// 위시리스트의 갯수는 데이터베이스 레코드의 수로 정의 ..
		// 가 아니라 0으로 초기화 해야함 ㅋ 아직 해결 못함
	}

	public void addBook(String userId, String bookId) {
//위시리스트에 책이름과 아이디 추가하기

//		for (int i = 0; i < count; i++) {
//			if (bookIds[i].equals(bookId)
//				&& equals(book)) {
//              System.out.println("이미 추가된 책입니다.");
//				return; 
//			} 
//		
//		} else { // 위시리스트 책의 갯수가 int wishBook보다 크면 저장을 멈춘다.
//			System.out.println("위시리스트가 가득 찼습니다.");
//		}
		FavoriteDbmanager.inputFavorite(userId, bookId);
		System.out.println("즐겨찾기에 추가되었습니다.");

	}

	public void removeItem(String userId, String bookId) {
		
		FavoriteDbmanager.deleteFavorite(userId, bookId);
		System.out.println("삭제완료");
	}

	public void viewCart(String userId) {
		
		Favorite[] list = FavoriteDbmanager.allFetch(userId);
		for (int i = 0;i<list.length ; i++) {
			if(list[i]==null) {
				break;
			}
			
			System.out.println(list[i].getuserId());
			System.out.println(list[i].getbookId());
		}
		}
	

	}


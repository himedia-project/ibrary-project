package favorite;

import java.util.Scanner;

public class FavoriteApp {

	public static void main(String[] args) {

		FavoriteDbmanager.initDBConnect();// 클래스명.메서드명() ; static 메서드 호출

		WishListFavorite List = new WishListFavorite(100);
		// WishListFavorite 생성자 선언 (int값 = 저장될 정보의 갯수)
//		위시리스트 한계수량 :100

		

		System.out.println("*******당신의 I_brary 장바구니*******");
		
		

		while (true) {
			System.out.println("*******메뉴선택을 해주세요*******" + "\n" + "1 장바구니 항목 추가하기" + "\n" + "2. 위시리스트 항목 삭제하기" + "\n"
					+ "3. 장바구니 전체보기 " + "\n" + "4. 종료하기");
			
			Scanner s = new Scanner(System.in);
			
			int select = s.nextInt();
			

			switch (select) {
			case 1:
				Scanner s1 = new Scanner(System.in);
				String addUserId = null;
				String addBookId = null;
				System.out.println("당신의 아이디를 입력하세요: ");
				addUserId = s1.nextLine();
				System.out.println("추가할 책의 코드번호를 입력하세요:");
				addBookId = s1.nextLine();

				List.addBook(addUserId, addBookId);
				break;

			case 2:
				Scanner s2 = new Scanner(System.in);
				System.out.println("당신의 아이디를 입력하세요: ");
				String removeItem = s2.nextLine();
				System.out.println("삭제할 책의 코드번호를 입력하세요:");
				String removeId = s2.nextLine();
				List.removeItem(removeItem, removeId);
				break;

			case 3:
				Scanner s3 = new Scanner(System.in);
				System.out.println("아이디를 입력하세요.");
				String viewId = s3.nextLine();
				List.viewCart(viewId);
				break;

//			case 4:
//				
//				System.out.println("프로그램을 종료합니다.");
//				s1.close();
//				return;
			// 아이디를 입력해서 렌트하기 기능으로 넘어가게
			// 찬엽씨가 만든 렌트메서드 호출

			default:
				System.out.println("메뉴 선택 오류입니다. 다시 선택해주세요.");
				break;
			}
		}
	}
}

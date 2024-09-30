package user;


import java.util.Scanner;

public class UserManager {


    private User[] users = null;


    public UserManager() {

    }

    public boolean loginProcess() {
        Scanner sc = new Scanner(System.in);
        System.out.println("아이디: ");
        String id = sc.nextLine();
        System.out.println("비밀번호: ");
        String pw = sc.nextLine();


        for (int i = 0; i < users.length; i++) {
            if (id.equals(users[i].getId()) && pw.equals(users[i].getPassword())) {
                System.out.println("로그인 되었습니다.");
                System.out.println("이름: " + users[i].getName() + "님 환영합니다!");
                users[i].setLoginState(true);
                return true;
            }
        }
        System.out.println("로그인 실패하였습니다.");
        return false;
    }

    public void logout(User user) {
        user.setLoginState(false);
    }
}

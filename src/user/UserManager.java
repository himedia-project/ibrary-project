package user;

import address.Address;
import address.AddressManager;
import menu.MenuManager;
import rent.Rent;
import rent.RentRepository;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;


public class UserManager {
    public static String currentUserEmail = null;
    private Scanner scanner = new Scanner(System.in);

    private UserRepository userRepository = null;
    private RentRepository rentRepository = null;

    public UserManager() {
        userRepository = new UserRepository();
        rentRepository = new RentRepository();
    }


    /**
     * 로그인 메서드
     *
     * @return 로그인 성공하면 true, 실패하면 false
     */
    public boolean loginProcess() {
        MenuManager menu = new MenuManager();
        System.out.print("   이메일: ");
        String id = scanner.nextLine();
        System.out.print("   비밀번호: ");
        String password = scanner.nextLine();

        currentUserEmail = id;  // 로그인한 유저의 이메일을 저장
        boolean success = userRepository.login(currentUserEmail, password);
        if (success) {
        	printLoginSuccessArt();
            menu.iBraryMenu();
            return true;
        } else {
        	printLoginFailureArt();
            return false;
        }
    }


    /**
     * 중복된 아이디가 있는지 확인하는 메서드
     *
     * @param id 중복을 확인할 아이디
     * @return 중복된 아이디가 있으면 true, 없으면 false
     */
    public boolean isIdDuplicate(String id) { // 중복값 체크 메서드
        return userRepository.findCountUserById(id) > 0;

    }

    /**
     * 회원가입 메서드
     *
     * @param user 회원가입할 유저 정보
     * @return 회원가입 성공하면 true, 실패하면 false
     */
    public boolean registerUser(User user) { // 적절히 값을 넣었는지 확인하는 메서드
        return userRepository.saveUser(user);
    }

    public void registerUser() { // 회원가입 메서드
        AddressManager addressManager = new AddressManager();
        while (true) {
            try {
                System.out.print("   이메일: ");
                String id = scanner.nextLine();

                if (isIdDuplicate(id)) {
                    System.out.println("중복 아이디입니다. 다른 아이디를 입력하십시오.");
                    continue;
                }

                if (!id.contains("@")) {
                    System.out.println("이메일 형식이 아닙니다. 다시 입력해주세요.");
                    continue;
                }
                currentUserEmail = id;
                
                System.out.print("   비밀번호: ");
                String password = scanner.nextLine();

                System.out.print("   이름: ");
                String name = scanner.nextLine();

                System.out.print("   전화번호: ");
                String phone = scanner.nextLine();

                System.out.print("   생년월일 (YYYY-MM-DD): ");
                String birthStr = scanner.nextLine();
                Date birth_date = Date.valueOf(birthStr);

                if (!birthStr.contains("-")) {
                    System.out.println("   잘못된 형식입니다. 다시 입력해주세요.");
                    continue;
                }

                System.out.println("   주소를 입력해주세요: ");
                Address address = addressManager.registAddress();
                long address_id = address.getId();


                User user = new User(id, password, name, phone, address_id, birth_date);
                boolean success = registerUser(user);
                if (success) {
                	printRegistrationSuccessArt();
                    return;
                } else {
                	printRegistrationFailureArt();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("   잘못 입력하셨습니다: " + e.getMessage());
                System.out.println("   다시 입력해 주세요.");
            }
        }
    }


    /**
     * 유저 정보 출력 메서드
     */
    public void showUserInfo() {
        for (User user : userRepository.findUserListById(currentUserEmail)) {
            System.out.println("\n\n");
            System.out.println("   =========================================================");
            System.out.println("   =                      마이 페이지                         =");
            System.out.println("   =                                                       =");
            System.out.println("   =         ██╗   ██╗███████╗███████╗██████╗              =");
            System.out.println("   =         ██║   ██║██╔════╝██╔════╝██╔══██╗             =");
            System.out.println("   =         ██║   ██║███████╗█████╗  ██████╔╝             =");
            System.out.println("   =         ██║   ██║╚════██║██╔══╝  ██╔══██╗             =");
            System.out.println("   =         ╚██████╔╝███████║███████╗██║  ██║             =");
            System.out.println("   =          ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝             =");
            System.out.println("   =                                                       =");
            System.out.println("   =      이메일: " + padRight(user.getId(), 37) + "     =");
            System.out.println("   =      이름: " + padRight(user.getName(), 39) + "     =");
            System.out.println("   =      전화번호: " + padRight(user.getPhone(), 35) + "      =");
            System.out.println("   =      주소: " + padRight(user.getAddr1() + " " + user.getAddr2(), 39) + "=");
            System.out.println("   =      우편번호: " + padRight(user.getZipcode(), 35) + "      =");
            System.out.println("   =      생년월일: " + padRight(user.getBirthDate().toString(), 35) + "      =");
            System.out.println("   =                                                        =");
            System.out.println("   ==========================================================");
        }
    }

    /**
     * 대여한 책 리스트 출력 메서드
     */
    public void showRentList() {
        List<Rent> rentList = this.rentRepository.rentListByUserId(currentUserEmail);
        System.out.println("\n\n");
        System.out.println("   =====================================================");
        System.out.println("   =                   대여 목록                          =");
        System.out.println("   =                                                   =");
        if (rentList.isEmpty()) {
            System.out.println("   =            대여한 책이 없습니다.                    =");
        } else {
            for (Rent rent : rentList) {
                System.out.println("   =  책 ID: " + padRight(rent.getBookId(), 37) + "     =");
                System.out.println("   =  대여 시작일: " + padRight(rent.getStartDate().toString(), 32) + "      =");
                System.out.println("   =  반납 예정일: " + padRight(rent.getEndDate().toString(), 32) + "      =");
                System.out.println("   =                                                   =");
            }
        }
        System.out.println("   =                                                   =");
        System.out.println("   =====================================================");
    }
    
    private String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
    
    private void printLoginSuccessArt() {
        System.out.println("\n");
        System.out.println("   =============================================");
        System.out.println("   =                                           =");
        System.out.println("   =   ██╗      ██████╗  ██████╗ ██╗███╗   ██╗ =");
        System.out.println("   =   ██║     ██╔═══██╗██╔════╝ ██║████╗  ██║ =");
        System.out.println("   =   ██║     ██║   ██║██║  ███╗██║██╔██╗ ██║ =");
        System.out.println("   =   ██║     ██║   ██║██║   ██║██║██║╚██╗██║ =");
        System.out.println("   =   ███████╗╚██████╔╝╚██████╔╝██║██║ ╚████║ =");
        System.out.println("   =   ╚══════╝ ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝ =");
        System.out.println("   =                                           =");
        System.out.println("   =                로그인 성공!                  =");
        System.out.println("   =         환영합니다, " + currentUserEmail + "님              =");
        System.out.println("   =                                           =");
        System.out.println("   =============================================");
        System.out.println("\n");
    }
    
    private void printLoginFailureArt() {
        System.out.println("\n");
        System.out.println("   ================================================");
        System.out.println("   =                                               =");
        System.out.println("   =   ███████╗ █████╗ ██╗██╗     ███████╗██████╗  =");
        System.out.println("   =   ██╔════╝██╔══██╗██║██║     ██╔════╝██╔══██╗ =");
        System.out.println("   =   █████╗  ███████║██║██║     █████╗  ██║  ██║ =");
        System.out.println("   =   ██╔══╝  ██╔══██║██║██║     ██╔══╝  ██║  ██║ =");
        System.out.println("   =   ██║     ██║  ██║██║███████╗███████╗██████╔╝ =");
        System.out.println("   =   ╚═╝     ╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═════╝  =");
        System.out.println("   =                                               =");
        System.out.println("   =                     로그인 실패!                 =");
        System.out.println("   =             이메일과 비밀번호를 확인해주세요.           =");
        System.out.println("   =                                               =");
        System.out.println("   ================================================");
        System.out.println("\n");
    }
    
    private void printRegistrationSuccessArt() {
        System.out.println("\n");
        System.out.println("   ====================================================================");
        System.out.println("   =                                                                  =");
        System.out.println("   =   ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗ =");
        System.out.println("   =   ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝ =");
        System.out.println("   =   ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗   =");
        System.out.println("   =   ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝   =");
        System.out.println("   =   ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗ =");
        System.out.println("   =    ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝ =");
        System.out.println("   =                                                                  =");
        System.out.println("   =                              회원가입 성공!                          =");
        System.out.println("   =                     환영합니다, " + currentUserEmail + "님         =");
        System.out.println("   =                                                                  =");
        System.out.println("   ====================================================================");
        System.out.println("\n");
    }
    
    private void printRegistrationFailureArt() {
        System.out.println("\n");
        System.out.println("   =================================================");
        System.out.println("   =                                               =");
        System.out.println("   =   ███████╗ █████╗ ██╗██╗     ███████╗██████╗  =");
        System.out.println("   =   ██╔════╝██╔══██╗██║██║     ██╔════╝██╔══██╗ =");
        System.out.println("   =   █████╗  ███████║██║██║     █████╗  ██║  ██║ =");
        System.out.println("   =   ██╔══╝  ██╔══██║██║██║     ██╔══╝  ██║  ██║ =");
        System.out.println("   =   ██║     ██║  ██║██║███████╗███████╗██████╔╝ =");
        System.out.println("   =   ╚═╝     ╚═╝  ╚═╝╚═╝╚══════╝╚══════╝╚═════╝  =");
        System.out.println("   =                                               =");
        System.out.println("   =                   회원가입 실패!                  =");
        System.out.println("   =           다시 시도해주세요. 문제가 지속되면            =");
        System.out.println("   =                 관리자에게 문의하세요.               =");
        System.out.println("   =                                               =");
        System.out.println("   =================================================");
        System.out.println("\n");
    }

    /**
     * 로그아웃 메서드
     */
    public void logout() {
        currentUserEmail = null;
        System.out.println("   로그아웃되었습니다.");
    }
}

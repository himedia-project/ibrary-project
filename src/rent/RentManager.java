package rent;


public class RentManager {

    private RentRepository rentRepository = null;

    public RentManager() {
        rentRepository = new RentRepository();
    }

    public void updateRented() {

    }


    public void insertRent(String bookId, String uesrId){
        rentRepository.insertRent(bookId, uesrId);
        System.out.println("책이 성공적으로 대출되었습니다.");
    }



}
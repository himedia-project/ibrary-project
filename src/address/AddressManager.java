package address;

import java.util.Scanner;

public class AddressManager {
	private Scanner scanner = new Scanner(System.in);

    private AddressRepository addressRepository = null;
    
	public AddressManager() {
		addressRepository = new AddressRepository();
	}

    /**
     * 주소 등록
     * @param address 주소
     * @return ???
     */
    public int registAddress(Address address) {
        return addressRepository.insertAddress(address);
    }

    /**
     * 주소 등록 입력 받기
     * @return 등록할 주소
     */
    public Address registAddress() {
			while(true) {
				System.out.print("   주소 (도/특별시/광역시, 시/군/구, 읍/면/동): ");
				String addr1 = scanner.nextLine();
				System.out.print("   상세 주소 (동/층/호수): ");
				String addr2 = scanner.nextLine();
<<<<<<< HEAD
				System.out.print("   우편번호 (5자리 우편번호):");
=======
				System.out.print("우편번호 (5자리 우편번호):");
>>>>>>> 1975e3398841df23c1342ef6bd0e3a3dbfdaf825
				int zip_code = scanner.nextInt();
				scanner.nextLine();
				
				Address address = new Address(0,addr1,addr2,zip_code);
				int addressId = registAddress(address);
                if(addressId != -1) {
                	System.out.println("   주소가 성공적으로 등록되었습니다.");
                    return address;
                } else {
                    System.out.println("   주소를 잘못 입력했습니다. 다시 입력해주세요.");
                }
			}		
    }
	
}

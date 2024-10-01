package address;

public class Address {
	private int id;
	private String addr1;
	private String addr2;
	private int zip_code;

	public Address(int pid,String paddr1,String paddr2, int pzip_code) {
		this.id =pid;
		this.addr1 = paddr1;
		this.addr2 = paddr2;
		this.zip_code = pzip_code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	
	
	
}

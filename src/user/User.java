package user;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class User {

    private String id;
    private String password;
    private String name;
    private String phone;
    private Long addressId;
    private Date birthDate;

    private boolean loginState = false;

    private String addr1;
    private String addr2;
    private String zipcode;

    public User(String id, String password, String name, String phone, Long addressId, Date birthDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.addressId = addressId;
        this.birthDate = birthDate;
    }


    public User(String id, String name, String phone, String addr1, String addr2, String zipcode, Date birthDate) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.zipcode = zipcode;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }
}

package user;


import java.util.Date;

public class User {

    private String id;
    private String password;
    private String name;

    private String phone;

    private Long addressId;

    private Date birthDate;

    private boolean loginState = false;

    public User(String id, String password, String name, String phone, Long addressId, Date birthDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.addressId = addressId;
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

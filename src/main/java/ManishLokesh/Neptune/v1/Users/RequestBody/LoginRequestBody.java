package ManishLokesh.Neptune.v1.Users.RequestBody;

public class LoginRequestBody {

    private String mobileNumber;
    private String password;

    @Override
    public String toString() {
        return "LoginRequestBody{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

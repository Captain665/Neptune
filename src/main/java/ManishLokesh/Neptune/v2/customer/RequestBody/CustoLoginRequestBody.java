package ManishLokesh.Neptune.v2.customer.RequestBody;

public class CustoLoginRequestBody {


    private String mobileNumber;
    private String password;


    @Override
    public String toString() {
        return "CustoLoginRequestBody{" +
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

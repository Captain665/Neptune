package ManishLokesh.Neptune.v2.customer.RequestBody;

import javax.validation.constraints.*;

public class CustoLoginRequestBody {


//    @NotNull(message = "mobileNumber should not null")
    @NotNull(message = "mobile number should be contain 10 digit and should not be null, blank, empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "mobile number should be contain 10 digit and should not be null, blank, empty")
    private String mobileNumber;
    @NotNull(message = "password should not be null, blank, empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "password should not be null, blank, empty and must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long.")
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

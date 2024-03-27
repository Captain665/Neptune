package ManishLokesh.Neptune.v2.customer.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustoLoginRequestBody {


    @NotNull(message = "mobileNumber is not null")
    @NotBlank(message = "mobileNumber is not blank")
    @NotEmpty(message = "mobileNumber is not empty")
    private String mobileNumber;
    @NotNull(message = "password is not null")
    @NotBlank(message = "password is not blank")
    @NotEmpty(message = "password is not empty")
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

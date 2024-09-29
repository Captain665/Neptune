package ManishLokesh.Neptune.v2.customer.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustoSignupRequestBody {

    @NotEmpty(message = "fullName should not null")
    @NotNull(message = "fullName should not blank")
    @NotBlank(message = "fullName should not empty")
    private String fullName;
    @NotNull(message = "mobileNumber should not null")
    @NotBlank(message = "mobileNumber should not blank")
    @NotEmpty(message = "mobileNumber should not empty")
    @Size(min = 10,max = 10, message = "The length of mobile number must be 10 numbers.")
    private String mobileNumber;
    @NotNull(message = "emailId should not null")
    @NotBlank(message = "emailId should not blank")
    @NotEmpty(message = "emailId should not empty")
    private String emailId;
    @NotNull(message = "password should not null")
    @NotBlank(message = "password should not blank")
    @NotEmpty(message = "password should not empty")
    private String password;
    @NotNull(message = "gender should not null")
    @NotBlank(message = "gender should not blank")
    @NotEmpty(message = "gender should not empty")
    private String gender;


    @Override
    public String toString() {
        return "CustoSignupRequestBody{" +
                "fullName='" + fullName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

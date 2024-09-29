package ManishLokesh.Neptune.v2.customer.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustOtpValidateRequestBody {

    @NotNull(message = "mobileNumber should not null")
    @NotBlank(message = "mobileNumber should not blank")
    @NotEmpty(message = "mobileNumber should not empty")
    @Size(min = 10,max = 10, message = "The length of mobile number must be 10 numbers.")
    private String mobileNumber;
    @NotNull(message = "otp should not null")
    @NotBlank(message = "otp should not blank")
    @NotEmpty(message = "otp should not empty")
    @Size(min = 4,max = 4, message = "The length of otp must be 10 numbers.")
    private String otp;

    @Override
    public String toString() {
        return "CustOtpValidateRequestBody{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

package ManishLokesh.Neptune.v1.Users.RequestBody;


import javax.validation.constraints.*;

public class OtpValidateRequestBody {

    @NotBlank(message = "Not blank")
    @NotNull(message = "not null")
    private String mobileNumber;

    @NotBlank(message = "otp are not blank")
    @NotNull(message = "otp are not null")
    private String otp;

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

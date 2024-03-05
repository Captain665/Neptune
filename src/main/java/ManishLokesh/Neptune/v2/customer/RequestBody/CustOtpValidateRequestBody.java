package ManishLokesh.Neptune.v2.customer.RequestBody;

public class CustOtpValidateRequestBody {

    private String mobileNumber;
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

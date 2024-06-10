package ManishLokesh.Neptune.v2.customer.ResponseBody;

public class CustOtpValidateResponseBody {
    private Long id;
    private String fullName;
    private String emailId;
    private String mobileNumber;
    private String gender;
    private String jwt;



    public CustOtpValidateResponseBody(Long id,
                               String fullName, String emailId, String mobileNumber, String gender,
                               String jwt){
        this.id = id;
        this.fullName = fullName;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

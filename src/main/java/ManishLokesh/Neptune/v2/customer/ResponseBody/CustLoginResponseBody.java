package ManishLokesh.Neptune.v2.customer.ResponseBody;

public class CustLoginResponseBody {

    private Long id;
    private String fullName;
    private String mobileNumber;
    private String emailId;
    private String jwt;
    private String gender;



    public CustLoginResponseBody(Long id, String fullName, String mobileNumber,
                                 String emailId, String jwt, String gender){
        this.id = id;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.jwt = jwt;
        this.gender = gender;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

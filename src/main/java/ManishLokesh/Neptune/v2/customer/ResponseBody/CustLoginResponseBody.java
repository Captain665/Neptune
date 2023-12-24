package ManishLokesh.Neptune.v2.customer.ResponseBody;

public class CustLoginResponseBody {

    private Long id;
    private String fullName;
    private String createdAt;
    private String mobileNumber;
    private String emailId;
    private String jwt;
    private String gender;
    private String updatedAt;
    private String lastLogin;
    private String role;


    public CustLoginResponseBody(Long id, String fullName, String createdAt, String mobileNumber,
                                 String emailId, String jwt, String gender, String updatedAt, String lastLogin,
                                 String role){
        this.id = id;
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
        this.jwt = jwt;
        this.gender = gender;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.role = role;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

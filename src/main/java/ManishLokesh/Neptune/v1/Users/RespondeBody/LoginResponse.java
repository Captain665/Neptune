package ManishLokesh.Neptune.v1.Users.RespondeBody;

public class LoginResponse {
    private Long id;
    private String createdAt;
    private String fullName;
    private String emailId;
    private String mobileNumber;
    private String gender;
    private String updatedAt;
    private String lastLogin;
    private String jwt;
    private String role;


    public LoginResponse(Long id, String createdAt,
                               String fullName, String emailId, String mobileNumber, String gender,
                               String updatedAt,String lastLogin,String jwt,String role){
        this.id = id;
        this.createdAt = createdAt;
        this.fullName = fullName;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.jwt = jwt;
        this.role = role;

    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", jwt='" + jwt + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}


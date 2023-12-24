package ManishLokesh.Neptune.v1.Users.Repository;

import ManishLokesh.Neptune.v1.Users.Entity.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepo extends JpaRepository<Signup,Long> {

    Signup findByEmailId(String emailId);
    Signup findByMobileNumber(String mobileNumber);

    Signup findByOtp(String otp);


}

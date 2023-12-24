package ManishLokesh.Neptune.v2.customer.Repository;


import ManishLokesh.Neptune.v2.customer.Entity.CustomerSignup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustSignupRepo extends JpaRepository<CustomerSignup,Long> {
    CustomerSignup findByMobileNumber(String mobileNumber);
}

package ManishLokesh.Neptune.v1.Users.Repository;

import ManishLokesh.Neptune.v1.Users.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepo extends JpaRepository<Login, Long> {
    Login findByMobileNumber(String mobileNumber);
}

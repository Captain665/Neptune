package ManishLokesh.Neptune.v2.customer.Repository;

import ManishLokesh.Neptune.v2.customer.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustLoginRepo extends JpaRepository<Customer,Long> {
    Customer findByMobileNumber(String mobileNumber);

    Optional<Customer> findById(Long Id);

    @Query(value = "SELECT * from Customer WHERE id = :id", nativeQuery = true )
    Customer findByCustomerId(@Param("id") Long id);
}

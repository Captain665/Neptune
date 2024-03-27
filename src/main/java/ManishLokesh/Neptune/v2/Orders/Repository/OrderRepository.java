package ManishLokesh.Neptune.v2.Orders.Repository;


import ManishLokesh.Neptune.v2.Orders.Entity.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {

    public Orders saveAndFlush(Orders orders);
    public Optional<Orders> findById(Long orderId);

    public List<Orders> findByCustomerId(String customerId);

    @Query(value = "SELECT o.id, o.status, o.delivery_date, o.irctc_order_id FROM orders o WHERE o.`status` not IN ('DELIVERED','UNDELIVERED','PARTIALLY_DELIVERED','CANCELLED') AND o.delivery_date is not null AND o.irctc_order_id is not null", nativeQuery = true)
    public List<String> OrderStatus();


}

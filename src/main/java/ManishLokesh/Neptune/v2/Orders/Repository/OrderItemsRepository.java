package ManishLokesh.Neptune.v2.Orders.Repository;

import ManishLokesh.Neptune.v2.Orders.Entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems,Long> {
    public List<OrderItems> findByOrderId(String orderId);
}

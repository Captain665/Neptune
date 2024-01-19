package ManishLokesh.Neptune.v2.Orders.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderStatusBody;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    public ResponseEntity<ResponseDTO> addOrder(OrderRequestBody orderRequestBody);

    public ResponseEntity<ResponseDTO> getOrder(Long orderId, Long customerId);

    public ResponseEntity<ResponseDTO> getAllOrder(Long customerId);
    public ResponseEntity<ResponseDTO> updateStatus(OrderStatusBody orderStatusBody,Long orderId);

}

package ManishLokesh.Neptune.v2.Orders.Controller;


import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderStatusBody;
import ManishLokesh.Neptune.v2.Orders.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Objects;

@Controller
public class OrderController {

    @Autowired
    public OrderService service;

    @Autowired
    public JwtUtil jwtUtil;

    @PostMapping("/api/v2/create/order")
    public ResponseEntity<ResponseDTO> createOrder(@RequestBody OrderRequestBody orderRequestBody, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")){
                return this.service.addOrder(orderRequestBody);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/api/v2/order/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetails(@PathVariable Long orderId,@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")){
            return this.service.getOrder(orderId);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/api/v2/orders")
    public ResponseEntity<ResponseDTO> getAllOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")){
            return this.service.getAllOrder();
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/api/v2/order/{orderId}/status")
    public ResponseEntity<ResponseDTO>statusUpdate(@RequestBody OrderStatusBody orderStatusBody, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable String orderId){
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")){
            return this.service.updateStatus(orderStatusBody,Long.parseLong(orderId));
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);

    }
}

package ManishLokesh.Neptune.v2.Orders.Controller;


import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.Response;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderStatusBody;
import ManishLokesh.Neptune.v2.Orders.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

import static ManishLokesh.Neptune.ResponseDTO.Response.*;

@Controller
public class OrderController {

    @Autowired
    public OrderService service;

    @Autowired
    public JwtUtil jwtUtil;
    public Response response;

    public final Logger logger = LoggerFactory.getLogger("Status.Update.On.IRCTC");


    @PostMapping("/api/v2/create/order")
    public CompletionStage<ResponseEntity<ResponseDTO>> createOrder(@RequestBody OrderRequestBody orderRequestBody, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        try{
//            Thread.sleep(5000);
            String token = auth.replace("Bearer", "");
            if (Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")) {
                return this.service.addOrder(orderRequestBody);
            }
        }catch (Exception e){
            return ApiUnauthorized();
        }
        return ApiUnauthorized();
    }

    @GetMapping("/api/v2/order/{orderId}")
    public ResponseEntity<ResponseDTO> getOrderDetails(@PathVariable Long orderId, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer", "");
        if (Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")) {
            try {
//                Thread.sleep(5000);
                Long customerId = jwtUtil.validateId(token);
                return this.service.getOrder(orderId, customerId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/api/v2/orders")
    public ResponseEntity<ResponseDTO> getAllOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer", "");
        if (Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")) {
            try {
//                Thread.sleep(5000);
                Long customerId = jwtUtil.validateId(token);
                return this.service.getAllOrder(customerId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/api/v2/order/{orderId}/status")
    public CompletionStage<ResponseEntity<ResponseDTO>> statusUpdate(@RequestBody OrderStatusBody orderStatusBody, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth, @PathVariable String orderId) {
        String token = auth.replace("Bearer", "");
        if (Objects.equals(jwtUtil.validateRole(token), "CUSTOMER")) {
            try {
//                Thread.sleep(5000);
                return this.service.updateStatus(orderStatusBody, Long.parseLong(orderId));
            } catch (Exception e) {
                logger.info("exception msg {}",e.getMessage());
                e.printStackTrace();
            }
            return response.ApiFailure("Something went wrong, Please try again later");
        }
        return ApiUnauthorized();

    }
}

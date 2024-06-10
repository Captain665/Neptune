package ManishLokesh.Neptune.v2.payment;import ManishLokesh.Neptune.AuthController.JwtUtil;import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpHeaders;import org.springframework.http.ResponseEntity;import org.springframework.stereotype.Controller;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestHeader;import java.util.Objects;import java.util.concurrent.CompletionStage;import java.util.logging.Logger;import static ManishLokesh.Neptune.ResponseDTO.Response.ApiFailure;import static ManishLokesh.Neptune.ResponseDTO.Response.ApiUnauthorized;@Controllerpublic class PaymentController {    @Autowired    public JwtUtil jwtUtil;    @Autowired    public PaymentService paymentService;    @GetMapping("/api/v2/payment/available")    public CompletionStage<ResponseEntity<ResponseDTO>> getPaymentOption(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){        try{//            Thread.sleep(5000);            String token = auth.replace("Bearer", "");            if(Objects.equals(jwtUtil.validateRole(token),"CUSTOMER")) {                return this.paymentService.getPaymentMethodsDetail();            }        }catch (Exception e){            e.printStackTrace();            return ApiFailure(e.getMessage());        }        return ApiUnauthorized();    }}
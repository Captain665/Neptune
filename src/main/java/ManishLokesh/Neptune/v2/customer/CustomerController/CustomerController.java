package ManishLokesh.Neptune.v2.customer.CustomerController;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustOtpValidateRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoSignupRequestBody;
import ManishLokesh.Neptune.v2.customer.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    public CustomerService customerLoginService;

    @PostMapping("/api/v2/auth/login")
    public ResponseEntity<ResponseDTO> CustomerLogin(@RequestBody CustoLoginRequestBody loginRequestBody){
        return this.customerLoginService.CustomerAuthLogin(loginRequestBody);
    }

    @PostMapping("/api/v2/otp-validate")
    public ResponseEntity<ResponseDTO> valdateOtp(@RequestBody CustOtpValidateRequestBody otpValidateRequestBody){
        return this.customerLoginService.CustoOtpValidate(otpValidateRequestBody);
    }
    @PostMapping("/api/v2/signup")
    public ResponseEntity<ResponseDTO> customerSignUp(@RequestBody CustoSignupRequestBody newCustomerSignup){
        return this.customerLoginService.newCustomerSignUp(newCustomerSignup);
    }
}

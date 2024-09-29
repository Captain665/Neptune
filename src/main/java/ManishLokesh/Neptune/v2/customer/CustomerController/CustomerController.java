package ManishLokesh.Neptune.v2.customer.CustomerController;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustOtpValidateRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoSignupRequestBody;
import ManishLokesh.Neptune.v2.customer.Service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    public CustomerService customerLoginService;

    public Logger logger = LoggerFactory.getLogger("app.v2.customer");

    @PostMapping("/api/v2/auth/login")
    public ResponseEntity<ResponseDTO> CustomerLogin(@Valid @RequestBody CustoLoginRequestBody loginRequestBody){
//        try{
//            Thread.sleep(5000);
            return this.customerLoginService.CustomerAuthLogin(loginRequestBody);
//        }catch (Exception e){
//            e.printStackTrace();
//            logger.info(e.getLocalizedMessage());
//        }
//        return null;
    }

    @PostMapping("/api/v2/otp-validate")
    public ResponseEntity<ResponseDTO> valdateOtp(@Valid @RequestBody CustOtpValidateRequestBody otpValidateRequestBody){
        try{
//            Thread.sleep(5000);
            return this.customerLoginService.CustoOtpValidate(otpValidateRequestBody);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/api/v2/signup")
    public ResponseEntity<ResponseDTO> customerSignUp(@Valid @RequestBody CustoSignupRequestBody newCustomerSignup){
        try{
//            Thread.sleep(5000);
            return this.customerLoginService.newCustomerSignUp(newCustomerSignup);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

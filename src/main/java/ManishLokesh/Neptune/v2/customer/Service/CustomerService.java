package ManishLokesh.Neptune.v2.customer.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustOtpValidateRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import ManishLokesh.Neptune.v2.customer.RequestBody.CustoSignupRequestBody;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    public ResponseEntity<ResponseDTO>CustomerAuthLogin(CustoLoginRequestBody loginRequestBody);

    public ResponseEntity<ResponseDTO> CustoOtpValidate(CustOtpValidateRequestBody validateRequestBody);

    public ResponseEntity<ResponseDTO> newCustomerSignUp(CustoSignupRequestBody requestBody);
}

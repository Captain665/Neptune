package ManishLokesh.Neptune.v1.Users.Service;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.Users.RequestBody.LoginRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.OtpValidateRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.SignupRequestBody;
import org.springframework.http.ResponseEntity;


public interface SignupService {

    public ResponseEntity<ResponseDTO> signup(SignupRequestBody requestBody);

    public ResponseEntity<ResponseDTO> otpValidate(OtpValidateRequestBody otpValidateRequestBody);

    public ResponseEntity<ResponseDTO> login(LoginRequestBody loginRequestBody);

}

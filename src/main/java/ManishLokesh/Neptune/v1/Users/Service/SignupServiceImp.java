package ManishLokesh.Neptune.v1.Users.Service;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.EmailTrigger.SendSignupOTP;
import ManishLokesh.Neptune.Common.ResponseDTO;
import ManishLokesh.Neptune.v1.Users.Entity.Login;
import ManishLokesh.Neptune.v1.Users.Entity.Signup;
import ManishLokesh.Neptune.v1.Users.Repository.LoginRepo;
import ManishLokesh.Neptune.v1.Users.Repository.SignupRepo;
import ManishLokesh.Neptune.v1.Users.RequestBody.LoginRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.OtpValidateRequestBody;
import ManishLokesh.Neptune.v1.Users.RequestBody.SignupRequestBody;
import ManishLokesh.Neptune.v1.Users.RespondeBody.LoginResponse;
import ManishLokesh.Neptune.v1.Users.RespondeBody.OtpValidateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.concurrent.CompletableFuture.runAsync;

@Service
public class SignupServiceImp implements SignupService{
    @Autowired
    public SignupRepo signupRepo;
    @Autowired
    public LoginRepo loginRepo;
    @Autowired
    private JwtUtil jwtUtil;
    private final SendSignupOTP sendSignupOTP = new SendSignupOTP();
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private Logger logger = LoggerFactory.getLogger("app.signup.service.v1");
    @Override
    public ResponseEntity<ResponseDTO> signup(SignupRequestBody requestBody) {
        logger.info("api/v1/signup request json {}",requestBody.toString());
        try{
            Signup exist = signupRepo.findByMobileNumber(requestBody.getMobileNumber());

            if(exist != null){
                if(loginRepo.findByMobileNumber(requestBody.getMobileNumber()) != null){
                    return new ResponseEntity<>(
                            new ResponseDTO<>("failure","Mobile number already " +
                                    "exist, Please try with another mobile number",null),HttpStatus.BAD_REQUEST);
                }else{
                    exist.setUpdatedAt(LocalDateTime.now().toString());
                    String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
                    exist.setOtp(otp);
                    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                    String password = bCryptPasswordEncoder.encode(requestBody.getPassword());
                    exist.setPassword(password);
                    signupRepo.save(exist);
                    logger.info("email Id : {}",requestBody.getEmailId());
                    logger.info("otp : {}",otp);
                    runAsync(() -> sendSignupOTP.sendOTP(requestBody.getEmailId(), otp,"Vendor Signup OTP","Your Signup OTP is :"));
                    return new ResponseEntity<>(new ResponseDTO<>("success",null,
                            "OTP sent to the registered Email ID"),HttpStatus.OK);
                }
            }
            Signup signup = new Signup();
            signup.setFullName(requestBody.getFullName());
            signup.setGender(requestBody.getGender());
            signup.setEmailId(requestBody.getEmailId());
            signup.setMobileNumber(requestBody.getMobileNumber());
            //signup.setPassword(requestBody.getPassword());
            signup.setCreatedAt(LocalDateTime.now().toString());
            String otp = String.valueOf((int) (Math.random() * 9000) + 1000);
            signup.setOtp(otp);
            String password = bCryptPasswordEncoder.encode(requestBody.getPassword());
            signup.setPassword(password);
            signupRepo.save(signup);
            logger.info("email Id : {}",signup.getEmailId());
            logger.info("otp : {}",otp);
            logger.info("response : OTP sent to the registered Email Id");
            runAsync(() -> sendSignupOTP.sendOTP(signup.getEmailId(), otp,"Vendor Signup OTP","Your Signup OTP is :"));
            return new ResponseEntity<>(new ResponseDTO<>("success",null,
                    "OTP sent to the registered Email Id"),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    new ResponseDTO<>("failure","Something went wrong, Please try after some time",null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> otpValidate(OtpValidateRequestBody otpValidateRequestBody) {
        logger.info("api/v1/otp-validate request json {}",otpValidateRequestBody.toString());
        try{
            if(signupRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber()) == null){
                return new ResponseEntity<>(new ResponseDTO("failure","Incorrect mobile number",
                        null),HttpStatus.BAD_REQUEST);
            }else{
                Signup signup = signupRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber());
                if(!Objects.equals(signup.getOtp(), otpValidateRequestBody.getOtp())){
                    return new ResponseEntity<>(new ResponseDTO("failure","Incorrect OTP Value",
                            null),HttpStatus.BAD_REQUEST);
                }else{
                    Login mobile = loginRepo.findByMobileNumber(otpValidateRequestBody.getMobileNumber());
                    if(mobile == null){
                        Login login = new Login();
                        login.setFullName(signup.getFullName());
                        login.setMobileNumber(signup.getMobileNumber());
                        login.setEmailId(signup.getEmailId());
                        login.setGender(signup.getGender());
                        login.setPassword(signup.getPassword());
                        login.setCreatedAt(LocalDateTime.now().toString());
                        String userRole = "ADMIN";
                        login.setRole(userRole);
                        Login login1 = loginRepo.saveAndFlush(login);
                        String token = jwtUtil.generateToken(login1.getRole(),login.getId());
                        OtpValidateResponse res = new OtpValidateResponse(login1.getId(), login1.getCreatedAt(), login1.getFullName(), login1.getEmailId(),
                                login1.getMobileNumber(),login1.getGender(), login1.getUpdatedAt(),token,userRole);

                        logger.info("response : {}", res.toString());
                        return new ResponseEntity<>(new ResponseDTO("Success",null,res) ,HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>(new ResponseDTO("failure","Account already Created",
                                null),HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseDTO<>("failure","Something went wrong, Please try after some time",null)
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

@Override
public ResponseEntity<ResponseDTO> login(LoginRequestBody loginRequestBody) {
        logger.info("api/v1/login request json {}",loginRequestBody.toString());
        try{
            Login login = loginRepo.findByMobileNumber(loginRequestBody.getMobileNumber());
            if (login != null) {
                if (Objects.equals(login.getMobileNumber(), loginRequestBody.getMobileNumber())) {
                    if (bCryptPasswordEncoder.matches(loginRequestBody.getPassword(), login.getPassword())) {
                        login.setLastLogin(LocalDateTime.now().toString());
                        loginRepo.save(login);
                        String token = jwtUtil.generateToken(login.getRole(),login.getId());
                        final LoginResponse res = new LoginResponse(login.getId(), login.getCreatedAt(),
                                login.getFullName(), login.getEmailId(),
                                login.getMobileNumber(), login.getGender(), login.getUpdatedAt(),
                                login.getLastLogin(),token,login.getRole());

                        logger.info("response : {}",res.toString());

                        return new ResponseEntity<>(new ResponseDTO("Success", null, res), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new ResponseDTO("failure", "Incorrect mobile number or Password",
                                null), HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>(new ResponseDTO("failure", "Incorrect mobile number or Password",
                            null), HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>(new ResponseDTO("failure", "Incorrect mobile number",
                        null), HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ignore){
            return new ResponseEntity<>(new ResponseDTO<>("failure","Something went wrong, Please try after some time",null)
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


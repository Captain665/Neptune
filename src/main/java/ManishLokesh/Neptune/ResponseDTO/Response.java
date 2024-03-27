package ManishLokesh.Neptune.ResponseDTO;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import java.util.concurrent.CompletableFuture;import java.util.concurrent.CompletionStage;public class Response {    static final String SUCCESS = "success";    static final String FAILURE = "failure";    public static CompletionStage<ResponseEntity<ResponseDTO>> ApiSuccess(Object result){        ResponseDTO responseDTO = new ResponseDTO<>(SUCCESS,null,result);        return CompletableFuture.completedFuture(new ResponseEntity<>(responseDTO,HttpStatus.OK));    }    public static CompletionStage<ResponseEntity<ResponseDTO>> ApiFailure(String message){        ResponseDTO responseDTO = new ResponseDTO<>(FAILURE,message,null);        return CompletableFuture.completedFuture(new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST));    }    public static CompletionStage<ResponseEntity<ResponseDTO>> ApiUnauthorized(){        ResponseDTO responseDTO = new ResponseDTO<>(FAILURE,"Not authorize to Access",null);        return CompletableFuture.completedFuture(new ResponseEntity<>(responseDTO,HttpStatus.UNAUTHORIZED));    }}
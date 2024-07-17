package ManishLokesh.Neptune.Common;

public class ResponseDTO<T>{
    public String status;
    public String error;
    public Object result;


    public ResponseDTO(String status, String error, T result) {
        this.status = status;
        this.error = error;
        this.result = result;
    }

    public ResponseDTO(){

    }


}

package ManishLokesh.Neptune.ResponseDTO;

public class ResponseDTO<T>{
    public String status;
    public String error;
    public Object result;


    public ResponseDTO(String status, String error, Object result) {
        this.status = status;
        this.error = error;
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", result=" + result +
                '}';
    }

    public ResponseDTO(){

    }


}

package ManishLokesh.Neptune.v2.Outlets.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface GetOutletService{
    public ResponseEntity<ResponseDTO>GetOutletAll(String stationCode);

}

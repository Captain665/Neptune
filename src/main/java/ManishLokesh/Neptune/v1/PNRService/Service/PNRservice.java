package ManishLokesh.Neptune.v1.PNRService.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface PNRservice {

    public ResponseEntity<ResponseDTO> getPnrDetails(Long pnr);
}

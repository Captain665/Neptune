package ManishLokesh.Neptune.v2.Menu.Service;

import ManishLokesh.Neptune.Common.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface GetMenuService {

    public ResponseEntity<ResponseDTO> getActiveMenu(String outletId);
}

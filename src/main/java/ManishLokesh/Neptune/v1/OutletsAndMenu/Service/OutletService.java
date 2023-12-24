package ManishLokesh.Neptune.v1.OutletsAndMenu.Service;

import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface OutletService {

    public ResponseEntity<ResponseDTO> CreateNewOutlet(CreateOutlet createOutlet);

    public ResponseEntity<ResponseDTO>UpdateOutlet(CreateOutlet updateOutlet,Long outletId);

    public ResponseEntity<ResponseDTO> CreateNewMenu(Long outletId,CreateMenu createMenu);
    public ResponseEntity<ResponseDTO> UpdateMenu(CreateMenu updateMenu,Long outletId, Long menuId);

    public ResponseEntity<ResponseDTO>ActiveOutlet(Long outletId,Boolean status);

    public ResponseEntity<ResponseDTO>ActiveMenu(Long menuId,Boolean status);

}

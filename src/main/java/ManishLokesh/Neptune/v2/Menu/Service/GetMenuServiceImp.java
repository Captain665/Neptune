package ManishLokesh.Neptune.v2.Menu.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import ManishLokesh.Neptune.v2.Menu.Repository.GetMenuRepo;
import ManishLokesh.Neptune.v2.Outlets.Repository.GetOutletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetMenuServiceImp implements GetMenuService {

    @Autowired
    public GetMenuRepo getMenuRepo;
    public GetOutletRepo getOutletRepo;

    public Logger logger = LoggerFactory.getLogger("app.v2.outlet.menu");

    @Override
    public ResponseEntity<ResponseDTO> getActiveMenu(String outletId) {
        List<Menu> menuList = getMenuRepo.findByOutletId(outletId);
        List<Menu> activeItemList = menuList.stream().filter(Menu::getActive).collect(Collectors.toList());
        logger.info("outlet id {}",outletId);
        logger.info("response {}", menuList);
        return new ResponseEntity<>(new ResponseDTO("success",null,activeItemList),
                HttpStatus.OK);
    }
}

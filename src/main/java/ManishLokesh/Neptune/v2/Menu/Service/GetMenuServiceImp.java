package ManishLokesh.Neptune.v2.Menu.Service;

import ManishLokesh.Neptune.Common.ResponseDTO;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import ManishLokesh.Neptune.v2.Menu.MenuResponse.MenuResponse;
import ManishLokesh.Neptune.v2.Menu.Repository.GetMenuRepo;
import ManishLokesh.Neptune.v2.Outlets.Repository.GetOutletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if(activeItemList.isEmpty()){
            return new ResponseEntity<>(new ResponseDTO<>("failure","Menu items not exist for this station",null),HttpStatus.BAD_REQUEST);
        }
        List<MenuResponse> menuResponse = new ArrayList<>();

        for(Menu menu : activeItemList){
            MenuResponse menuResponse1 =  new MenuResponse();
            menuResponse1.setId(menu.getId());
            menuResponse1.setName(menu.getName());
            menuResponse1.setDescription(menu.getDescription());
            menuResponse1.setBasePrice(menu.getBasePrice());
            menuResponse1.setImage(menu.getImage());
            menuResponse1.setIsVegeterian(menu.getIsVegeterian());
            menuResponse.add(menuResponse1);
        }

        return new ResponseEntity<>(new ResponseDTO("success",null,menuResponse),
                HttpStatus.OK);
    }
}

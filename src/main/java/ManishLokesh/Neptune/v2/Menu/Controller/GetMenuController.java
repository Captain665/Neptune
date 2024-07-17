package ManishLokesh.Neptune.v2.Menu.Controller;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.Common.ResponseDTO;
import ManishLokesh.Neptune.v2.Menu.Service.GetMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetMenuController {

    @Autowired
    public GetMenuService service;
    @Autowired
    public JwtUtil jwtUtil;

    public Logger logger = LoggerFactory.getLogger("app.v2.outlet.menu");

    @GetMapping("api/v2/outlet/{outlet_Id}/menu")
    public ResponseEntity<ResponseDTO> getMenu(@PathVariable("outlet_Id") String outletId) {
        try{
            logger.info("menu calling");
            logger.info("outlet Id {}",outletId);
//            Thread.sleep(5000);
            return this.service.getActiveMenu(outletId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
    
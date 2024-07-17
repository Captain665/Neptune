package ManishLokesh.Neptune.v1.OutletsAndMenu.Controller;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Service.OutletService;
import ManishLokesh.Neptune.Common.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class OutletController {

    @Autowired
    public OutletService outletService;
    @Autowired
    public JwtUtil jwtUtil;

    @PostMapping("api/v1/add/outlet")
    public ResponseEntity<ResponseDTO> addNewOutlet(@RequestBody CreateOutlet createOutlet, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer","");
        if (Objects.equals(jwtUtil.validateRole(token), "ADMIN")) {
            return this.outletService.CreateNewOutlet(createOutlet);
        }
        return new ResponseEntity<>(new ResponseDTO("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("api/v1/update/outlet/{outletId}")
    public ResponseEntity<ResponseDTO> updateOutlet(@RequestBody CreateOutlet outlet, @PathVariable String outletId, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "ADMIN")){
            return this.outletService.UpdateOutlet(outlet, Long.parseLong(outletId));
        }
        return new ResponseEntity<>(new ResponseDTO("failure", "Not authorize to Access", null), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("api/v1/add/menu/outlet/{outletId}")
    public ResponseEntity<ResponseDTO>addNewMenu(@PathVariable String outletId, @RequestBody CreateMenu createMenu, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "ADMIN")){
            return this.outletService.CreateNewMenu(Long.parseLong(outletId), createMenu);
        }
        return new ResponseEntity<>(new ResponseDTO("failure","Not authorize to Access",null),HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("api/v1/outlet/{outletId}/menu/{menuId}")
    public ResponseEntity<ResponseDTO> updateMenu(@RequestBody CreateMenu menu,@PathVariable String outletId,@PathVariable String menuId, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "ADMIN")){
            return this.outletService.UpdateMenu(menu,Long.parseLong(outletId),Long.parseLong(menuId));
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Not authorize to Access",null), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("api/v1/active/outlet/{outletId}")
    public ResponseEntity<ResponseDTO>activeOutlet(@PathVariable String outletId,@RequestParam Boolean status ,@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "ADMIN")){
            return this.outletService.ActiveOutlet(Long.parseLong(outletId),status);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Not authorize to Access",null),
                HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("api/v1/active/menu/{menuId}")
    public ResponseEntity<ResponseDTO>activeMenu(@PathVariable String menuId,@RequestParam Boolean status ,@RequestHeader(HttpHeaders.AUTHORIZATION) String auth){
        String token = auth.replace("Bearer","");
        if(Objects.equals(jwtUtil.validateRole(token), "ADMIN")){
            return this.outletService.ActiveMenu(Long.parseLong(menuId),status);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Not authorize to Access",null),
                HttpStatus.UNAUTHORIZED);
    }
}

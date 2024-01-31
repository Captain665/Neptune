package ManishLokesh.Neptune.v2.Outlets.Controller;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v2.Outlets.Service.GetOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Objects;

@Controller
public class GetOutletController {

    @Autowired
    public GetOutletService getOutletService;
    @Autowired
    public JwtUtil jwtUtil;

    @GetMapping("/api/v2/outlet/station/{stationCode}")
    public ResponseEntity<ResponseDTO> GetOutlet(@PathVariable String stationCode) {
        try{
            Thread.sleep(2000);
            return this.getOutletService.GetOutletAll(stationCode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

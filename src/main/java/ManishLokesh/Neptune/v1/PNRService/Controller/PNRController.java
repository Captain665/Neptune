package ManishLokesh.Neptune.v1.PNRService.Controller;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.PNRService.Service.PNRservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PNRController {

    @Autowired
    public PNRservice pnRservice;

    @GetMapping("api/v1/pnr/{PNR}")
    public ResponseEntity<ResponseDTO> getPnrDetails(@PathVariable String PNR){
        if(PNR.length() == 10){
            return this.pnRservice.getPnrDetails(Long.parseLong(PNR));
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Please Entry Valid PNR",null),
                HttpStatus.BAD_REQUEST);
    }
}

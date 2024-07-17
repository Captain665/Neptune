package ManishLokesh.Neptune.v1.PNRService.Controller;

import ManishLokesh.Neptune.Common.ResponseDTO;
import ManishLokesh.Neptune.v1.PNRService.Service.PNRservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public Logger logger = LoggerFactory.getLogger("app.v1.pnr.service");
    
    @GetMapping("api/v2/pnr/{PNR}") 
    public ResponseEntity<ResponseDTO> getPnrDetails(@PathVariable String PNR){

        if(PNR.length() == 10){
                return this.pnRservice.getPnrDetails(Long.parseLong(PNR));
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","PNR number Should have 10 digit",null),
                HttpStatus.BAD_REQUEST);
    }
}

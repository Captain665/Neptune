package ManishLokesh.Neptune.v2.Outlets.Service;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import ManishLokesh.Neptune.v2.Outlets.OutletResponse.OutletResponse;
import ManishLokesh.Neptune.v2.Outlets.Repository.GetOutletRepo;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetOutletServiceimp implements GetOutletService{

    @Autowired
    public GetOutletRepo outletRepo;
    public Logger logger = LoggerFactory.getLogger("app.v2.outlet.service");

    @Override
    public ResponseEntity<ResponseDTO> GetOutletAll(String stationCode) {

        List<Outlet> outletList = outletRepo.findByStationCode(stationCode);
        List<Outlet> activeOutlets = outletList.stream().filter(Outlet::getActive).collect(Collectors.toList());
        logger.info("outlet data {}", activeOutlets.stream().collect(Collectors.toList()));
        for (Outlet outlet : activeOutlets){
            logger.info("active outlets id {}", outlet.getId());
        }

        return new ResponseEntity<>(
                new ResponseDTO("success",null,activeOutlets), HttpStatus.OK);
    }
}

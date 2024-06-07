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

import java.util.ArrayList;
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
        List<OutletResponse> listOfOutlets =  new ArrayList<>();

        for (Outlet outlet : activeOutlets){
            OutletResponse outletResponse = new OutletResponse();
            outletResponse.setId(outlet.getId());
            outletResponse.setOutletName(outlet.getOutletName());
            outletResponse.setMinOrderValue(outlet.getMinOrderValue());
            outletResponse.setOrderTiming(outlet.getOrderTiming());
            outletResponse.setDeliveryCost(outlet.getDeliveryCost());
            outletResponse.setPrepaid(outlet.getPrepaid());
            outletResponse.setLogoImage(outlet.getLogoImage());
            outletResponse.setTags(outlet.getTags());
            outletResponse.setRatingCount(outlet.getRatingCount());
            outletResponse.setRatingValue(outlet.getRatingValue());
            listOfOutlets.add(outletResponse);
        }
        logger.info("Outlet Response {}",listOfOutlets.toString());

        return new ResponseEntity<>(
                new ResponseDTO("success",null,listOfOutlets), HttpStatus.OK);
    }
}

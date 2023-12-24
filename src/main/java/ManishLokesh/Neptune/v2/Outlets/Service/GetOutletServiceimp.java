package ManishLokesh.Neptune.v2.Outlets.Service;


import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import ManishLokesh.Neptune.v2.Outlets.Repository.GetOutletRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetOutletServiceimp implements GetOutletService{

    @Autowired
    public GetOutletRepo outletRepo;

    @Override
    public ResponseEntity<ResponseDTO> GetOutletAll(String stationCode) {

        List<Outlet> outletList = outletRepo.findByStationCode(stationCode);
        List<Outlet> activeOutlets = outletList.stream().filter(Outlet::getActive).collect(Collectors.toList());
        return new ResponseEntity<>(
                new ResponseDTO("success",null,activeOutlets), HttpStatus.OK);
    }
}

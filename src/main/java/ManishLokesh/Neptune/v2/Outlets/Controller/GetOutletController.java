package ManishLokesh.Neptune.v2.Outlets.Controller;

import ManishLokesh.Neptune.AuthController.JwtUtil;
import ManishLokesh.Neptune.Common.ApiFailure;
import ManishLokesh.Neptune.Common.ResponseDTO;
import ManishLokesh.Neptune.v2.Outlets.Service.GetOutletService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.xml.transform.Result;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Controller
public class GetOutletController {

    @Autowired
    public GetOutletService getOutletService;
    @Autowired
    public JwtUtil jwtUtil;

    @GetMapping("/api/v2/outlet/station/{stationCode}")
    public CompletionStage<Result> ListStationOutlet(@PathVariable String stationCode) {
        try {
//            Thread.sleep(5000);
            return supplyAsync(() -> this.getOutletService.GetOutletAll(stationCode));

        } catch (Exception e) {
            return supplyAsync(() -> ResponseEntity.badRequest(new ApiFailure("Some problem occured")));
        }
    }
}

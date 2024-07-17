package ManishLokesh.Neptune.v2.Outlets.Service;

import ManishLokesh.Neptune.Common.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletionStage;

public interface GetOutletService{
    public CompletionStage<Result> GetOutletAll(String stationCode);

}

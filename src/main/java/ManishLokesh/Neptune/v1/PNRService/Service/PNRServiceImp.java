package ManishLokesh.Neptune.v1.PNRService.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;


@Service
public class PNRServiceImp implements PNRservice{

    private RestTemplate restTemplate;
    private final String EcateUrl;
    private final String authToken;
    private final ObjectMapper objectMapper;
    public Logger logger = LoggerFactory.getLogger("app.v1.pnr.service");

    @Autowired
    public PNRServiceImp(RestTemplate restTemplate, @Value("${E-catering.stage.url}") String ecateUrl,
                         @Value("${E-catering.auth.token}") String authToken,ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.EcateUrl = ecateUrl;
        this.authToken = authToken;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<ResponseDTO> getPnrDetails(Long pnr) {
        logger.info("requested pnr number :- {}",pnr);
        ArrayList<String> response = new ArrayList<>();
        try{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization",authToken);
            ResponseEntity<String> responseEntity = this.restTemplate.exchange(
                    EcateUrl+"api/v1/pnr/vendor?pnr="+pnr,
                    HttpMethod.GET,
                    new HttpEntity<>(httpHeaders),
                    String.class
            );
            response.add(responseEntity.getBody());
            logger.info("response body get from IRCTC server {}",responseEntity.getBody());
            logger.info("response code {}", responseEntity.getStatusCode());
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                String responseBody = responseEntity.getBody();
                try {
                    JsonNode jsonNode = objectMapper.readTree(responseBody);
                    JsonNode resultObject = jsonNode.get("result");
                    return new ResponseEntity<>(new ResponseDTO<>("success",null,resultObject),
                            HttpStatus.OK);
                }catch (JsonProcessingException e){
                    return new ResponseEntity<>(new ResponseDTO<>("failure", "JSON processing error", null),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }catch (HttpClientErrorException e){
            logger.info("response :- {}",response);
            return new ResponseEntity<>(new ResponseDTO<>("faliure","IRCTC PNR api throwing error",response),
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Something went wrong",null),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

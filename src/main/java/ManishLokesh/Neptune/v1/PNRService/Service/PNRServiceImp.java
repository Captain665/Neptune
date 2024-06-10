package ManishLokesh.Neptune.v1.PNRService.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.PNRService.PnrResponse.PnrResponse;
import ManishLokesh.Neptune.v1.PNRService.PnrResponse.SeatResponse;
import ManishLokesh.Neptune.v1.PNRService.PnrResponse.StationResponse;
import ManishLokesh.Neptune.v1.PNRService.PnrResponse.TrainResponse;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class PNRServiceImp implements PNRservice {

    private RestTemplate restTemplate;
    private final String EcateUrl;
    private final String authToken;
    private final ObjectMapper objectMapper;
    public Logger logger = LoggerFactory.getLogger("app.v1.pnr.service");

    @Autowired
    public PNRServiceImp(RestTemplate restTemplate, @Value("${E-catering.stage.url}") String ecateUrl,
                         @Value("${E-catering.auth.token}") String authToken, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.EcateUrl = ecateUrl;
        this.authToken = authToken;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<ResponseDTO> getPnrDetails(Long pnr) {

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", authToken);
            ResponseEntity<String> responseEntity = this.restTemplate.exchange(
                    EcateUrl + "api/v1/pnr/vendor?pnr=" + pnr,
                    HttpMethod.GET,
                    new HttpEntity<>(httpHeaders),
                    String.class
            );
            logger.info("response body get from IRCTC {}", responseEntity.getBody());

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();
                try {
                    JsonNode jsonNode = objectMapper.readTree(responseBody);
                    JsonNode resultObject = jsonNode.get("result");

                    List<StationResponse> stationResponse = StationInfo(resultObject.get("stations"));
                    TrainResponse trainResponse = TrainInfo(resultObject.get("trainInfo"));
                    SeatResponse seatResponse = SeatInfo(resultObject.get("seatInfo"));
                    int totalStations = stationResponse.size();
                    if (totalStations > 0) {
                        PnrResponse pnrResponse = new PnrResponse(totalStations, stationResponse,
                                trainResponse, seatResponse, pnr);

                        return new ResponseEntity<>(new ResponseDTO<>("success", null, pnrResponse),
                                HttpStatus.OK);
                    }

                    return new ResponseEntity<>(new ResponseDTO<>("failure", "Sorry, " +
                            "Restaurant are not available in your journey", null),
                            HttpStatus.BAD_REQUEST);
                } catch (JsonProcessingException e) {
                    return new ResponseEntity<>(new ResponseDTO<>("failure", "JSON processing error", null),
                            HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            return null;
        } catch (HttpClientErrorException e) {
            try {
                String msg = e.getResponseBodyAsString();
                JsonNode jsonNode = objectMapper.readTree(msg);
                JsonNode resultObject = jsonNode.get("message");
                String errorMsg = resultObject.asText();
                logger.info("error msg {}", resultObject);
                return new ResponseEntity<>(new ResponseDTO<>("failure", errorMsg, null),
                        HttpStatus.BAD_REQUEST);
            } catch (JsonProcessingException z) {
                logger.info(z.getMessage());
            }
            return new ResponseEntity<>(new ResponseDTO<>("failure", "Something Went Wrong, Please try again late", null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    public List<StationResponse> StationInfo(JsonNode stationsList) {

        List<StationResponse> stationRes = new ArrayList<>();
        for (JsonNode stationResponse : stationsList) {
            StationResponse stationResponse1 = new StationResponse();
            String code = stationResponse.get("code").asText();
            String name = stationResponse.get("name").asText();
            String delayArrival = stationResponse.get("delayArrival").asText();
            String halt = stationResponse.get("halt").asText();
            String arrival = stationResponse.get("arrival").asText();
            String departure = stationResponse.get("departure").asText();
            String depDate = stationResponse.get("depDate").asText();
            stationResponse1.setCode(code);
            stationResponse1.setName(name);
            stationResponse1.setDelayArrival(delayArrival);
            stationResponse1.setHalt(halt);
            stationResponse1.setArrival(arrival);
            stationResponse1.setDeparture(departure);
            stationResponse1.setDepDate(depDate);


            DateTimeFormatter dateForMatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate currentDate = LocalDate.parse(LocalDate.now().format(dateForMatter),dateForMatter);
            LocalDate departureDate = LocalDate.parse(depDate, dateForMatter);

            DateTimeFormatter timeForMatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime currentTime = LocalTime.parse(LocalTime.now().format(timeForMatter),timeForMatter);
            LocalTime trainArrTime = LocalTime.parse(Objects.equals(arrival, "--") ? departure : arrival ,timeForMatter);

            boolean isDepDateServiceable = departureDate.isAfter(currentDate) || departureDate.isEqual(currentDate);
            boolean isArriTimeServiceable = isDepDateServiceable && trainArrTime.isAfter(currentTime);
            stationResponse1.setServiceable(isArriTimeServiceable);
            stationRes.add(stationResponse1);
        }
        return stationRes;
    }

    public SeatResponse SeatInfo(JsonNode seatData) {
        String coach = seatData.get("coach").asText();
        String berth = seatData.get("berth").asText();
        SeatResponse seatResponse = new SeatResponse();
        seatResponse.setBerth(berth);
        seatResponse.setCoach(coach);
        return seatResponse;
    }

    public TrainResponse TrainInfo(JsonNode trainData) {
        String trainNo = trainData.get("trainNo").asText();
        String name = trainData.get("name").asText();
        String boarding = trainData.get("boarding").asText();
        String destination = trainData.get("destination").asText();
        String dt = trainData.get("dt").asText();
        TrainResponse trainResponse = new TrainResponse();
        trainResponse.setTrainNo(trainNo);
        trainResponse.setName(name);
        trainResponse.setBoarding(boarding);
        trainResponse.setDestination(destination);
        trainResponse.setDt(dt);

        return trainResponse;
    }

}

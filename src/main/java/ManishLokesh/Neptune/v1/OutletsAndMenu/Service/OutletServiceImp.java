package ManishLokesh.Neptune.v1.OutletsAndMenu.Service;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.OutletClosing;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestOfIRCTCPush.MenuPushRequestBody;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestOfIRCTCPush.MenuPushToIRCTC;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestOfIRCTCPush.OutletsPushToIRCTC;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestOfIRCTCPush.outletPushRequestBody;
import ManishLokesh.Neptune.v1.OutletsAndMenu.ReponseBody.CreateOutletResponse;
import ManishLokesh.Neptune.v1.OutletsAndMenu.ReponseBody.MenuResponse;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.MenuRepo;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.OutletClosingRepo;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.OutletRepo;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateMenu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.CreateOutlet;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody.OutletClosingRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service 
public class OutletServiceImp implements OutletService{

    private Logger logger = LoggerFactory.getLogger("app.OutletService");
    @Autowired
    public OutletRepo outletRepo;
    @Autowired
    public ObjectMapper objectMapper;
    @Autowired
    public MenuRepo menuRepo;
    @Autowired
    public OutletClosingRepo outletClosingRepo;
    private RestTemplate restTemplate;
    private final String EcateUrl;
    private final String AuthToken;

    @Autowired
    public OutletServiceImp(RestTemplate restTemplate, @Value("${E-catering.stage.url}") String ecateUrl,
                            @Value("${E-catering.auth.token}") String authToken){
        this.restTemplate = restTemplate;
        this.EcateUrl = ecateUrl;
        this.AuthToken = authToken;
    }

    @Override
    public ResponseEntity<ResponseDTO> CreateNewOutlet(CreateOutlet createOutlet) {

        logger.info("hlw working name is : {}", createOutlet.getOutletName());

        Outlet outlet = new Outlet();
        outlet.setCreatedAt(LocalDate.now().toString());
        outlet.setStationCode(createOutlet.getStationCode());
        outlet.setOutletName(createOutlet.getOutletName());
        outlet.setMinOrderValue(createOutlet.getMinOrderValue());
        outlet.setOrderTiming(createOutlet.getOrderTiming());
        outlet.setOpeningTime(createOutlet.getOpeningTime());
        outlet.setClosingTime(createOutlet.getClosingTime());
        outlet.setDeliveryCost(createOutlet.getDeliveryCost());
        outlet.setAddress(createOutlet.getAddress());
        outlet.setCity(createOutlet.getCity());
        outlet.setState(createOutlet.getState());
        outlet.setPrepaid(createOutlet.getPrepaid());
        outlet.setCompanyName(createOutlet.getCompanyName());
        outlet.setPanCard(createOutlet.getPanCard());
        outlet.setGstNo(createOutlet.getGstNo());
        outlet.setFssaiNo(createOutlet.getFssaiNo());
        outlet.setFssaiValidUpto(createOutlet.getFssaiValidUpto());
        outlet.setActive(false);
        outlet.setLogoImage(createOutlet.getLogoImage());
        outlet.setEmailId(createOutlet.getEmailId());
        outlet.setMobileNo(createOutlet.getMobileNo());
        outlet.setRatingValue(3.3);
        String aggOutletId = String.valueOf((int) (Math.random() * 90000) + 10000);
        outlet.setIrctcOutletId(aggOutletId);
        outlet.setCreatedAt(LocalDateTime.now().toString());
        Outlet o = outletRepo.saveAndFlush(outlet);
        List<OutletClosingRequest> ClosingRequestList = objectMapper.convertValue(createOutlet.getOutletClosing(), new TypeReference<>() {});
        List<OutletClosing> outletClosingList = new ArrayList<>();
        for(OutletClosingRequest closing : ClosingRequestList){
            OutletClosing outletClosing = new OutletClosing();
            outletClosing.setClosedFrom(closing.getClosingFrom());
            outletClosing.setClosedTo(closing.getClosingTo());
            outletClosing.setOutletId(String.valueOf(o.getId()));
            outletClosingList.add(outletClosing);
        }
       List <OutletClosing> storeClose = outletClosingRepo.saveAllAndFlush(outletClosingList);

        CreateOutletResponse createOutletResponse = new CreateOutletResponse(o.getId(),o.getOutletName(),
                o.getMinOrderValue(),o.getOrderTiming(),o.getOpeningTime(),o.getClosingTime(),
                o.getDeliveryCost(),o.getAddress(),o.getCity(),o.getState(),o.getPrepaid(),o.getCompanyName(),
                o.getPanCard(),o.getGstNo(),o.getFssaiNo(),o.getFssaiValidUpto(),
                o.getActive(),o.getCreatedAt(),storeClose,o.getUpdatedAt(),o.getLogoImage(),o.getEmailId(),
                o.getMobileNo(),o.getStationCode());

        return new ResponseEntity<>(new ResponseDTO("success",null,createOutletResponse),
                HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<ResponseDTO> UpdateOutlet(CreateOutlet updateOutlet,Long outletId) {
        Optional <Outlet> outletDetails = outletRepo.findById(outletId);
        if(outletDetails.isPresent()){
            Outlet outlet = outletDetails.get();
            outlet.setStationCode(updateOutlet.getStationCode());
            outlet.setUpdatedAt(LocalDateTime.now().toString());
            outlet.setOutletName(updateOutlet.getOutletName());
            outlet.setMinOrderValue(updateOutlet.getMinOrderValue());
            outlet.setOrderTiming(updateOutlet.getOrderTiming());
            outlet.setOpeningTime(updateOutlet.getOpeningTime());
            outlet.setClosingTime(updateOutlet.getClosingTime());
            outlet.setDeliveryCost(updateOutlet.getDeliveryCost());
            outlet.setAddress(updateOutlet.getAddress());
            outlet.setState(updateOutlet.getState());
            outlet.setCity(updateOutlet.getCity());
            outlet.setPrepaid(updateOutlet.getPrepaid());
            outlet.setCompanyName(updateOutlet.getCompanyName());
            outlet.setPanCard(updateOutlet.getPanCard());
            outlet.setGstNo(updateOutlet.getGstNo());
            outlet.setFssaiNo(updateOutlet.getFssaiNo());
            outlet.setFssaiValidUpto(updateOutlet.getFssaiValidUpto());
            outlet.setLogoImage(updateOutlet.getLogoImage());
            outlet.setEmailId(updateOutlet.getEmailId());
            outlet.setMobileNo(updateOutlet.getMobileNo());
            Outlet o = outletRepo.save(outlet);

            outletClosingRepo.deleteByOutletId(String.valueOf(outletId));
            List<OutletClosingRequest> closingRequestList = objectMapper.convertValue(updateOutlet.getOutletClosing(), new TypeReference<>() {});
            List<OutletClosing> closingList = new ArrayList<>();
                for(OutletClosingRequest closingRequest : closingRequestList){
                    OutletClosing closing = new OutletClosing();
                    closing.setClosedFrom(closingRequest.getClosingFrom());
                    closing.setClosedTo(closingRequest.getClosingTo());
                    closing.setOutletId( String.valueOf(o.getId()));
                    closingList.add(closing);
                }
            List<OutletClosing> savedClosing =  outletClosingRepo.saveAllAndFlush(closingList);

            CreateOutletResponse updateOutletResponse = new CreateOutletResponse(o.getId(),o.getOutletName(),
                    o.getMinOrderValue(),o.getOrderTiming(),o.getOpeningTime(),o.getClosingTime(),
                    o.getDeliveryCost(),o.getAddress(),o.getCity(),o.getState(),o.getPrepaid(),o.getCompanyName(),
                    o.getPanCard(),o.getGstNo(),o.getFssaiNo(),o.getFssaiValidUpto(),
                    o.getActive(),o.getCreatedAt(),savedClosing,o.getUpdatedAt(),o.getLogoImage(),o.getEmailId(),
                    o.getMobileNo(),o.getStationCode());

            List<Outlet> allTheOutlet = outletRepo.findByStationCode(o.getStationCode());
            List<Outlet> activeOutletList = allTheOutlet.stream().filter(Outlet :: getActive).collect(Collectors.toList());

            List<outletPushRequestBody> pushOutlet = new ArrayList<>();
            for(Outlet outlet1 : activeOutletList){
                outletPushRequestBody outlet2 = new outletPushRequestBody();
                outlet2.setOutletId(outlet1.getIrctcOutletId());
                outlet2.setOutletName(outlet1.getOutletName());
                outlet2.setOrder_timing(outlet1.getOrderTiming());
                outlet2.setMinOrderAmount(outlet1.getMinOrderValue());
                outlet2.setOpeningTime(outlet1.getOpeningTime());
                outlet2.setClosingTime(outlet1.getClosingTime());
                outlet2.setDeliveryCost(outlet1.getDeliveryCost());
                outlet2.setPrepaid(outlet1.getPrepaid());
                outlet2.setAddress(outlet1.getAddress());
                outlet2.setCity(outlet1.getCity());
                outlet2.setState(outlet1.getState());
                outlet2.setCompanyName(outlet1.getCompanyName());
                outlet2.setVendorPanNumber(outlet1.getPanCard());
                outlet2.setGstNo(outlet1.getGstNo());
                outlet2.setFssaiNo(outlet1.getFssaiNo());
                outlet2.setFssaiValidUpto(outlet1.getFssaiValidUpto());
                outlet2.setLogoImage(outlet1.getLogoImage());
                outlet2.setMobileNumber(outlet1.getMobileNo());
                outlet2.setEmailId(outlet1.getEmailId());
                pushOutlet.add(outlet2);
            }
            OutletsPushToIRCTC outletsPushToIRCTC = new OutletsPushToIRCTC(pushOutlet);
            logger.info("testing : {}",pushOutlet);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization",AuthToken);
            String stationCode = o.getStationCode();
            Object response = this.restTemplate.exchange(
                    EcateUrl+"api/v1/vendor/aggregator/outlets/"+stationCode,
                    HttpMethod.POST,
                    new HttpEntity<>(outletsPushToIRCTC,httpHeaders),
                    Object.class
            ).getBody();
            logger.info("testing : {}",response);

            return new ResponseEntity<>(new ResponseDTO<>("success",null,updateOutletResponse),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","outlet is not found",null),
                HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<ResponseDTO> CreateNewMenu(Long outletId,CreateMenu createMenu) {
        Optional<Outlet> outletDetails = outletRepo.findById(outletId);
        if(outletRepo.findById(outletId).isPresent()){
            if(((Float.parseFloat(createMenu.getBasePrice())) * 0.05) == Float.parseFloat(createMenu.getTax())){
                if(((Float.parseFloat(createMenu.getBasePrice()) + Float.parseFloat(createMenu.getTax()))
                        == Float.parseFloat(createMenu.getSellingPrice()))){
                    Menu menu = new Menu();
                    String aggMenuId = String.valueOf((int) (Math.random() * 90000) + 10000);
                    menu.setIrctcMenuId(aggMenuId);
                    menu.setCreatedAt(LocalDateTime.now().toString());
                    menu.setActive(false);
                    menu.setOutletId(outletId.toString());
                    menu.setName(createMenu.getName());
                    menu.setBasePrice(createMenu.getBasePrice());
                    menu.setTax(createMenu.getTax());
                    menu.setSellingPrice(createMenu.getSellingPrice());
                    menu.setImage(createMenu.getImage());
                    menu.setDescription(createMenu.getDescription());
                    menu.setFoodType(createMenu.getFoodType());
                    menu.setCuisine(createMenu.getCuisine());
                    menu.setTags(createMenu.getTags());
                    menu.setBulkOnly(createMenu.getBulkOnly());
                    menu.setIsVegeterian(createMenu.getIsVegeterian());
                    menu.setCustomisations(createMenu.getCustomisations());
                    Outlet outlet = outletDetails.get();
                    menu.setOpeningTime(outlet.getOpeningTime());
                    menu.setClosingTime(outlet.getClosingTime());
                    Menu m = menuRepo.saveAndFlush(menu);

                    MenuResponse menuResponse = new MenuResponse(m.getId(), m.getName(), m.getDescription(),
                            m.getBasePrice(), m.getTax(), m.getSellingPrice(), m.getFoodType(), m.getCuisine(), m.getTags(),
                            m.getBulkOnly(), m.getIsVegeterian(), m.getImage(), m.getCustomisations(), m.getOpeningTime(),
                            m.getClosingTime(), m.getCreatedAt(), m.getUpdatedAt(), m.getActive());

                    return new ResponseEntity<>(new ResponseDTO<>("Success", null, menuResponse), HttpStatus.CREATED);
                }else{
                    return new ResponseEntity<>(new ResponseDTO<>("failure", "Incorrect selling price", null), HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>(new ResponseDTO<>("failure", "Incorrect tax value", null), HttpStatus.BAD_REQUEST);
            }

        }else {
            return new ResponseEntity<>(new ResponseDTO<>("failure", "Outlet is not Present", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO> UpdateMenu(CreateMenu updateMenu, Long outletId, Long menuId) {
        Optional<Outlet> outletDetails = outletRepo.findById(outletId);
        if(outletDetails.isPresent()){
            Optional<Menu> menuDetails = menuRepo.findById(menuId);
            if(menuDetails.isPresent()){
                if((Float.parseFloat(updateMenu.getBasePrice()) * 0.05) == Float.parseFloat(updateMenu.getTax())){
                    if((Float.parseFloat(updateMenu.getBasePrice()) + Float.parseFloat(updateMenu.getTax())) == Float.parseFloat(updateMenu.getSellingPrice())){
                        Menu menu = menuDetails.get();
                        menu.setUpdatedAt(LocalDateTime.now().toString());
                        menu.setName(updateMenu.getName());
                        menu.setImage(updateMenu.getImage());
                        menu.setDescription(updateMenu.getDescription());
                        menu.setBasePrice(updateMenu.getBasePrice());
                        menu.setTax(updateMenu.getTax());
                        menu.setSellingPrice(updateMenu.getSellingPrice());
                        menu.setFoodType(updateMenu.getFoodType());
                        menu.setCuisine(updateMenu.getCuisine());
                        menu.setTags(updateMenu.getTags());
                        menu.setBulkOnly(updateMenu.getBulkOnly());
                        menu.setIsVegeterian(updateMenu.getIsVegeterian());
                        menu.setCustomisations(updateMenu.getCustomisations());
                        Outlet outlet = outletDetails.get();
                        menu.setOpeningTime(outlet.getOpeningTime());
                        menu.setClosingTime(outlet.getClosingTime());
                        Menu m = menuRepo.save(menu);

                        Long outletId2 = Long.parseLong(m.getOutletId());
                        Optional<Outlet> outlet1 = outletRepo.findById(outletId2);
                        Outlet o = outlet1.get();
                        String stationCode = o.getStationCode();
                        String irctcOutletId = o.getIrctcOutletId();

                        List<Menu> menuList = menuRepo.findByOutletId(m.getOutletId());
                        List<Menu> activeMenuList = menuList.stream().filter(Menu :: getActive).collect(Collectors.toList());
                        List<MenuPushRequestBody> menuPushList = new ArrayList<>();
                        for(Menu menu1 : activeMenuList){
                            MenuPushRequestBody pushData = new MenuPushRequestBody();
                            pushData.setItemId(menu1.getIrctcMenuId());
                            pushData.setItemName(menu1.getName());
                            pushData.setBasePrice(menu1.getBasePrice());
                            pushData.setDescription(menu1.getDescription());
                            pushData.setOpeningTime(menu1.getOpeningTime());
                            pushData.setClosingTime(menu1.getClosingTime());
                            pushData.setTax(menu1.getTax());
                            pushData.setSellingPrice(menu1.getSellingPrice());
                            pushData.setIsVegeterian(menu1.getIsVegeterian());
                            pushData.setTags(null);
                            pushData.setImage(menu1.getImage());
                            pushData.setCuisine(menu1.getCuisine());
                            pushData.setFoodType(menu1.getFoodType());
                            pushData.setBulkOnly(menu1.getBulkOnly());
                            pushData.setCustomisations(null);
                            menuPushList.add(pushData);
                        }
                        MenuPushToIRCTC menuPushToIRCTC = new MenuPushToIRCTC(menuPushList);

                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        httpHeaders.add("Authorization",AuthToken);
                        Object response = this.restTemplate.exchange(
                                EcateUrl+"api/v1/vendor/aggregator/station/"+stationCode+"/"+"outlet" +"/"+irctcOutletId,
                                HttpMethod.POST,
                                new HttpEntity<>(menuPushToIRCTC,httpHeaders),
                                Object.class
                        ).getBody();

                        logger.info("push data : {}",response);
                        return new ResponseEntity<>(new ResponseDTO("success",null,menu),HttpStatus.OK);
                    }else{
                        return new ResponseEntity<>(new ResponseDTO("failure","Incorrect Selling price",null), HttpStatus.BAD_REQUEST);
                    }
                }else{
                    return new ResponseEntity<>(new ResponseDTO("failure","Incorrect Tax value",null), HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>(new ResponseDTO("failure","Menu is not found",null), HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(new ResponseDTO("failure","Outlet is not found",null), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<ResponseDTO> ActiveOutlet(Long outletId,Boolean status) {
        Optional<Outlet> outletDetails = outletRepo.findById(outletId);
        if(outletDetails.isPresent()){
            Outlet outlet = outletDetails.get();
            outlet.setActive(status);
            outlet.setUpdatedAt(LocalDateTime.now().toString());
            Outlet o = outletRepo.save(outlet);

            List<Outlet> allTheOutlet = outletRepo.findByStationCode(o.getStationCode());
            List<Outlet> activeOutletList = allTheOutlet.stream().filter(Outlet :: getActive).collect(Collectors.toList());

            List<outletPushRequestBody> pushOutlet = new ArrayList<>();
            for(Outlet outlet1 : activeOutletList){
                outletPushRequestBody outlet2 = new outletPushRequestBody();
                outlet2.setOutletId(outlet1.getIrctcOutletId());
                outlet2.setOutletName(outlet1.getOutletName());
                outlet2.setOrder_timing(outlet1.getOrderTiming());
                outlet2.setMinOrderAmount(outlet1.getMinOrderValue());
                outlet2.setOpeningTime(outlet1.getOpeningTime());
                outlet2.setClosingTime(outlet1.getClosingTime());
                outlet2.setDeliveryCost(outlet1.getDeliveryCost());
                outlet2.setPrepaid(outlet1.getPrepaid());
                outlet2.setAddress(outlet1.getAddress());
                outlet2.setCity(outlet1.getCity());
                outlet2.setState(outlet1.getState());
                outlet2.setCompanyName(outlet1.getCompanyName());
                outlet2.setVendorPanNumber(outlet1.getPanCard());
                outlet2.setGstNo(outlet1.getGstNo());
                outlet2.setFssaiNo(outlet1.getFssaiNo());
                outlet2.setFssaiValidUpto(outlet1.getFssaiValidUpto());
                outlet2.setLogoImage(outlet1.getLogoImage());
                outlet2.setMobileNumber(outlet1.getMobileNo());
                outlet2.setEmailId(outlet1.getEmailId());
                pushOutlet.add(outlet2);
            }
            OutletsPushToIRCTC outletsPushToIRCTC = new OutletsPushToIRCTC(pushOutlet);
            logger.info("testing : {}",pushOutlet);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization",AuthToken);
            String stationCode = o.getStationCode();
            Object response = this.restTemplate.exchange(
                    EcateUrl+"api/v1/vendor/aggregator/outlets/"+stationCode,
                    HttpMethod.POST,
                    new HttpEntity<>(outletsPushToIRCTC,httpHeaders),
                    Object.class
            ).getBody();
            logger.info("testing : {}",response);

            return new ResponseEntity<>(new ResponseDTO<>("success",null,"Outlet status is Updated Successfully"),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Outlet is not found",null),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDTO> ActiveMenu(Long menuId,Boolean status) {
        Optional<Menu> menuDetails = menuRepo.findById(menuId);
        if(menuDetails.isPresent()){
            Menu menu = menuDetails.get();
            menu.setActive(status);
            menu.setUpdatedAt(LocalDateTime.now().toString());
            Menu m = menuRepo.save(menu);

            Long outletId = Long.parseLong(m.getOutletId());
            Optional<Outlet> outlet = outletRepo.findById(outletId);
            Outlet o = outlet.get();
            String stationCode = o.getStationCode();
            String irctcOutletId = o.getIrctcOutletId();

            List<Menu> menuList = menuRepo.findByOutletId(m.getOutletId());
            List<Menu> activeMenuList = menuList.stream().filter(Menu :: getActive).collect(Collectors.toList());
            List<MenuPushRequestBody> menuPushList = new ArrayList<>();
            for(Menu menu1 : activeMenuList){
                MenuPushRequestBody pushData = new MenuPushRequestBody();
                pushData.setItemId(menu1.getIrctcMenuId());
                pushData.setItemName(menu1.getName());
                pushData.setBasePrice(menu1.getBasePrice());
                pushData.setDescription(menu1.getDescription());
                pushData.setOpeningTime(menu1.getOpeningTime());
                pushData.setClosingTime(menu1.getClosingTime());
                pushData.setTax(menu1.getTax());
                pushData.setSellingPrice(menu1.getSellingPrice());
                pushData.setIsVegeterian(menu1.getIsVegeterian());
                pushData.setTags(null);
                pushData.setImage(menu1.getImage());
                pushData.setCuisine(menu1.getCuisine());
                pushData.setFoodType(menu1.getFoodType());
                pushData.setBulkOnly(menu1.getBulkOnly());
                pushData.setCustomisations(null);
                menuPushList.add(pushData);
            }
            MenuPushToIRCTC menuPushToIRCTC = new MenuPushToIRCTC(menuPushList);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("Authorization",AuthToken);
            Object response = this.restTemplate.exchange(
                    EcateUrl+"api/v1/vendor/aggregator/station/"+stationCode+"/"+"outlet" +"/"+irctcOutletId,
                    HttpMethod.POST,
                    new HttpEntity<>(menuPushToIRCTC,httpHeaders),
                    Object.class
            ).getBody();

            logger.info("push data : {}",response);
            return new ResponseEntity<>(new ResponseDTO<>("success",null,"Menu status is updated successfully"),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","menu is not found",null),
                HttpStatus.BAD_REQUEST);
    }
}

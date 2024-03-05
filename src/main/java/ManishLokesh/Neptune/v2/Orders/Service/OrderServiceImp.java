package ManishLokesh.Neptune.v2.Orders.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.MenuRepo;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.OutletRepo;
import ManishLokesh.Neptune.v2.Orders.Entity.OrderItems;
import ManishLokesh.Neptune.v2.Orders.Entity.Orders;
import ManishLokesh.Neptune.v2.Orders.Repository.OrderItemsRepository;
import ManishLokesh.Neptune.v2.Orders.Repository.OrderRepository;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderItemRequest;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderPushToIRCTC.CustomerInfo;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderPushToIRCTC.OrderItemsInfo;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderPushToIRCTC.OrderPushToIRCTC;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderPushToIRCTC.OutletInfo;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderStatusBody;
import ManishLokesh.Neptune.v2.Orders.ResponseBody.OrderResponseBody;
import ManishLokesh.Neptune.v2.customer.Entity.Customer;
import ManishLokesh.Neptune.v2.customer.Repository.CustLoginRepo;
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


import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImp implements OrderService {


    @Autowired
    public MenuRepo menuRepo;
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public OrderItemsRepository orderItemsRepository;

    @Autowired
    public OutletRepo outletRepo;

    public Logger logger = LoggerFactory.getLogger("app.v2.order.service");

//    private OrderPushService orderPushService;

    @Autowired
    public CustLoginRepo custLoginRepo;
    private RestTemplate restTemplate;
    private final String EcateUrl;
    private final String AuthToken;

    private final ObjectMapper objectMapper;

    @Autowired
    public OrderServiceImp(RestTemplate restTemplate, @Value("${E-catering.stage.url}") String ecateUrl,
                           @Value("${E-catering.auth.token}") String authToken, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.EcateUrl = ecateUrl;
        this.AuthToken = authToken;
        this.objectMapper = objectMapper;

    }

    @Override
    public ResponseEntity<ResponseDTO> addOrder(OrderRequestBody orderRequestBody) {

        Object outletValid = outletRepo.findById(Long.parseLong(orderRequestBody.getOutletId()));
        logger.info("requst body {} ", orderRequestBody.toString());

        if (((Optional<?>) outletValid).isEmpty()) {
            return new ResponseEntity<>(
                    new ResponseDTO("failure", "outlet is not present", null),
                    HttpStatus.BAD_REQUEST);
        }
        Object customerData = custLoginRepo.findById(Long.parseLong(orderRequestBody.getCustomerId()));
        if (((Optional<?>) customerData).isEmpty()) {
            return new ResponseEntity<>(
                    new ResponseDTO("failure", "customer id is not present", null),
                    HttpStatus.BAD_REQUEST);
        }
        List<OrderItemRequest> itemsList = orderRequestBody.getOrderItem();
        if (itemsList.isEmpty()) {
            return new ResponseEntity<>(
                    new ResponseDTO("failure", "Item list can not be empty", null),
                    HttpStatus.BAD_REQUEST);
        }

        List<Long> itemIds = itemsList.stream().map(OrderItemRequest::getItemId).filter(Objects::nonNull).collect(Collectors.toList());
        List<Menu> menuList = itemIds.stream().map(itemId -> menuRepo.findById(itemId)).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        List<OrderItems> orderItemsList = new ArrayList<>();
        Double subtotalPrice = 0.0;
        for (Menu menu : menuList) {
            OrderItems orderItems = new OrderItems();
            Long itemId = menu.getId();
            Optional<OrderItemRequest> quantity = itemsList.stream().filter(findId -> findId.getItemId().equals(itemId)).findFirst();
            Integer quantityValue = quantity.get().getQuantity();
            orderItems.setQuantity(quantityValue);
            Double basePrice = menu.getBasePrice();
            subtotalPrice = subtotalPrice + (basePrice * quantityValue);
            orderItems.setBasePrice(basePrice);
            orderItems.setItemId(menu.getId());
            orderItems.setItemName(menu.getName());
            orderItems.setDescription(menu.getDescription());
            orderItems.setCreatedAt(LocalDateTime.now().toString());
            orderItems.setTax(menu.getBasePrice() * 0.05);
            orderItems.setVeg(menu.getIsVegeterian());
            orderItems.setSellingPrice(menu.getSellingPrice());
            orderItemsList.add(orderItems);

        }
        logger.info("order items data is, {}", orderItemsList.stream().collect(Collectors.toList()));
        for (OrderItems item : orderItemsList) {
            logger.info("item is added {}", item.toString());
        }
        Orders orders = new Orders();
        orders.setBerth(orderRequestBody.getBerth());
        orders.setCoach(orderRequestBody.getCoach());
        orders.setTrainName(orderRequestBody.getTrainName());
        orders.setTrainNo(orderRequestBody.getTrainNo());
        logger.info("delivery date from request body {}", orderRequestBody.getDeliveryDate());
        orders.setDeliveryDate(orderRequestBody.getDeliveryDate());
        orders.setOrderFrom(orderRequestBody.getOrderFrom());
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatedBookingData = localDateTime.format(formatter);
        orders.setBookingDate(formatedBookingData);
        String formatedCreatedDate = localDateTime.format(formatter);
        orders.setCreatedAt(formatedCreatedDate);
        orders.setStationName(orderRequestBody.getStationName());
        orders.setStationCode(orderRequestBody.getStationCode());
        orders.setOutletId(orderRequestBody.getOutletId());
        orders.setCustomerId(orderRequestBody.getCustomerId());
        orders.setPnr(orderRequestBody.getPnr());
        orders.setCreatedBy("CUSTOMER");
        orders.setStatus("PLACED");
        orders.setPaymentType(orderRequestBody.getPaymentType());
        orders.setDeliveryCharge(orderRequestBody.getDeliveryCharge());
        orders.setTotalAmount(subtotalPrice);
        DecimalFormat df = new DecimalFormat("#.##");
        Double gst = subtotalPrice * 0.05;
        orders.setGst(Double.valueOf(df.format(gst)));
        logger.info("run here");
        logger.info("subtotal is {}", subtotalPrice);
        logger.info("tax is  {}", (subtotalPrice * 0.05));
        double deliveryCharges = 0;
        if (orderRequestBody.getDeliveryCharge().isNaN()) {
            deliveryCharges = orderRequestBody.getDeliveryCharge();
        }
        logger.info("delivery charge is {}", deliveryCharges);
        logger.info("total amount is  {}", (subtotalPrice + (subtotalPrice * 0.05)) + deliveryCharges);
        orders.setPayable_amount(Math.round(subtotalPrice + (subtotalPrice * 0.05)) + deliveryCharges);

        Orders saveOrder = orderRepository.saveAndFlush(orders);


        logger.info("order is saved {}", saveOrder.toString());
        for (OrderItems orderItems : orderItemsList) {
            orderItems.setOrderId(String.valueOf(saveOrder.getId()));
        }
        List<OrderItems> orderItemsData = orderItemsRepository.saveAllAndFlush(orderItemsList);
        Optional<Outlet> outlet = outletRepo.findById((Long.parseLong(saveOrder.getOutletId())));
        Object customer = custLoginRepo.findById(Long.parseLong(saveOrder.getCustomerId()));
        OrderResponseBody orderResponseBody = new OrderResponseBody(saveOrder.getId(), saveOrder.getTotalAmount(), saveOrder.getGst(), saveOrder.getDeliveryCharge(),
                saveOrder.getPayable_amount(), saveOrder.getDeliveryDate(), saveOrder.getBookingDate(), saveOrder.getPaymentType(), saveOrder.getStatus(),
                saveOrder.getOutletId(), orderItemsData, saveOrder.getTrainName(), saveOrder.getTrainNo(), saveOrder.getStationCode(), saveOrder.getStationName(),
                saveOrder.getCoach(), saveOrder.getBerth(), saveOrder.getOrderFrom(), saveOrder.getPnr(), saveOrder.getCreatedAt(), saveOrder.getCreatedBy(), outlet, customer);

        logger.info("order is push to irctc");
        logger.info("request body {}", saveOrder.toString());

        Long customerId = Long.valueOf(saveOrder.getCustomerId());
        Optional<Customer> customerDa = custLoginRepo.findById(customerId);
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setFullName(customerDa.get().getFullName());
        customerInfo.setEmail(customerDa.get().getEmailId());
        customerInfo.setMobile(customerDa.get().getMobileNumber());

        logger.info("customer id" + customerId);
        logger.info("customer details  {}", customerInfo.toString());

        Long outletId = Long.valueOf(saveOrder.getOutletId());
        Optional<Outlet> outletData = outletRepo.findById(outletId);
        OutletInfo outletInfo = new OutletInfo();
        outletInfo.setId(outletData.get().getId());
        outletInfo.setName(outletData.get().getOutletName());
        outletInfo.setAddress(outletData.get().getAddress());
        outletInfo.setCity(outletData.get().getCity());
        outletInfo.setState(outletData.get().getState());
        outletInfo.setPinCode("110001");
        outletInfo.setContactNumbers(outletData.get().getMobileNo());
        outletInfo.setRelationshipManagerName(outletData.get().getCompanyName());
        outletInfo.setRelationshipManagerEmail(outletData.get().getEmailId());
        outletInfo.setRelationshipManagerPhone(outletData.get().getMobileNo());
        outletInfo.setFssaiNumber(outletData.get().getFssaiNo());
        logger.info("fssai number {}", outletData.get().getFssaiNo());
        outletInfo.setFssaiCutOffDate(outletData.get().getFssaiValidUpto() + " 00:00 IST");
        logger.info("fssai upto date {}", outletData.get().getFssaiValidUpto() + " 00:00 IST");
        outletInfo.setGstNumber(outletData.get().getGstNo());
        logger.info("gst number {}", outletData.get().getGstNo());

        logger.info("outlet id" + outletId);
        logger.info("outlet details " + outletInfo);

        String orderId = String.valueOf(saveOrder.getId());
        logger.info("order id is {}", orderId);
        List<OrderItemsInfo> orderItemInfoList = new ArrayList<>();
//        List<OrderItems> orderItemsData = orderItemsRepository.findByOrderId(orderId);

        logger.info("order items data {}", orderItemsData);
        for (OrderItems orderItems1 : orderItemsData) {
            logger.info("runing........");
            logger.info("item id {}", orderItems1.getItemId());
            OrderItemsInfo orderItemsInfo = new OrderItemsInfo();
            orderItemsInfo.setItemId(orderItems1.getItemId());
            orderItemsInfo.setItemName(orderItems1.getItemName());
            orderItemsInfo.setDescription(orderItems1.getDescription());
            orderItemsInfo.setBasePrice(orderItems1.getBasePrice());
            orderItemsInfo.setTaxRate(orderItems1.getTax());
            orderItemsInfo.setSellingPrice(orderItems1.getSellingPrice());
            orderItemsInfo.setVegetarian(orderItems1.getVeg());
            orderItemsInfo.setQuantity(orderItems1.getQuantity());
            orderItemsInfo.setOption("");
            orderItemInfoList.add(orderItemsInfo);
            logger.info("order items is added");
        }

        logger.info("order id " + orderId);
        logger.info("order items info [{}]", orderItemInfoList);
        logger.info("delivery date, {}", saveOrder.getDeliveryDate() + " IST");
        logger.info("booking date {}", saveOrder.getBookingDate() + " IST");

        String paymentType = "PRE_PAID";
        if (Objects.equals(saveOrder.getPaymentType(), "CASH")) {
            paymentType = "CASH_ON_DELIVERY";
        }

        logger.info("payment type is {}", paymentType);


        OrderPushToIRCTC orderPush = new OrderPushToIRCTC(saveOrder.getId(), "", "", customerInfo,
                outletInfo, saveOrder.getBookingDate() + " IST", saveOrder.getDeliveryDate() + " IST",
                saveOrder.getPnr(), saveOrder.getTrainName(), saveOrder.getTrainNo(),
                saveOrder.getStationCode(), saveOrder.getStationName(), saveOrder.getCoach(),
                saveOrder.getBerth(), saveOrder.getTotalAmount(), saveOrder.getDeliveryCharge(),
                saveOrder.getGst(), 0.0, saveOrder.getPayable_amount(), paymentType, orderItemInfoList);


        HttpHeaders httpHeaders = new HttpHeaders();
        logger.info("client error");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        logger.info("type error ");
        httpHeaders.add("Authorization", AuthToken);
        logger.info("run here .... ");
        logger.info("auth token {} ", AuthToken);
        logger.info("http header {}", httpHeaders);
        logger.info("ecat url {}", EcateUrl);
        try {
            ResponseEntity<String> response = this.restTemplate.exchange(
                    EcateUrl + "api/v1/order/vendor",
                    HttpMethod.POST,
                    new HttpEntity<>(orderPush, httpHeaders),
                    String.class
            );
            String responseBody = response.getBody();
            try {
                if (response.getStatusCode().is2xxSuccessful()) {
                    JsonNode jsonNode = objectMapper.readTree(responseBody);
                    JsonNode resultObject = jsonNode.get("result");
                    JsonNode responseOrderId = resultObject.get("id");
                    JsonNode orderStatus = resultObject.get("status");
                    Optional<Orders> orders1 = orderRepository.findById(saveOrder.getId());
                    Long irctcOrderId = Long.parseLong(String.valueOf(responseOrderId));
                    logger.info("irctc order id is {}", irctcOrderId);
                    Orders orders2 = orders1.get();
                    orders2.setIrctcOrderId(irctcOrderId);
                    String irctcOrderStatus = String.valueOf(orderStatus);
                    String result = irctcOrderStatus.substring(1, irctcOrderStatus.length() - 1);
                    if (result.equals("ORDER_CONFIRMED")) {
                        orders2.setStatus("CONFIRMED");
                    } else {
                        orders2.setStatus(result);
                    }
                    logger.info("irctc order status {}", result);
                    Orders orders3 = orderRepository.save(orders2);
                }
            } catch (JsonProcessingException e) {
                return new ResponseEntity<>(new ResponseDTO<>("failure", "JSON processing error", null),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            logger.info("response body {}", response.getBody());
        } catch (HttpClientErrorException e) {
            logger.info("getting expections");
            logger.info("error : {}", e.getResponseBodyAsString());
//            return new ResponseEntity<>(
//                    new ResponseDTO("failure", "something went wrong, please try again later", null),
//                    HttpStatus.BAD_REQUEST);
        }

        logger.info("order push is completed");

        return new ResponseEntity<>(
                new ResponseDTO("success", null, orderResponseBody),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getOrder(Long orderId, Long customerId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
        Long customerDetail = Long.parseLong(orders.get().getCustomerId());
        if (!customerDetail.equals(customerId)) {
            return new ResponseEntity<>(new ResponseDTO<>("failure", "Not Authorize to Access", null),
                    HttpStatus.UNAUTHORIZED);
        }
        if (orders.isEmpty()) {
            return new ResponseEntity<>(new ResponseDTO<>("failure", "Incorrect order Id", null),
                    HttpStatus.BAD_REQUEST);
        }
        Orders saveOrder = orders.get();
        List<OrderItems> orderItems1 = orderItemsRepository.findByOrderId(String.valueOf(orderId));
        Optional<Outlet> outlet = outletRepo.findById((Long.parseLong(saveOrder.getOutletId())));
        Object customer = custLoginRepo.findById(Long.parseLong(saveOrder.getCustomerId()));

        OrderResponseBody orderResponseBody = new OrderResponseBody(saveOrder.getId(), saveOrder.getTotalAmount(), saveOrder.getGst(), saveOrder.getDeliveryCharge(),
                saveOrder.getPayable_amount(), saveOrder.getDeliveryDate(), saveOrder.getBookingDate(), saveOrder.getPaymentType(), saveOrder.getStatus(),
                saveOrder.getOutletId(), orderItems1, saveOrder.getTrainName(), saveOrder.getTrainNo(), saveOrder.getStationCode(), saveOrder.getStationName(),
                saveOrder.getCoach(), saveOrder.getBerth(), saveOrder.getOrderFrom(), saveOrder.getPnr(), saveOrder.getCreatedAt(), saveOrder.getCreatedBy(), outlet, customer);

        return new ResponseEntity<>(new ResponseDTO<>("success", null, orderResponseBody),
                HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> getAllOrder(Long customerId) {
//        List<Orders> ordersList = orderRepository.findAll();
        List<Orders> ordersList = orderRepository.findByCustomerId(String.valueOf(customerId));
        List<OrderResponseBody> orderResponseBodies = new ArrayList<>();
        for (Orders orders : ordersList) {
            OrderResponseBody orderBody = new OrderResponseBody();
            orderBody.setId(orders.getId());
            orderBody.setTrainName(orders.getTrainName());
            orderBody.setTrainNo(orders.getTrainNo());
            orderBody.setStationCode(orders.getStationCode());
            orderBody.setStationName(orders.getStationName());
            orderBody.setCoach(orders.getCoach());
            orderBody.setBerth(orders.getBerth());
            orderBody.setBookingDate(orders.getBookingDate());
            orderBody.setOutletId(orders.getOutletId());
            orderBody.setCreatedAt(orders.getCreatedAt());
            orderBody.setStatus(orders.getStatus());
            orderBody.setCreatedBy(orders.getCreatedBy());
            orderBody.setPnr(orders.getPnr());
            orderBody.setPaymentType(orders.getPaymentType());
            orderBody.setDeliveryCharge(orders.getDeliveryCharge());
            orderBody.setOrderFrom(orders.getOrderFrom());
            orderBody.setTotalAmount(orders.getTotalAmount());
            orderBody.setGst(orders.getGst());
            orderBody.setPayable_amount(orders.getPayable_amount());
            orderBody.setDeliveryDate(orders.getDeliveryDate());
            List<OrderItems> orderItemsList = orderItemsRepository.findByOrderId(String.valueOf(orders.getId()));
            Optional<Outlet> outlet = outletRepo.findById((Long.parseLong(orders.getOutletId())));
            Optional<Customer> customerDetails = custLoginRepo.findById(customerId);
            orderBody.setCustomerDetail(customerDetails);
            orderBody.setOutlets(outlet);
            orderBody.setOrderItems(orderItemsList);
            orderResponseBodies.add(orderBody);
        }

        return new ResponseEntity<>(new ResponseDTO<>("success", null, orderResponseBodies),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateStatus(OrderStatusBody orderStatusBody, Long orderId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
        if (!orders.isPresent()) {
            return new ResponseEntity<>(new ResponseDTO<>("failure", "order is not found", null),
                    HttpStatus.BAD_REQUEST);
        }
        Orders orders1 = orders.get();
        orders1.setStatus(orderStatusBody.getStatus());
        Orders orders2 = orderRepository.save(orders1);
        List<OrderItems> orderItemsList = orderItemsRepository.findByOrderId(String.valueOf(orderId));
        Optional<Outlet> outlet = outletRepo.findById((Long.parseLong(orders1.getOutletId())));
        Object customer = custLoginRepo.findById(Long.parseLong(orders1.getCustomerId()));

        OrderResponseBody orderResponseBody = new OrderResponseBody(orders2.getId(), orders2.getTotalAmount(), orders2.getGst(), orders2.getDeliveryCharge(),
                orders2.getPayable_amount(), orders2.getDeliveryDate(), orders2.getBookingDate(), orders2.getPaymentType(), orders2.getStatus(),
                orders2.getOutletId(), orderItemsList, orders2.getTrainName(), orders2.getTrainNo(), orders2.getStationCode(), orders2.getStationName(),
                orders2.getCoach(), orders2.getBerth(), orders2.getOrderFrom(), orders2.getPnr(), orders2.getCreatedAt(), orders2.getCreatedBy(), outlet, customer);

        return new ResponseEntity<>(new ResponseDTO<>("failure", null, orderResponseBody),
                HttpStatus.OK);
    }
}

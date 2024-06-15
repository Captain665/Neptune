package ManishLokesh.Neptune.v2.Orders.Service;

import ManishLokesh.Neptune.PushToIRCTC.OrderStatus;
import ManishLokesh.Neptune.ResponseDTO.Response;
import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.Scheduler.Order.OrderStatusUpdate;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Outlet;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.MenuRepo;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.OutletRepo;
import ManishLokesh.Neptune.v2.Orders.Entity.OrderItems;
import ManishLokesh.Neptune.v2.Orders.Entity.Orders;
import ManishLokesh.Neptune.v2.Orders.Repository.OrderItemsRepository;
import ManishLokesh.Neptune.v2.Orders.Repository.OrderRepository;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderItemRequest;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderStatusBody;
import ManishLokesh.Neptune.v2.Orders.ResponseBody.*;
import ManishLokesh.Neptune.v2.Outlets.OutletResponse.OutletResponse;
import ManishLokesh.Neptune.v2.customer.Entity.Customer;
import ManishLokesh.Neptune.v2.customer.Repository.CustLoginRepo;

import ManishLokesh.Neptune.v2.customer.RequestBody.CustoLoginRequestBody;
import ManishLokesh.Neptune.v2.customer.ResponseBody.CustLoginResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.stereotype.Service;


import static ManishLokesh.Neptune.ResponseDTO.Response.ApiFailure;
import static ManishLokesh.Neptune.ResponseDTO.Response.ApiSuccess;
import static java.util.concurrent.CompletableFuture.runAsync;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletionStage;
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
    @Autowired
    private OrderPush orderPushService;
    @Autowired
    public CustLoginRepo custLoginRepo;
    public Response response;
    @Autowired
    public OrderStatus orderStatus;

    private final ObjectMapper objectMapper;

    @Autowired
    public OrderServiceImp(OrderPush orderPushService, ObjectMapper objectMapper) {
        this.orderPushService = orderPushService;
        this.objectMapper = objectMapper;
    }

    @Override
    public CompletionStage<ResponseEntity<ResponseDTO>> addOrder(OrderRequestBody orderRequestBody) {

        Object outletValid = outletRepo.findById(Long.parseLong(orderRequestBody.getOutletId()));
        logger.info("request body {} ", orderRequestBody.toString());

        if (((Optional<?>) outletValid).isEmpty()) {
            return ApiFailure("outlet is not present");
        }

        Object customerData = custLoginRepo.findById(Long.parseLong(orderRequestBody.getCustomerId()));

        if (((Optional<?>) customerData).isEmpty()) {
            return ApiFailure("customer id is not present");
        }
        List<OrderItemRequest> itemsList = orderRequestBody.getOrderItem();
        if (itemsList.isEmpty()) {
            return ApiFailure("Item list can not be empty");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.now();
        DecimalFormat df = new DecimalFormat("#.##");

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
            subtotalPrice = Double.valueOf(df.format(subtotalPrice + (basePrice * quantityValue)));

            Double itemWiseBasePrice = basePrice;
            orderItems.setBasePrice(itemWiseBasePrice);
            orderItems.setItemId(menu.getId());
            orderItems.setItemName(menu.getName());
            orderItems.setDescription(menu.getDescription());
            String createdAtDate = localDateTime.format(formatter);
            orderItems.setCreatedAt(createdAtDate);
            Double tax = Double.valueOf(df.format(itemWiseBasePrice * 0.05));
            orderItems.setTax(tax);
            orderItems.setVeg(menu.getIsVegeterian());
            Double sellingPrice = Double.valueOf(df.format(itemWiseBasePrice + tax));
            orderItems.setSellingPrice(sellingPrice);
            orderItemsList.add(orderItems);
        }

        Orders orders = new Orders();
        orders.setBerth(orderRequestBody.getBerth());
        orders.setCoach(orderRequestBody.getCoach());
        orders.setTrainName(orderRequestBody.getTrainName());
        orders.setTrainNo(orderRequestBody.getTrainNo());
        orders.setDeliveryDate(orderRequestBody.getDeliveryDate());
        orders.setOrderFrom(orderRequestBody.getOrderFrom());
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

        Double gst = subtotalPrice * 0.05;
        orders.setGst(Double.valueOf(df.format(gst)));

        double deliveryCharges = 0;
        if (orderRequestBody.getDeliveryCharge().isNaN()) {
            deliveryCharges = orderRequestBody.getDeliveryCharge();
        }
        orders.setPayable_amount(Math.round(subtotalPrice + (subtotalPrice * 0.05)) + deliveryCharges);

        Orders saveOrder = orderRepository.saveAndFlush(orders);


        for (OrderItems orderItems : orderItemsList) {
            orderItems.setOrderId(String.valueOf(saveOrder.getId()));
        }
        List<OrderItems> orderItemsData = orderItemsRepository.saveAllAndFlush(orderItemsList);
        Optional<Outlet> outlet = outletRepo.findById((Long.parseLong(saveOrder.getOutletId())));
        Object customer = custLoginRepo.findById(Long.parseLong(saveOrder.getCustomerId()));
        OrderAmountResponse orderAmountResponse = amountModel(saveOrder);
        OrderDeliveryResponse orderDeliveryResponse = deliveryModel(saveOrder);
        OrderResponseBody orderResponseBody = new OrderResponseBody(saveOrder.getId(), saveOrder.getBookingDate(),
                saveOrder.getStatus(), saveOrder.getOrderFrom(), saveOrder.getPnr(), orderDeliveryResponse,
                orderAmountResponse, orderItemsData, outlet, customer);


        runAsync(() -> orderPushService.OrderPushToIrctc(saveOrder));

        logger.info("response body {}", orderResponseBody.toString());

        return ApiSuccess(orderResponseBody);
    }

    @Override
    public ResponseEntity<ResponseDTO> getOrder(Long orderId, Long customerId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(new ResponseDTO<>("failure", "Order not found, Please Enter correct order Id", null),
                    HttpStatus.BAD_REQUEST);
        }

        Long customerDetail = Long.parseLong(orders.get().getCustomerId());
        if (!customerDetail.equals(customerId)) {
            return new ResponseEntity<>(new ResponseDTO<>("failure", "Not Authorize to Access", null),
                    HttpStatus.UNAUTHORIZED);
        }

        Orders saveOrder = orders.get();
        List<OrderItemResponse> orderItems = orderItemModel(orderItemsRepository.findByOrderId(
                String.valueOf(orderId)));
        OrderCustomerResponse orderCustomerResponse = customerModel(custLoginRepo.findByCustomerId(
                Long.parseLong(saveOrder.getCustomerId())));
        OrderOutletResponse orderOutletResponse = outletModel(outletRepo.findByOutletId(
                Long.parseLong(saveOrder.getOutletId())));
        OrderAmountResponse orderAmountResponse = amountModel(saveOrder);
        OrderDeliveryResponse orderDeliveryResponse = deliveryModel(saveOrder);


        OrderResponseBody orderResponseBody = new OrderResponseBody(saveOrder.getId(), saveOrder.getBookingDate(), saveOrder.getStatus(),
                saveOrder.getOrderFrom(), saveOrder.getPnr(), orderDeliveryResponse,
                orderAmountResponse, orderItems, orderOutletResponse, orderCustomerResponse);
        logger.info("Order Response {}",orderResponseBody);


        return new ResponseEntity<>(new ResponseDTO<>("success", null, orderResponseBody),
                HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO> getAllOrder(Long customerId) {

        List<Orders> ordersList = orderRepository.findByCustomerId(String.valueOf(customerId));

        List<OrderResponseBody> orderResponseBodies = new ArrayList<>();
        for (Orders orders : ordersList) {
            OrderResponseBody orderBody = new OrderResponseBody();
            orderBody.setId(orders.getId());
            orderBody.setBookingDate(orders.getBookingDate());
            orderBody.setStatus(orders.getStatus());
            orderBody.setPnr(orders.getPnr());
            orderBody.setOrderFrom(orders.getOrderFrom());
            List<OrderItemResponse> orderItemResponse = orderItemModel(orderItemsRepository.
                    findByOrderId(String.valueOf(orders.getId())));
            OrderOutletResponse orderOutletResponse = outletModel(outletRepo.
                    findByOutletId(Long.parseLong(orders.getOutletId())));
            OrderCustomerResponse orderCustomerResponse = customerModel(custLoginRepo.findByCustomerId(customerId));
            OrderAmountResponse orderAmountResponse = amountModel(orders);
            OrderDeliveryResponse orderDeliveryResponse = deliveryModel(orders);
            orderBody.setDelivery(orderDeliveryResponse);
            orderBody.setPayments(orderAmountResponse);
            orderBody.setCustomer(orderCustomerResponse);
            orderBody.setOutlet(orderOutletResponse);
            orderBody.setOrderItems(orderItemResponse);
            orderResponseBodies.add(orderBody);
        }
        logger.info("Response {}",orderResponseBodies);

        return new ResponseEntity<>(new ResponseDTO<>("success", null, orderResponseBodies),
                HttpStatus.OK);
    }

    @Override
    public CompletionStage<ResponseEntity<ResponseDTO>> updateStatus(OrderStatusBody orderStatusBody, Long orderId) {
        try {
            logger.info("Order Id " + orderId + " Order status" + orderStatusBody.toString());
            Optional<Orders> orders = orderRepository.findById(orderId);
            if (orders.isEmpty()) {
                logger.info("order is not found");
                return ApiFailure("order is not found");
            }
            Orders orders1 = orders.get();
            List<OrderItems> orderItemsList = orderItemsRepository.findByOrderId(String.valueOf(orderId));
            Optional<Outlet> outlet = outletRepo.findById((Long.parseLong(orders1.getOutletId())));
            Object customer = custLoginRepo.findById(Long.parseLong(orders1.getCustomerId()));

            if (Objects.equals(orders1.getStatus(), "CANCELLED")) {
                logger.info("Order already marked as cancelled");
                return ApiFailure("Order already marked as cancelled");
            }
            Map<String, Object> status = new HashMap<>();

            status.put("status", "ORDER_" + orderStatusBody.getStatus());

            if (Objects.equals(orderStatusBody.getStatus(), "CANCELLED")) {
                status.put("remarks", "PASSENGER_JOURNEY_CANCELLED");
            } else if (Objects.equals(orderStatusBody.getStatus(), "UNDELIVERED")) {
                status.put("remarks", "VENDOR_INABILITY");
            }

            long irctcOrderId = orders.get().getIrctcOrderId();
            String response = orderStatus.StatusPushToIrctc(status, irctcOrderId);

            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode orderStatus = jsonNode.get("status");

            if (Objects.equals(orderStatus.asText(), "success")) {
                JsonNode resultObject = jsonNode.get("result");
                JsonNode responseStatus = resultObject.get("status");

                if (Objects.equals(responseStatus.asText(), "ORDER_" + orderStatusBody.getStatus())) {
                    orders1.setStatus(orderStatusBody.getStatus());
                }
            } else {
                JsonNode msg = jsonNode.get("message");
                return ApiFailure(msg.asText());
            }

            Orders orders2 = orderRepository.save(orders1);
            OrderAmountResponse orderAmountResponse = amountModel(orders2);
            OrderDeliveryResponse orderDeliveryResponse = deliveryModel(orders2);

            OrderResponseBody orderResponseBody = new OrderResponseBody(orders2.getId(),
                    orders2.getBookingDate(), orders2.getStatus(), orders2.getOrderFrom(),
                    orders2.getPnr(),orderDeliveryResponse, orderAmountResponse, orderItemsList, outlet, customer);

            return ApiSuccess(orderResponseBody);
        } catch (Exception e) {
            return ApiFailure(e.getMessage());
        }

    }

    public OrderCustomerResponse customerModel(Customer customerData) {
        OrderCustomerResponse custBody = new OrderCustomerResponse();
        custBody.setMobileNumber(customerData.getMobileNumber());
        custBody.setEmailId(customerData.getEmailId());
        custBody.setId(customerData.getId());
        custBody.setFullName(customerData.getFullName());
        return custBody;
    }

    public OrderOutletResponse outletModel(Outlet outlet) {
        OrderOutletResponse outletResponse = new OrderOutletResponse();
        outletResponse.setId(outlet.getId());
        outletResponse.setOutletName(outlet.getOutletName());
        outletResponse.setCity(outlet.getCity());
        outletResponse.setFssaiNo(outlet.getFssaiNo());
        outletResponse.setMobileNo(outlet.getMobileNo());
        return outletResponse;
    }

    public List<OrderItemResponse> orderItemModel(List<OrderItems> orderItems) {
        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for (OrderItems orderItems1 : orderItems) {
            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setId(orderItems1.getId());
            orderItemResponse.setItemName(orderItems1.getItemName());
            orderItemResponse.setDescription(orderItems1.getDescription());
            orderItemResponse.setBasePrice(orderItems1.getBasePrice());
            orderItemResponse.setQuantity(orderItems1.getQuantity());
            orderItemResponse.setVeg(orderItems1.getVeg());
            itemResponses.add(orderItemResponse);
        }
        return itemResponses;
    }

    public OrderAmountResponse amountModel(Orders order) {
        OrderAmountResponse orderAmountResponse = new OrderAmountResponse();
        orderAmountResponse.setPaymentType(order.getPaymentType());
        orderAmountResponse.setTotalAmount(order.getTotalAmount());
        orderAmountResponse.setGst(order.getGst());
        orderAmountResponse.setDeliveryCharge(order.getDeliveryCharge());
        orderAmountResponse.setPayable_amount(order.getPayable_amount());
        return orderAmountResponse;
    }

    public OrderDeliveryResponse deliveryModel(Orders orders) {
        OrderDeliveryResponse orderDeliveryResponse = new OrderDeliveryResponse();
        orderDeliveryResponse.setDeliveryDate(orders.getDeliveryDate());
        orderDeliveryResponse.setBerth(orders.getBerth());
        orderDeliveryResponse.setCoach(orders.getCoach());
        orderDeliveryResponse.setStationCode(orders.getStationCode());
        orderDeliveryResponse.setStationName(orders.getStationName());
        orderDeliveryResponse.setTrainName(orders.getTrainName());
        orderDeliveryResponse.setTrainNo(orders.getTrainNo());
        return orderDeliveryResponse;
    }
}

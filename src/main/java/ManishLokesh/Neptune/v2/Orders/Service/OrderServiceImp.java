package ManishLokesh.Neptune.v2.Orders.Service;

import ManishLokesh.Neptune.ResponseDTO.ResponseDTO;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.Menu;
import ManishLokesh.Neptune.v1.OutletsAndMenu.Repository.MenuRepo;
import ManishLokesh.Neptune.v2.Orders.Entity.OrderItems;
import ManishLokesh.Neptune.v2.Orders.Entity.Orders;
import ManishLokesh.Neptune.v2.Orders.Repository.OrderItemsRepository;
import ManishLokesh.Neptune.v2.Orders.Repository.OrderRepository;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderItemRequest;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderRequestBody;
import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderStatusBody;
import ManishLokesh.Neptune.v2.Orders.ResponseBody.OrderResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImp implements OrderService{


    @Autowired
    public MenuRepo menuRepo;
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public OrderItemsRepository orderItemsRepository;

    @Override
    public ResponseEntity<ResponseDTO> addOrder(OrderRequestBody orderRequestBody) {

        List<OrderItemRequest> itemsList = orderRequestBody.getOrderItem();
        List<Long> itemIds = itemsList.stream().map(OrderItemRequest::getItemId).filter(Objects::nonNull).collect(Collectors.toList());
        List<Menu> menuList = itemIds.stream().map(itemId -> menuRepo.findById(itemId)).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        List<OrderItems> orderItemsList = new ArrayList<>();
        float subtotalPrice = 0.0F;
        for(Menu menu : menuList){
            OrderItems orderItems = new OrderItems();
            Long itemId = menu.getId();
            Optional<OrderItemRequest> quantity = itemsList.stream().filter(findId -> findId.getItemId().equals(itemId)).findFirst();
            Integer quantityValue = quantity.get().getQuantity();
            orderItems.setQuantity(quantityValue);
            Float basePrice = Float.parseFloat(menu.getBasePrice());
            subtotalPrice = subtotalPrice+(basePrice*quantityValue);
            orderItems.setBasePrice(basePrice);
            orderItems.setItemId(menu.getId());
            orderItems.setItemName(menu.getName());
            orderItems.setDescription(menu.getDescription());
            orderItems.setCreatedAt(LocalDateTime.now().toString());
            orderItems.setTax((float) (Float.parseFloat(menu.getBasePrice()) * 0.05));
            orderItemsList.add(orderItems);
        }
        Orders orders = new Orders();
        orders.setBerth(orderRequestBody.getBerth());
        orders.setCoach(orderRequestBody.getCoach());
        orders.setTrainName(orderRequestBody.getTrainName());
        orders.setTrainNo(orderRequestBody.getTrainNo());
        orders.setDeliveryDate(orderRequestBody.getDeliveryDate());
        orders.setOrderFrom(orderRequestBody.getOrderFrom());
        orders.setCreatedAt(LocalDateTime.now().toString());
        orders.setBookingDate(LocalDateTime.now().toString());
        orders.setStationName(orderRequestBody.getStationName());
        orders.setStationCode(orderRequestBody.getStationCode());
        orders.setOutletId(orderRequestBody.getOutletId());
        orders.setCustomerId(orderRequestBody.getCustomerId());
        orders.setPnr(orderRequestBody.getPnr());
        orders.setCreatedBy("CUSTOMER");
        orders.setStatus("ORDER_PLACED");
        orders.setPaymentType(orderRequestBody.getPaymentType());
        orders.setDeliveryCharge(orderRequestBody.getDeliveryCharge());
        orders.setTotalAmount(subtotalPrice);
        orders.setGst((float) (subtotalPrice * 0.05));
        orders.setPayable_amount((float) (subtotalPrice + (subtotalPrice*0.05) + orderRequestBody.getDeliveryCharge()));
        Orders saveOrder = orderRepository.saveAndFlush(orders);
        for(OrderItems orderItems : orderItemsList){
            orderItems.setOrderId(String.valueOf(saveOrder.getId()));
        }
        List<OrderItems> orderItems1 = orderItemsRepository.saveAllAndFlush(orderItemsList);

        OrderResponseBody orderResponseBody = new OrderResponseBody(saveOrder.getId(),saveOrder.getTotalAmount(),saveOrder.getGst(),saveOrder.getDeliveryCharge(),
                saveOrder.getPayable_amount(),saveOrder.getDeliveryDate(),saveOrder.getBookingDate(),saveOrder.getPaymentType(),saveOrder.getStatus(),
                saveOrder.getCustomerId(),saveOrder.getOutletId(),orderItems1,saveOrder.getTrainName(), saveOrder.getTrainNo(), saveOrder.getStationCode(), saveOrder.getStationName(),
                saveOrder.getCoach(), saveOrder.getBerth(), saveOrder.getOrderFrom(),saveOrder.getPnr(),saveOrder.getCreatedAt(),saveOrder.getCreatedBy());
        return new ResponseEntity<>(
                new ResponseDTO("success", null, orderResponseBody),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getOrder(Long orderId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
        if(orders.isPresent()){
            Orders saveOrder = orders.get();
            List<OrderItems> orderItems1 = orderItemsRepository.findByOrderId(String.valueOf(orderId));
            OrderResponseBody orderResponseBody = new OrderResponseBody(saveOrder.getId(),saveOrder.getTotalAmount(),saveOrder.getGst(),saveOrder.getDeliveryCharge(),
                    saveOrder.getPayable_amount(),saveOrder.getDeliveryDate(),saveOrder.getBookingDate(),saveOrder.getPaymentType(),saveOrder.getStatus(),
                    saveOrder.getCustomerId(),saveOrder.getOutletId(),orderItems1,saveOrder.getTrainName(), saveOrder.getTrainNo(), saveOrder.getStationCode(), saveOrder.getStationName(),
                    saveOrder.getCoach(), saveOrder.getBerth(), saveOrder.getOrderFrom(),saveOrder.getPnr(),saveOrder.getCreatedAt(),saveOrder.getCreatedBy());

            return new ResponseEntity<>(new ResponseDTO<>("success",null,orderResponseBody),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","Incorrect order Id",null),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<ResponseDTO> getAllOrder() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderResponseBody> orderResponseBodies = new ArrayList<>();
        for(Orders orders :ordersList){
            OrderResponseBody orderBody = new OrderResponseBody();
            orderBody.setId(orders.getId());
            orderBody.setTrainName(orders.getTrainName());
            orderBody.setTrainNo(orders.getTrainNo());
            orderBody.setStationCode(orders.getStationCode());
            orderBody.setStationName(orders.getStationName());
            orderBody.setCoach(orders.getCoach());
            orderBody.setBerth(orders.getBerth());
            orderBody.getDeliveryDate(orders.getDeliveryDate());
            orderBody.setBookingDate(orders.getBookingDate());
            orderBody.setOutletId(orders.getOutletId());
            orderBody.setCustomerId(orders.getCustomerId());
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
            List<OrderItems> orderItemsList = orderItemsRepository.findByOrderId(String.valueOf(orders.getId()));
            orderBody.setOrderItems(orderItemsList);
            orderResponseBodies.add(orderBody);
        }

        return new ResponseEntity<>(new ResponseDTO<>("success",null,orderResponseBodies),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateStatus(OrderStatusBody orderStatusBody, Long orderId) {
        Optional<Orders> orders = orderRepository.findById(orderId);
        if(orders.isPresent()){
            Orders orders1 = orders.get();
            orders1.setStatus(orderStatusBody.getStatus());
            Orders orders2 = orderRepository.save(orders1);
            List<OrderItems> orderItemsList = orderItemsRepository.findByOrderId(String.valueOf(orderId));
            OrderResponseBody orderResponseBody = new OrderResponseBody(orders2.getId(),orders2.getTotalAmount(),orders2.getGst(),orders2.getDeliveryCharge(),
                    orders2.getPayable_amount(),orders2.getDeliveryDate(),orders2.getBookingDate(),orders2.getPaymentType(),orders2.getStatus(),
                    orders2.getCustomerId(),orders2.getOutletId(),orderItemsList,orders2.getTrainName(), orders2.getTrainNo(), orders2.getStationCode(), orders2.getStationName(),
                    orders2.getCoach(), orders2.getBerth(), orders2.getOrderFrom(),orders2.getPnr(),orders2.getCreatedAt(),orders2.getCreatedBy());

            return new ResponseEntity<>(new ResponseDTO<>("failure",null,orderResponseBody),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO<>("failure","order is not found",null),
                HttpStatus.BAD_REQUEST);
    }
}

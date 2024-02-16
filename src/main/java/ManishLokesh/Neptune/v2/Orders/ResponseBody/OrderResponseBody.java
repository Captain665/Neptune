package ManishLokesh.Neptune.v2.Orders.ResponseBody;

import ManishLokesh.Neptune.v2.Orders.RequestBody.OrderPushToIRCTC.OrderItemsInfo;

import java.util.List;

public class OrderResponseBody {
    private Long id;
    private String trainName;
    private String trainNo;
    private String stationCode;
    private String stationName;
    private String coach;
    private String berth;
    private String deliveryDate;
    private String bookingDate;
    private String outletId;
    private String createdAt;
    private String status;
    private String createdBy;
    private String pnr;
    private String paymentType;
    private Double deliveryCharge;
    private String orderFrom;
    private Double totalAmount;
    private Double gst;
    private Double payable_amount;
    private Object orderItems;
    private Object outlets;
    private Object customerDetail;


    public OrderResponseBody(){

    }

    public OrderResponseBody(Long id,Double totalAmount, Double gst, Double deliveryCharge, Double payable_amount,String deliveryDate,String bookingDate,
                             String paymentType,String status ,String outletId,Object orderItems,String trainName, String trainNo,String stationCode,
                             String stationName,String coach,String berth,String orderFrom,String pnr,String createdAt,String createdBy, Object outlets,Object customerDetail){
        this.id = id;
        this.totalAmount = totalAmount;
        this.gst = gst;
        this.deliveryCharge = deliveryCharge;
        this.payable_amount = payable_amount;
        this.deliveryDate = deliveryDate;
        this.bookingDate = bookingDate;
        this.paymentType = paymentType;
        this.status = status;
        this.outletId = outletId;
        this.orderItems = orderItems;
        this.trainName = trainName;
        this.trainNo = trainNo;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.coach = coach;
        this.berth = berth;
        this.orderFrom = orderFrom;
        this.pnr = pnr;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.outlets = outlets;
        this.customerDetail = customerDetail;
    }


    public Object getOutlets() {
        return outlets;
    }

    public void setOutlets(Object outlets) {
        this.outlets = outlets;
    }

    public Object getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(Object customerDetail) {
        this.customerDetail = customerDetail;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getBerth() {
        return berth;
    }

    public void setBerth(String berth) {
        this.berth = berth;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getGst() {
        return gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Double getPayable_amount() {
        return payable_amount;
    }

    public void setPayable_amount(Double payable_amount) {
        this.payable_amount = payable_amount;
    }

    public List<OrderItemsInfo> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Object orderItems) {
        this.orderItems = orderItems;
    }
}

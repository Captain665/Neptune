package ManishLokesh.Neptune.v2.Orders.ResponseBody;

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
    private String customerId;
    private String createdAt;
    private String status;
    private String createdBy;
    private String pnr;
    private String paymentType;
    private Float deliveryCharge;
    private String orderFrom;
    private Float totalAmount;
    private Float gst;
    private Float payable_amount;
    private Object orderItems;

    public OrderResponseBody(){

    }

    public OrderResponseBody(Long id,Float totalAmount, Float gst, Float deliveryCharge, Float payable_amount,String deliveryDate,String bookingDate,
                             String paymentType,String status,String customerId,String outletId,Object orderItems,String trainName, String trainNo,String stationCode,
                             String stationName,String coach,String berth,String orderFrom,String pnr,String createdAt,String createdBy){
        this.id = id;
        this.totalAmount = totalAmount;
        this.gst = gst;
        this.deliveryCharge = deliveryCharge;
        this.payable_amount = payable_amount;
        this.deliveryDate = deliveryDate;
        this.bookingDate = bookingDate;
        this.paymentType = paymentType;
        this.status = status;
        this.customerId = customerId;
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

    public String getDeliveryDate(String deliveryDate) {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Float getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Float deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getGst() {
        return gst;
    }

    public void setGst(Float gst) {
        this.gst = gst;
    }

    public Float getPayable_amount() {
        return payable_amount;
    }

    public void setPayable_amount(Float payable_amount) {
        this.payable_amount = payable_amount;
    }

    public Object getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Object orderItems) {
        this.orderItems = orderItems;
    }
}

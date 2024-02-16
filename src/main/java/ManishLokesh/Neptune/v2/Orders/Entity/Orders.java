package ManishLokesh.Neptune.v2.Orders.Entity;

import javax.persistence.*;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Double deliveryCharge;
    private String orderFrom;
    private Double totalAmount;
    private Double gst;
    private Double payable_amount;

    private Long IrctcOrderId;


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

    public String getDeliveryDate() {
        return deliveryDate;
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

    public Long getIrctcOrderId() {
        return IrctcOrderId;
    }

    public void setIrctcOrderId(Long irctcOrderId) {
        IrctcOrderId = irctcOrderId;
    }
}

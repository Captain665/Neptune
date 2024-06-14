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
    private String status;
    private String pnr;
    private String orderFrom;
    private Object payments;
    private Object orderItems;
    private Object outlets;
    private Object customerDetail;


    public OrderResponseBody() {

    }

    public OrderResponseBody(Long id, String deliveryDate, String bookingDate,
                             String status, String trainName, String trainNo, String stationCode,
                             String stationName, String coach, String berth, String orderFrom,
                             String pnr, Object payments, Object orderItems, Object outlets, Object customerDetail) {
        this.id = id;
        this.deliveryDate = deliveryDate;
        this.bookingDate = bookingDate;
        this.status = status;
        this.trainName = trainName;
        this.trainNo = trainNo;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.coach = coach;
        this.berth = berth;
        this.orderFrom = orderFrom;
        this.pnr = pnr;
        this.payments = payments;
        this.orderItems = orderItems;
        this.outlets = outlets;
        this.customerDetail = customerDetail;
    }

    @Override
    public String toString() {
        return "OrderResponseBody{" +
                "id=" + id +
                ", trainName='" + trainName + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", stationCode='" + stationCode + '\'' +
                ", stationName='" + stationName + '\'' +
                ", coach='" + coach + '\'' +
                ", berth='" + berth + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", status='" + status + '\'' +
                ", pnr='" + pnr + '\'' +
                ", orderFrom='" + orderFrom + '\'' +
                ", payments=" + payments +
                ", orderItems=" + orderItems +
                ", outlets=" + outlets +
                ", customerDetail=" + customerDetail +
                '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    public Object getPayments() {
        return payments;
    }

    public void setPayments(Object payments) {
        this.payments = payments;
    }

    public Object getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Object orderItems) {
        this.orderItems = orderItems;
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
}

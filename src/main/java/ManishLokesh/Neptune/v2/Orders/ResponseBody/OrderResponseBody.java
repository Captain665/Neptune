package ManishLokesh.Neptune.v2.Orders.ResponseBody;


public class OrderResponseBody {
    private Long id;
    private String bookingDate;
    private String status;
    private String pnr;
    private String orderFrom;
    private Object delivery;
    private Object payments;
    private Object orderItems;
    private Object outlets;
    private Object customerDetail;


    public OrderResponseBody() {

    }

    public OrderResponseBody(Long id, String bookingDate, String status, String orderFrom, String pnr,Object delivery,
                             Object payments, Object orderItems, Object outlets, Object customerDetail) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.status = status;
        this.orderFrom = orderFrom;
        this.pnr = pnr;
        this.delivery = delivery;
        this.payments = payments;
        this.orderItems = orderItems;
        this.outlets = outlets;
        this.customerDetail = customerDetail;
    }

    @Override
    public String toString() {
        return "OrderResponseBody{" +
                "id=" + id +
                ", bookingDate='" + bookingDate + '\'' +
                ", status='" + status + '\'' +
                ", pnr='" + pnr + '\'' +
                ", orderFrom='" + orderFrom + '\'' +
                ", delivery=" + delivery +
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

    public Object getDelivery() {
        return delivery;
    }

    public void setDelivery(Object delivery) {
        this.delivery = delivery;
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

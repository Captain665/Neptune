package ManishLokesh.Neptune.v1.OutletsAndMenu.ReponseBody;

import ManishLokesh.Neptune.v1.OutletsAndMenu.Entity.OutletClosing;

import java.util.List;

public class CreateOutletResponse <T>{

    private Long id;
    private String outletName;
    private Long minOrderValue;
    private Integer orderTiming;
    private String openingTime;
    private String closingTime;
    private Integer deliveryCost;
    private String address;
    private String city;
    private String state;
    private Boolean prepaid;
    private String companyName;
    private String panCard;
    private String gstNo;
    private String fssaiNo;
    private String fssaiValidUpto;
    private Boolean active;
    private String createdAt;
    private Object outletClosing;
    private String updatedAt;
    private String logoImage;
    private String emailId;
    private String mobileNo;
    private String stationCode;


    public CreateOutletResponse(Long id, String outletName, Long minOrderValue, Integer orderTiming, String openingTime,
                                String closingTime, Integer deliveryCost, String address, String city, String state,
                                Boolean prepaid, String companyName, String panCard, String gstNo, String fssaiNo,
                                String fssaiValidUpto, Boolean active,String createdAt, Object outletClosing ,
                                String updatedAt, String logoImage, String emailId, String mobileNo,
                                String stationCode){
        this.id = id;
        this.outletName = outletName;
        this.minOrderValue = minOrderValue;
        this.orderTiming = orderTiming;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.deliveryCost = deliveryCost;
        this.address = address;
        this.city = city;
        this.state = state;
        this.prepaid = prepaid;
        this.companyName = companyName;
        this.panCard = panCard;
        this.gstNo = gstNo;
        this.fssaiNo = fssaiNo;
        this.fssaiValidUpto = fssaiValidUpto;
        this.active = active;
        this.createdAt = createdAt;
        this.outletClosing = outletClosing;
        this.updatedAt = updatedAt;
        this.logoImage = logoImage;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
        this.stationCode = stationCode;
    }


    public CreateOutletResponse(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public Long getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(Long minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public Integer getOrderTiming() {
        return orderTiming;
    }

    public void setOrderTiming(Integer orderTiming) {
        this.orderTiming = orderTiming;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }
    public Object getOutletClosing() {
        return outletClosing;
    }

    public void setOutletClosing(Object outletClosing) {
        this.outletClosing = outletClosing;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public Integer getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Integer deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(Boolean prepaid) {
        this.prepaid = prepaid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getFssaiNo() {
        return fssaiNo;
    }

    public void setFssaiNo(String fssaiNo) {
        this.fssaiNo = fssaiNo;
    }

    public String getFssaiValidUpto() {
        return fssaiValidUpto;
    }

    public void setFssaiValidUpto(String fssaiValidUpto) {
        this.fssaiValidUpto = fssaiValidUpto;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

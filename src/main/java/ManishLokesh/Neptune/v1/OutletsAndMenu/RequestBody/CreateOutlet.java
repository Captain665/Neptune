package ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody;

import org.json.simple.JSONArray;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

public class CreateOutlet {
    private List<String> stationCode;
    private String outletName;
    private Long minOrderValue;
    private Integer orderTiming;
    private String openingTime;
    private String closingTime;
    private Double deliveryCost;
    private String address;
    private String city;
    private String state;
    private Boolean prepaid;
    private String companyName;
    private String panCard;
    private String gstNo;
    private String fssaiNo;
    private String fssaiValidUpto;
    private List<OutletClosingRequest> outletClosing;
    private String logoImage;
    private String emailId;
    private String mobileNo;
    private String tags;


    public CreateOutlet(){
    }

    @Override
    public String toString() {
        return "CreateOutlet{" +
                "stationCode='" + stationCode + '\'' +
                ", outletName='" + outletName + '\'' +
                ", minOrderValue=" + minOrderValue +
                ", orderTiming=" + orderTiming +
                ", openingTime='" + openingTime + '\'' +
                ", closingTime='" + closingTime + '\'' +
                ", deliveryCost=" + deliveryCost +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", prepaid=" + prepaid +
                ", companyName='" + companyName + '\'' +
                ", panCard='" + panCard + '\'' +
                ", gstNo='" + gstNo + '\'' +
                ", fssaiNo='" + fssaiNo + '\'' +
                ", fssaiValidUpto='" + fssaiValidUpto + '\'' +
                ", outletClosing=" + outletClosing +
                ", logoImage='" + logoImage + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }

    public List<String> getStationCode() {
        return stationCode;
    }

    public void setStationCode(List<String> stationCode) {
        this.stationCode = stationCode;
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

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
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

    public List getOutletClosing() {
        return outletClosing;
    }

    public void setOutletClosing(List outletClosing) {
        this.outletClosing = outletClosing;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

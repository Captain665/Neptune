package ManishLokesh.Neptune.v2.Outlets.OutletResponse;import java.util.List;public class OutletResponse <T>{    private Long id;    private String outletName;    private Long minOrderValue;    private Integer orderTiming;    private Double deliveryCost;    private Boolean prepaid;    private String logoImage;    private String tags;    private Double ratingValue;    private Integer ratingCount;//    public OutletResponse(Long id, String outletName, Long minOrderValue, Integer orderTiming,//                          Double deliveryCost, Boolean prepaid, String logoImage, String tags, Integer ratingCount,//                          Double ratingValue, String getLogoImage){////        this.id = id;//        this.outletName = outletName;//        this.minOrderValue = minOrderValue;//        this.orderTiming = orderTiming;//        this.deliveryCost = deliveryCost;//        this.prepaid = prepaid;//        this.logoImage = logoImage;//        this.tags = tags;//        this.ratingCount = ratingCount;//        this.ratingValue = ratingValue;//        this.getLogoImage = getLogoImage;//    }    public Long getId() {        return id;    }    public void setId(Long id) {        this.id = id;    }    public String getOutletName() {        return outletName;    }    public void setOutletName(String outletName) {        this.outletName = outletName;    }    public Long getMinOrderValue() {        return minOrderValue;    }    public void setMinOrderValue(Long minOrderValue) {        this.minOrderValue = minOrderValue;    }    public Integer getOrderTiming() {        return orderTiming;    }    public void setOrderTiming(Integer orderTiming) {        this.orderTiming = orderTiming;    }    public Double getDeliveryCost() {        return deliveryCost;    }    public void setDeliveryCost(Double deliveryCost) {        this.deliveryCost = deliveryCost;    }    public Boolean getPrepaid() {        return prepaid;    }    public void setPrepaid(Boolean prepaid) {        this.prepaid = prepaid;    }    public String getLogoImage() {        return logoImage;    }    public void setLogoImage(String logoImage) {        this.logoImage = logoImage;    }    public String getTags() {        return tags;    }    public void setTags(String tags) {        this.tags = tags;    }    public Double getRatingValue() {        return ratingValue;    }    public void setRatingValue(Double ratingValue) {        this.ratingValue = ratingValue;    }    public Integer getRatingCount() {        return ratingCount;    }    public void setRatingCount(Integer ratingCount) {        this.ratingCount = ratingCount;    }}
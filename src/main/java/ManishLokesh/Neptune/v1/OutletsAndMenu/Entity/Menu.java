package ManishLokesh.Neptune.v1.OutletsAndMenu.Entity;

import javax.persistence.*;

@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String outletId;
    private String name;
    private String description;
    private Double basePrice;
    private Double tax;
    private Double sellingPrice;
    private String foodType;
    private String cuisine;
    private String tags;
    private Boolean bulkOnly;
    private Boolean isVegeterian;
    private String image;
    private String customisations;
    private String openingTime;
    private String closingTime;
    private String createdAt;
    private String updatedAt;
    private Boolean active;
    private String irctcMenuId;

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", outletId='" + outletId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", basePrice=" + basePrice +
                ", tax=" + tax +
                ", sellingPrice=" + sellingPrice +
                ", foodType='" + foodType + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", tags='" + tags + '\'' +
                ", bulkOnly=" + bulkOnly +
                ", isVegeterian=" + isVegeterian +
                ", image='" + image + '\'' +
                ", customisations='" + customisations + '\'' +
                ", openingTime='" + openingTime + '\'' +
                ", closingTime='" + closingTime + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", active=" + active +
                ", irctcMenuId='" + irctcMenuId + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getBulkOnly() {
        return bulkOnly;
    }

    public void setBulkOnly(Boolean bulkOnly) {
        this.bulkOnly = bulkOnly;
    }

    public Boolean getIsVegeterian() {
        return isVegeterian;
    }

    public void setIsVegeterian(Boolean isVegeterian) {
        this.isVegeterian = isVegeterian;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCustomisations() {
        return customisations;
    }

    public void setCustomisations(String customisations) {
        this.customisations = customisations;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getIrctcMenuId() {
        return irctcMenuId;
    }

    public void setIrctcMenuId(String irctcMenuId) {
        this.irctcMenuId = irctcMenuId;
    }
}



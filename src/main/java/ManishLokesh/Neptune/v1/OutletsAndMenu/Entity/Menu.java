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
    private String basePrice;
    private String tax;
    private String sellingPrice;
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

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
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



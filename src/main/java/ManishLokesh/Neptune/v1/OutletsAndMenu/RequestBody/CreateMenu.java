package ManishLokesh.Neptune.v1.OutletsAndMenu.RequestBody;


public class CreateMenu {
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


    public CreateMenu(String name,String description, Double basePrice,Double tax,
                      Double sellingPrice,String foodType,String cuisine, String tags, Boolean bulkOnly,
                      Boolean isVegeterian, String image, String customisations){
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.tax = tax;
        this.sellingPrice = sellingPrice;
        this.foodType = foodType;
        this.cuisine = cuisine;
        this.tags = tags;
        this.bulkOnly = bulkOnly;
        this.isVegeterian = isVegeterian;
        this.image = image;
        this.customisations = customisations;
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

    public Boolean getVegeterian() {
        return isVegeterian;
    }

    public void setVegeterian(Boolean vegeterian) {
        isVegeterian = vegeterian;
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

}

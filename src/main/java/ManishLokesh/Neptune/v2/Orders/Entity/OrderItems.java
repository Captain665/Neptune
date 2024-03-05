package ManishLokesh.Neptune.v2.Orders.Entity;

import javax.persistence.*;

@Entity
@Table(name = "orderItems")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderId;
    private Integer quantity;
    private Double basePrice;
    private String itemName;
    private Long itemId;
    private String description;
    private Double tax;
    private String createdAt;
    private Boolean isVeg;

    private Double sellingPrice;

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", quantity=" + quantity +
                ", basePrice=" + basePrice +
                ", itemName='" + itemName + '\'' +
                ", itemId=" + itemId +
                ", description='" + description + '\'' +
                ", tax=" + tax +
                ", createdAt='" + createdAt + '\'' +
                ", isVeg=" + isVeg +
                ", sellingPrice=" + sellingPrice +
                '}';
    }

    public Boolean getVeg() {
        return isVeg;
    }

    public void setVeg(Boolean veg) {
        isVeg = veg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String  orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}

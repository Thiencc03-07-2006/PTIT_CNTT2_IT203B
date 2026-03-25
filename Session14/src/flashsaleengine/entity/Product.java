package flashsaleengine.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Product {
    private int id;
    private String productCode;
    private String productName;
    private String category;
    private BigDecimal price;
    private int stockQuantity;
    private Timestamp createdAt;

    public Product() {}

    public Product(String productCode, String productName, String category,
                   BigDecimal price, int stockQuantity) {
        this.productCode = productCode;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
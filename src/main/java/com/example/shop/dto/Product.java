package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal; // DECIMAL 타입 매핑
import java.sql.Timestamp; // TIMESTAMP 타입 매핑

@Setter
@Getter

public class Product {
    private int productId;
    private String productName;
    private String description;
    private BigDecimal price; // 가격은 정밀한 계산을 위해 BigDecimal 사용
    private int stockQuantity;
    private Integer categoryId; // FK는 null이 될 수 있으므로 Integer (Wrapper class) 사용
    private Integer brandId;    // FK는 null이 될 수 있으므로 Integer (Wrapper class) 사용
    private String mainImageUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean isActive;
    private BigDecimal averageRating; // 평균 별점
    private int reviewCount; // 리뷰 개수

    // 기본 생성자
    public Product() {
    }

    // 모든 필드를 포함하는 생성자 (필요에 따라 오버로드 가능)
    public Product(int productId, String productName, String description, BigDecimal price,
                   int stockQuantity, Integer categoryId, Integer brandId, String mainImageUrl,
                   Timestamp createdAt, Timestamp updatedAt, boolean isActive,
                   BigDecimal averageRating, int reviewCount) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.mainImageUrl = mainImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
    }

    // --- Getter와 Setter 메소드 ---
    // 각 필드에 대한 getter와 setter를 여기에 작성합니다.
    // 예시:

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", isActive=" + isActive +
                '}';
    }
}

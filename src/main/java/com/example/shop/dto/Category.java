package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Category {
    private int categoryId;
    private String categoryName;
    private Integer parentCategoryId; // 상위 카테고리 ID, NULL 가능하므로 Integer (Wrapper class) 사용
    private String description;
    private String imageUrl; // SQL 스키마의 image_url
    private int displayOrder;
    private boolean isActive;

    // 기본 생성자
    public Category() {
    }

    // 모든 필드를 포함하는 생성자
    public Category(int categoryId, String categoryName, Integer parentCategoryId,
                    String description, String imageUrl, int displayOrder, boolean isActive) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentCategoryId = parentCategoryId;
        this.description = description;
        this.imageUrl = imageUrl;
        this.displayOrder = displayOrder;
        this.isActive = isActive;
    }

    // --- Getter와 Setter 메소드 ---
    // 각 필드에 대한 getter와 setter를 여기에 작성합니다.

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                ", isActive=" + isActive +
                '}';
    }
}

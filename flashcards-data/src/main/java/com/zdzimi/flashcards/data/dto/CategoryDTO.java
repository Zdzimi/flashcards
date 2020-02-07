package com.zdzimi.flashcards.data.dto;

import java.sql.Date;

public class CategoryDTO {

    private Integer categoryId;
    private String categoryName;
    private Date lastUpdate;

    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryDTO(Integer categoryId, String categoryName, Date lastUpdate) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.lastUpdate = lastUpdate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}

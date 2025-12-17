package com.ShopGameBD.ShopGamePostgre.dto;

import java.math.BigDecimal;

public class GamePatchDTO {
    private String name;
    private BigDecimal price;
    private Integer age;
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryID(Integer categoryId) {
        this.categoryId = categoryId;
    }
}


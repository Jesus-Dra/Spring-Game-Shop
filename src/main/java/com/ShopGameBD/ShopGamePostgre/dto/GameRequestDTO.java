package com.ShopGameBD.ShopGamePostgre.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class GameRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Min(1995)
    private Integer age;

    @NotBlank
    private String type;

    @NotNull
    @DecimalMin("0.1")
    private BigDecimal price;

    private Integer categoryID;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }
}

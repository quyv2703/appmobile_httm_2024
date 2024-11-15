package com.ltq27.baotrimaylanh.model;

public class DichVu {
    private Long id;
    private String name;
    private String commonPrice;
    private Boolean isFixedPrice;

    public DichVu(String name, String commonPrice, Boolean isFixedPrice) {
        this.name = name;
        this.commonPrice = commonPrice;
        this.isFixedPrice = isFixedPrice;
    }

    public String getCommonPrice() {
        return commonPrice;
    }

    public void setCommonPrice(String commonPrice) {
        this.commonPrice = commonPrice;
    }

    public Boolean getFixedPrice() {
        return isFixedPrice;
    }

    public void setFixedPrice(Boolean fixedPrice) {
        isFixedPrice = fixedPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

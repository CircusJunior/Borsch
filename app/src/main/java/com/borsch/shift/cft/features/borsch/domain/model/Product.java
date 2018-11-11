package com.borsch.shift.cft.features.borsch.domain.model;

public final class Product {
    private String id;
    private String foodId;
    private String foodName;
    private String allWeight;
    private String reservedWeight;

    public Product(String foodId, String foodName){
        this.foodId = foodId;
        this.allWeight = "1";
        this.reservedWeight = "0" ;
        this.foodName = foodName;
    }

    public Product(String id){
        this.id = id;
        this.foodId = null;
        this.allWeight = null;
        this.reservedWeight = null;
        this.foodName = null;
    }

    public String getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodId() {
        return foodId;
    }

    public String getAllWeight() {
        return allWeight;
    }

    public String getReservedWeight() {
        return reservedWeight;
    }
}

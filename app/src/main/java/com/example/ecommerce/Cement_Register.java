package com.example.ecommerce;

public class Cement_Register {

    public String getCementName() { return cementName; }

    public void setCementName(String cementName) {
        this.cementName = cementName;
    }

    public String getCementPrice() {
        return cementPrice;
    }

    public void setCementPrice(String cementPrice) {
        this.cementPrice = cementPrice;
    }

    public String getCementStock() { return cementStock; }

    public void setCementStock(String cementStock) { this.cementStock = cementStock; }

    public String getCementQuantity() { return cementQuantity; }

    public void setCementQuantity(String cementQuantity) { this.cementQuantity = cementQuantity; }

    public int getCementImage() {
        return cementImage;
    }

    public void setCementImage(int cementImage) {
        this.cementImage = cementImage;
    }


    private String cementName;
    private String cementPrice;
    private String cementStock;
    private int cementImage;
    private String cementQuantity;


    public Cement_Register() {

    }
}



package com.hcm.sale_laptop.data.model.other;

public class DiscountedProductModel {
    private String urlImage;
    private String productName;
    private float originalPrice;
    private float currentPrice;

    public DiscountedProductModel(String urlImage, String productName, float originalPrice, float currentPrice) {
        this.urlImage = urlImage;
        this.productName = productName;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }
}

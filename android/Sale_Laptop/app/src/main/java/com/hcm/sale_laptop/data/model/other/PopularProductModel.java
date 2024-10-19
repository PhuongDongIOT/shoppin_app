package com.hcm.sale_laptop.data.model.other;

public class PopularProductModel {
    private String urlImage;
    private String productName;
    private float price;

    public PopularProductModel(String urlImage, String productName, float price) {
        this.urlImage = urlImage;
        this.productName = productName;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

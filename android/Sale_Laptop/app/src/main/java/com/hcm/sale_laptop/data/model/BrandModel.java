package com.hcm.sale_laptop.data.model;

import java.util.List;

public class BrandModel {
    private Integer imageView;  // List chứa các resource ID của ảnh
    private String brandName;

    public BrandModel(Integer imageView, String brandName) {
        this.imageView = imageView;
        this.brandName = brandName;
    }

    public Integer getImageView() {
        return imageView;
    }

    public void setImageView(Integer imageView) {
        this.imageView = imageView;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}

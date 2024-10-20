package com.hcm.sale_laptop.data.model.other;

import java.util.List;

public class ProductObject {
    private List<ProductModel> product;

    public ProductObject(List<ProductModel> productModels) {
        this.product = productModels;
    }

    public List<ProductModel> getProductModels() {
        return product;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.product = productModels;
    }
}

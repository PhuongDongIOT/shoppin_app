package com.hcm.sale_laptop.data.model.other;

import java.util.List;

public class CategoryModel {

    List<BrandModel> category;

    public CategoryModel(List<BrandModel> category) {
        this.category = category;
    }

    public List<BrandModel> getCategory() {
        return category;
    }

    public void setCategory(List<BrandModel> category) {
        this.category = category;
    }
}

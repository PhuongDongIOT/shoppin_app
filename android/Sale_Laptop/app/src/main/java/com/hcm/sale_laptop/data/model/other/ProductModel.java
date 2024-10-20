package com.hcm.sale_laptop.data.model.other;

public class ProductModel {
    private String id;
    private String category_id;
    private String title;
    private String slug;
    private String picture;
    private String summary;
    private String description;
    private float price;
    private String created_by;

    public ProductModel(String id, String category_id, String title, String slug, String picture, String summary, String description, float price, String created_by) {
        this.id = id;
        this.category_id = category_id;
        this.title = title;
        this.slug = slug;
        this.picture = picture;
        this.summary = summary;
        this.description = description;
        this.price = price;
        this.created_by = created_by;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCreatedBy() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}

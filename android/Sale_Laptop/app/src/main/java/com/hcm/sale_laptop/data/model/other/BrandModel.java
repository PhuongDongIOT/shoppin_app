package com.hcm.sale_laptop.data.model.other;

public class BrandModel {
    private String name;
    private String parent_category;
    private String slug;
    private String description;
    private String id;
    private String imageUrl;

    public BrandModel(String name, String parent_category, String slug, String description, String id, String imageUrl) {
        this.name = name;
        this.parent_category = parent_category;
        this.slug = slug;
        this.description = description;
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_category() {
        return parent_category;
    }

    public void setParent_category(String parent_category) {
        this.parent_category = parent_category;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

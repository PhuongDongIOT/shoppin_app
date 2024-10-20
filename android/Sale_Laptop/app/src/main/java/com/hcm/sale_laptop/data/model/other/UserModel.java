package com.hcm.sale_laptop.data.model.other;

import com.hcm.sale_laptop.data.enums.RoleUser;
import com.hcm.sale_laptop.utils.Constants;

public class UserModel {
    private String name;
    private RoleUser roleUser;
    private String phoneNumber;
    private String address;
    private String id;
    private String email;
    private String role;
    private int age;
    private String created_at;
    private String updated_at;
    private String bio;
    private String company;
    private String slug;
    private String avatar;
    private String token;

    public UserModel() {

    }

    public UserModel(String name, String phoneNumber, String address, String id,
                     String email, String role, int age,
                     String created_at, String updated_at, String bio,
                     String company, String slug, String avatar, String token) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.id = id;
        this.email = email;
        this.role = role;
        this.age = age;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.bio = bio;
        this.company = company;
        this.slug = slug;
        this.avatar = avatar;
        this.token = token;
        setRoleUser(role);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRoleUser(String role) {
        if (role.equalsIgnoreCase(Constants.USER)) {
            roleUser = RoleUser.USER;
        } else {
            roleUser = RoleUser.ADMIN;
        }
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        setRoleUser(role);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

}

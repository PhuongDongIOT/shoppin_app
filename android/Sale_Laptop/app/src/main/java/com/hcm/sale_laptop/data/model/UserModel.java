package com.hcm.sale_laptop.data.model;

import com.hcm.sale_laptop.data.enums.RoleUser;
import com.hcm.sale_laptop.utils.Constants;

public class UserModel {
    private String name;
    private RoleUser roleUser;
    private String phoneNumber;
    private String address;
    private String id;
    private String email;

    public void setRoleUser(String role) {
        if (role.equalsIgnoreCase(Constants.USER)) {
            roleUser = RoleUser.USER;
        } else {
            roleUser = RoleUser.ADMIN;
        }
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

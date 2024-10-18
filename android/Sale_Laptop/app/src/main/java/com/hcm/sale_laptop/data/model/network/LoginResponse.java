package com.hcm.sale_laptop.data.model.network;

import com.hcm.base.BaseResponse;
import com.hcm.sale_laptop.data.model.UserModel;

public class LoginResponse extends BaseResponse<LoginResponse> {
    private String role;
    private UserModel user;
    private String token;

    public LoginResponse(boolean success, LoginResponse data, Object errors) {
        super(success, data, errors);
    }

    public LoginResponse(boolean success, LoginResponse data, Object errors, String role, UserModel user, String token) {
        super(success, data, errors);
        this.role = role;
        this.user = user;
        this.token = token;
        if (user != null) {
            user.setRoleUser(role);
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

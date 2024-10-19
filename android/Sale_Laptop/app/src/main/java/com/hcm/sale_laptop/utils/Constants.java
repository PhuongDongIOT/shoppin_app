package com.hcm.sale_laptop.utils;

import com.hcm.sale_laptop.data.model.other.UserModel;

public class Constants {
    private static UserModel userModel;
    private static String token;

    public static UserModel getUserModel() {
        if (userModel == null) {
            userModel = new UserModel();
        }
        return userModel;
    }

    public static void setUserModel(UserModel model) {
        userModel = model;
    }

    public static void setToken(String value) {
        token = value;
    }

    public static String getToken() {
        return token;
    }

    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String DATA_FORMAT = "dd/mm/yyyy";
}

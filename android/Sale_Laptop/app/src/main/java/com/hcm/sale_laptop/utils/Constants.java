package com.hcm.sale_laptop.utils;

import com.hcm.sale_laptop.data.model.UserModel;

public class Constants {
    private static UserModel userModel;

    public static UserModel getUserModel() {
        if (userModel == null) {
            userModel = new UserModel();
        }
        return userModel;
    }

    public static void setUserModel(UserModel model) {
        userModel = model;
    }

    public static final String USER = "user";
    public static final String ADMIN = "admin";

    public static final String DATA_FORMAT = "dd/mm/yyyy";
}

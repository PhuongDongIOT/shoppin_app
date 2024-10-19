package com.hcm.sale_laptop.data.model.network.response;

import com.hcm.base.BaseResponse;
import com.hcm.sale_laptop.data.model.other.UserModel;

public class LoginResponse extends BaseResponse<UserModel> {
    public LoginResponse(boolean success, UserModel data, Object errors) {
        super(success, data, errors);
    }
}

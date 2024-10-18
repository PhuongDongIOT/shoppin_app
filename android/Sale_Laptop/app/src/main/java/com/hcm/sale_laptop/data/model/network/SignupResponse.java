package com.hcm.sale_laptop.data.model.network;

import com.hcm.base.BaseResponse;

public class SignupResponse extends BaseResponse<SignupResponse> {
    public SignupResponse(boolean success, SignupResponse data, Object errors) {
        super(success, data, errors);
    }
}

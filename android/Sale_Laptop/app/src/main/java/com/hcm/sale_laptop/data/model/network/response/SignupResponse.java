package com.hcm.sale_laptop.data.model.network.response;

import com.hcm.base.BaseResponse;

public class SignupResponse extends BaseResponse<Object> {
    public SignupResponse(boolean success, Object data, Object errors) {
        super(success, data, errors);
    }
}

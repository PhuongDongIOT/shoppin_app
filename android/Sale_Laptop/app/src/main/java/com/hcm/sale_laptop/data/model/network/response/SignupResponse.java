package com.hcm.sale_laptop.data.model.network.response;

import com.hcm.base.BaseResponse;
import com.hcm.sale_laptop.data.model.network.request.SignupRequest;

public class SignupResponse extends BaseResponse<SignupRequest> {
    public SignupResponse(boolean success, SignupRequest data, Object errors) {
        super(success, data, errors);
    }
}

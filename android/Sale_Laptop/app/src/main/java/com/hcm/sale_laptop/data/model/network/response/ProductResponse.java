package com.hcm.sale_laptop.data.model.network.response;

import com.hcm.base.BaseResponse;
import com.hcm.sale_laptop.data.model.other.ProductObject;

public class ProductResponse extends BaseResponse<ProductObject> {
    public ProductResponse(boolean success, ProductObject data, Object errors) {
        super(success, data, errors);
    }
}

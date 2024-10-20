package com.hcm.sale_laptop.data.model.network.response;

import com.hcm.base.BaseResponse;
import com.hcm.sale_laptop.data.model.other.CategoryModel;

public class BrandResponse extends BaseResponse<CategoryModel> {
    public BrandResponse(boolean success, CategoryModel data, Object errors) {
        super(success, data, errors);
    }
}

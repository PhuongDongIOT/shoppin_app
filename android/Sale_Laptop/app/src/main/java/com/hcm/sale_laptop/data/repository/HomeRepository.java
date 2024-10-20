package com.hcm.sale_laptop.data.repository;

import com.hcm.base.BaseRepository;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.network.response.BrandResponse;
import com.hcm.sale_laptop.data.model.network.response.ProductResponse;

import io.reactivex.rxjava3.core.Single;

public class HomeRepository extends BaseRepository {

    private final ApiService apiService;

    public HomeRepository() {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    public Single<BrandResponse> getDataBrand() {
        return applySingle(apiService.getDataBrand());
    }

    public Single<ProductResponse> getDataProducts() {
        return applySingle(apiService.getDataProducts());
    }
}

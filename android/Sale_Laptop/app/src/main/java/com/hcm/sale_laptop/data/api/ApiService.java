package com.hcm.sale_laptop.data.api;

import com.hcm.sale_laptop.data.model.network.request.LoginRequest;
import com.hcm.sale_laptop.data.model.network.request.SignupRequest;
import com.hcm.sale_laptop.data.model.network.response.BrandResponse;
import com.hcm.sale_laptop.data.model.network.response.LoginResponse;
import com.hcm.sale_laptop.data.model.network.response.ProductResponse;
import com.hcm.sale_laptop.data.model.network.response.SignupResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST(EndPoint.LOGIN)
    Single<LoginResponse> login(@Body LoginRequest signupRequest);

    @POST(EndPoint.SIGNUP)
    Single<SignupResponse> signup(@Body SignupRequest signupRequest);

    @GET(EndPoint.ADMIN_CATEGORY)
    Single<BrandResponse> getDataBrand();

    @GET(EndPoint.ADMIN_PRODUCT)
    Single<ProductResponse> getDataProducts();
}

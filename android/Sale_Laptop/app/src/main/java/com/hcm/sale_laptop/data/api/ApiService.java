package com.hcm.sale_laptop.data.api;

import com.hcm.sale_laptop.data.model.network.LoginRequest;
import com.hcm.sale_laptop.data.model.network.LoginResponse;
import com.hcm.sale_laptop.data.model.network.SignupRequest;
import com.hcm.sale_laptop.data.model.network.SignupResponse;
import com.hcm.sale_laptop.utils.ApiEndPoint;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST(ApiEndPoint.LOGIN)
    Single<LoginResponse> login(@Body LoginRequest signupRequest);

    @POST(ApiEndPoint.SIGNUP)
    Single<SignupResponse> signup(@Body SignupRequest signupRequest);


}

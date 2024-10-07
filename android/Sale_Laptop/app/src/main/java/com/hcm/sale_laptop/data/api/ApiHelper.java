package com.hcm.sale_laptop.data.api;

import com.hcm.sale_laptop.data.model.network.ApiError;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiHelper {
    @GET("users/{id}")
    Single<ApiError> getUserById(@Path("id") int userId);
//    Single<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);
}

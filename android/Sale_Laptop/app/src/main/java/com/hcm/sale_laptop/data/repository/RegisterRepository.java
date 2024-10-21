package com.hcm.sale_laptop.data.repository;

import com.hcm.base.BaseRepository;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.network.request.SignupRequest;
import com.hcm.sale_laptop.data.model.network.response.SignupResponse;

import io.reactivex.rxjava3.core.Single;

public class RegisterRepository extends BaseRepository {

    private final ApiService apiService;

    public RegisterRepository() {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    public Single<SignupResponse> signupUser(SignupRequest request) {
        return applySingle(apiService.signupUser(request));
    }
}

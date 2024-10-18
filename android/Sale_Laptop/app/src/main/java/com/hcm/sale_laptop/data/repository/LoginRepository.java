package com.hcm.sale_laptop.data.repository;

import com.hcm.base.BaseRepository;
import com.hcm.sale_laptop.data.api.ApiService;
import com.hcm.sale_laptop.data.api.RetrofitClient;
import com.hcm.sale_laptop.data.model.network.LoginRequest;
import com.hcm.sale_laptop.data.model.network.LoginResponse;

import io.reactivex.rxjava3.core.Single;

public class LoginRepository extends BaseRepository {

    private final ApiService apiService;

    public LoginRepository() {
        apiService = RetrofitClient.getInstance().create(ApiService.class);
    }

    public Single<LoginResponse> login(LoginRequest loginRequest) {
        return applySingle(apiService.login(loginRequest));
    }

}

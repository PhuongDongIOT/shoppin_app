package com.hcm.sale_laptop.data.api;

import com.hcm.sale_laptop.utils.Constants;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Constants.BASE_URL + "/5926ce9d11000096006ccb30")
    Observable<Object> getExampleData();
}

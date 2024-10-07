package com.hcm.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApiClient {

    private static final String BASE_URL = "https://api.example.com/"; // Thay thế bằng URL của bạn
    private static Retrofit retrofit;

    // Singleton Retrofit instance
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Tạo Gson instance
            final Gson gson = new GsonBuilder()
                    .setStrictness(Strictness.LENIENT) // Sử dụng khi dữ liệu không chuẩn JSON
                    .create();

            // Tạo OkHttpClient với timeout
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();

            // Khởi tạo Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)) // Converter cho Gson
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Adapter cho RxJava
                    .build();

        }
        return retrofit;
    }
}



package com.hcm.sale_laptop.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    private static Retrofit retrofit;

    // Singleton Retrofit instance
    public static Retrofit getInstance() {
        if (retrofit == null) {
            // Tạo Gson instance
            final Gson gson = new GsonBuilder()
                    .setStrictness(Strictness.LENIENT) // Sử dụng khi dữ liệu không chuẩn JSON
                    .create();

            // Tạo interceptor để log thông tin request và response
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Log toàn bộ body

            // Tạo OkHttpClient với timeout
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
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



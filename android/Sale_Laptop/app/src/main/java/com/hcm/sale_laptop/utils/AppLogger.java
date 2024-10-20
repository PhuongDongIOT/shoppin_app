package com.hcm.sale_laptop.utils;

import android.util.Log;

public class AppLogger {

    private static final String DEFAULT_TAG = "AppLogger";  // Tag mặc định nếu không truyền tag

    // Debug Log
    public static void d(String tag, String message) {
        Log.d(tag, message);
    }

    // Overload: Debug Log với tag mặc định
    public static void d(String message) {
        d(DEFAULT_TAG, message);
    }

    // Info Log
    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    // Overload: Info Log với tag mặc định
    public static void i(String message) {
        i(DEFAULT_TAG, message);
    }

    // Error Log
    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    // Overload: Error Log với tag mặc định
    public static void e(String message) {
        e(DEFAULT_TAG, message);
    }

    // Warning Log
    public static void w(String tag, String message) {
        Log.w(tag, message);
    }

    // Overload: Warning Log với tag mặc định
    public static void w(String message) {
        w(DEFAULT_TAG, message);
    }

    // Log exception
    public static void e(String tag, String message, Throwable throwable) {
        Log.e(tag, message, throwable);
    }

    // Overload: Log exception với tag mặc định
    public static void e(String message, Throwable throwable) {
        e(DEFAULT_TAG, message, throwable);
    }

    // Method để log API request/response data
    public static void logApi(String tag, String url, String params, String response) {
        Log.d(tag, "API Request: " + url);
        Log.d(tag, "Params: " + params);
        Log.d(tag, "Response: " + response);
    }
}


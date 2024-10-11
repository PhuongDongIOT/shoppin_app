package com.hcm.sale_laptop.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPrefManager {
    private static final String PREF_NAME = "app_prefs"; // Tên file SharedPreferences
    public static final String KEY_ONBOARDING_COMPLETED = "ONBOARDING_COMPLETED";

    private static SharedPrefManager instance;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    // Constructor là private để đảm bảo rằng chỉ có một instance được tạo (Singleton pattern)
    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Đảm bảo chỉ tạo một instance duy nhất
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    // Lưu boolean
    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    // Lấy boolean
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    // Lưu String
    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    // Lấy String
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    // Lưu int
    public void saveInt(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    // Lấy int
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    // Lưu float
    public void saveFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.apply();
    }

    // Lấy float
    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    // Lưu long
    public void saveLong(String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    // Lấy long
    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    // Xóa dữ liệu với khóa cụ thể
    public void removeKey(String key) {
        editor.remove(key);
        editor.apply();
    }

    // Xóa toàn bộ dữ liệu trong SharedPreferences
    public void clear() {
        editor.clear();
        editor.apply();
    }

    // Lưu object dưới dạng JSON
    public void saveObject(String key, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        editor.putString(key, json);
        editor.apply();
    }

    // Lấy object từ JSON
    public <T> T getObject(String key, Class<T> classOfT) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, null);
        return gson.fromJson(json, classOfT);
    }
}


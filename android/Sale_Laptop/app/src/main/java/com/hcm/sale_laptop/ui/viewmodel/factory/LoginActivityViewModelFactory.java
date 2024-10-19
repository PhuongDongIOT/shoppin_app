package com.hcm.sale_laptop.ui.viewmodel.factory;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.sale_laptop.ui.viewmodel.LoginActivityViewModel;

public class LoginActivityViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;

    public LoginActivityViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginActivityViewModel.class)) {
            return (T) new LoginActivityViewModel(this.application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

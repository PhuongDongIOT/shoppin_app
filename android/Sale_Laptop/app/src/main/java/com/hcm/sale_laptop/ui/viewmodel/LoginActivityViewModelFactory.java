package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
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
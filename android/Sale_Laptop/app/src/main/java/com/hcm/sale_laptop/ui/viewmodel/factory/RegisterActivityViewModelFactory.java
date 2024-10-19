package com.hcm.sale_laptop.ui.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.sale_laptop.ui.viewmodel.RegisterActivityViewModel;

public class RegisterActivityViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public RegisterActivityViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterActivityViewModel.class)) {
            return (T) new RegisterActivityViewModel(this.application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ForgotPasswordActivityViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ForgotPasswordActivityViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ForgotPasswordActivityViewModel.class)) {
            return (T) new ForgotPasswordActivityViewModel(application);
        }
        throw new  IllegalArgumentException("Unknown ViewModel class");
    }
}

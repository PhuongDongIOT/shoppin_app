package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.hcm.base.BaseRepository;
import com.hcm.base.BaseViewModel;

public class MainActivityViewModel extends BaseViewModel<BaseRepository> {

//    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

    }

//    LiveData<LoginFormState> getLoginFormState() {
//        return loginFormState;
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
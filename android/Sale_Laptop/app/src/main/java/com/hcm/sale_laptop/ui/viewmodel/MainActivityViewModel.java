package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;

import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.network.LoginRequest;
import com.hcm.sale_laptop.data.repository.LoginRepository;
import com.hcm.sale_laptop.utils.AppUtils;
import com.hcm.sale_laptop.utils.Constants;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivityViewModel extends BaseViewModel {

//    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    MainActivityViewModel(@NonNull Application application) {
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
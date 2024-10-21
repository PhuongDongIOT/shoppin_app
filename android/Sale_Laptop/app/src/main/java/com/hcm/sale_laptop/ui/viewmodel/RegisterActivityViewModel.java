package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.network.request.SignupRequest;
import com.hcm.sale_laptop.data.model.network.response.SignupResponse;
import com.hcm.sale_laptop.data.repository.RegisterRepository;
import com.hcm.sale_laptop.utils.AppUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterActivityViewModel extends BaseViewModel<RegisterRepository> {

    private final MutableLiveData<Void> onSignUpSuccess = new MutableLiveData<>();

    public RegisterActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = new RegisterRepository();
    }

    public void registerUser(SignupRequest request) {
        setLoading(true);
        if (AppUtils.stringNullOrEmpty(request.getName())
                || AppUtils.stringNullOrEmpty(request.getEmail())) {
            setErrorMessage("Các trường Tên người dùng, Tài khoản không được để trống");
            return;
        }
        if (!isUserNameValid(request.getEmail())) {
            setErrorMessage(getStringResource(R.string.username_cannot_email));
            return;
        }
        if (request.getPassword().length() < 8) {
            setErrorMessage("Mật khẩu phải có ít nhất 8 ký tự");
            return;
        }
        final Disposable disposable = mRepository.signupUser(request)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(dis -> setLoading(true))
                .doOnError(error -> setLoading(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSignUpResponse, throwable -> setErrorMessage(throwable.getMessage())
                );
        addDisposable(disposable);
    }

    public LiveData<Void> onSignupSuccess() {
        return onSignUpSuccess;
    }

    private void handleSignUpResponse(SignupResponse response) {
        setLoading(false);
        if (response.isSuccess()) {
            setSuccessMessage("Đăng ký người dùng thành công");
            onSignUpSuccess.setValue(null);
        } else {
            setErrorMessage("Đăng ký người dùng thất bại");
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        }
        return Patterns.PHONE.matcher(username).matches();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

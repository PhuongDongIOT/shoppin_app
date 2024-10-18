package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.UserModel;
import com.hcm.sale_laptop.data.model.network.LoginRequest;
import com.hcm.sale_laptop.data.model.network.LoginResponse;
import com.hcm.sale_laptop.data.repository.LoginRepository;
import com.hcm.sale_laptop.utils.AppUtils;
import com.hcm.sale_laptop.utils.Constants;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivityViewModel extends BaseViewModel {

    private final LoginRepository repository;
    private final MutableLiveData<UserModel> userModel = new MutableLiveData<>();

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository();
    }

    public void login(String username, String password) {
        if (AppUtils.checkStringNullOrEmpty(username)) {
            setErrorMessage(getStringResource(R.string.username_cannot_be_empty));
            return;
        }
        if (AppUtils.checkStringNullOrEmpty(password) || !isPasswordValid(password)) {
            setErrorMessage(getStringResource(R.string.password_cannot_be_empty));
            return;
        }
        if (!isUserNameValid(username)) {
            setErrorMessage(getStringResource(R.string.username_cannot_email));
            return;
        }
        if (!username.contains("@") && (username.length() < 9 || username.length() > 11)) {
            setErrorMessage(getStringResource(R.string.username_cannot_email));
            return;
        }
        final Disposable disposable = repository.login(new LoginRequest(username, password))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(dis -> setLoading(true))
                .doOnError(error -> setLoading(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoginResponse, throwable -> {
                    setErrorMessage("Có lỗi khi kết nối với máy chủ");
                   }
                );
        addDisposable(disposable);
    }

    private void handleLoginResponse(LoginResponse response) {
        setLoading(false);
        if (!response.isSuccess()) {
            setErrorMessage("Đăng nhập không thành công");
            return;
        }
        final UserModel model = response.getUser();
        if (model == null) {
            setErrorMessage("Đăng nhập không thành công");
            return;
        }
        Constants.setUserModel(model);
        userModel.setValue(model);
    }

    public LiveData<UserModel> loginSuccess() {
        return userModel;
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        }
        return Patterns.PHONE.matcher(username).matches();
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 4;
    }
}

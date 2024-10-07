package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.ui.login.LoginFormState;
import com.hcm.sale_laptop.utils.AppUtils;

public class LoginActivityViewModel extends BaseViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
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
        return password != null && password.trim().length() > 5;
    }
}
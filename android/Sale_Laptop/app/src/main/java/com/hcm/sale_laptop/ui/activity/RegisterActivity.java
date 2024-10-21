package com.hcm.sale_laptop.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.local.prefs.KeyPref;
import com.hcm.sale_laptop.data.local.prefs.SharedPrefManager;
import com.hcm.sale_laptop.data.model.network.request.SignupRequest;
import com.hcm.sale_laptop.databinding.ActivityRegisterBinding;
import com.hcm.sale_laptop.ui.viewmodel.RegisterActivityViewModel;
import com.hcm.sale_laptop.ui.viewmodel.factory.RegisterActivityViewModelFactory;
import com.hcm.sale_laptop.utils.AppUtils;
import com.hcm.sale_laptop.utils.Constants;

public class RegisterActivity extends BaseActivity<RegisterActivityViewModel, ActivityRegisterBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setup();
    }

    @Override
    protected void setupUI() {
        mBinding.btnRegister.setActivated(true);
        configTextView();
        final Drawable offIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_off_eye_24, null);
        final Drawable onIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_on_eye_24, null);
        configEditTextPassword(mBinding.edtPassword, offIcon, onIcon);
        configEditTextPassword(mBinding.edtRePassword, offIcon, onIcon);
    }

    @Override
    protected void setupAction() {
        setOnClickListener(mBinding.btnRegister, view -> {
            final SignupRequest request = new SignupRequest();
            request.setName(mBinding.edtAccountName.getText().toString());
            request.setEmail(mBinding.edtAccount.getText().toString());
            request.setRole(Constants.USER);
            request.setAddress(mBinding.edtAddress.getText().toString());
            request.setPhoneNumber(mBinding.edtPhoneNumber.getText().toString());
            final String rePassword = mBinding.edtRePassword.getText().toString();
            final String password = mBinding.edtPassword.getText().toString();
            if (AppUtils.stringNullOrEmpty(rePassword) || AppUtils.stringNullOrEmpty(password)) {
                showToast("Các trường mật khẩu không được để trống ");
                return;
            }
            if (!password.equals(rePassword)) {
                showToast("Xác thực mật khẩu đang không trùng nhau");
                return;
            }
            request.setPassword(password);
            mViewModel.registerUser(request);
        });
    }

    @Override
    protected void setupData() {
        mViewModel = new ViewModelProvider(this, new RegisterActivityViewModelFactory(getApplication()))
                .get(RegisterActivityViewModel.class);
        mViewModel.errorMessage.observe(this, this::showToast);
        mViewModel.successMessage.observe(this, this::showToast);
        mViewModel.onSignupSuccess().observe(this, unused -> {
            if (mBinding.cbRememberAccount.isChecked()) {
                final SharedPrefManager shared = SharedPrefManager.getInstance(this);
                saveData(shared);
            }
        });
    }

    private void configTextView() {
        // config for txtNotYetAccount
        final String haveAccount = getString(R.string.you_already_have_a_login_account);
        final String login = getString(R.string.login);
        final SpannableString spannableString = new SpannableString(haveAccount);
        setUpSpannableString(spannableString, haveAccount, login, R.color.blue_sky, LoginActivity.class);
        mBinding.txtHaveAccount.setText(spannableString);
        mBinding.txtHaveAccount.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void saveData(SharedPrefManager shared) {
        final String userName = mBinding.edtAccount.getText().toString();
        final String userPassword = mBinding.edtPassword.getText().toString();
        shared.saveBoolean(KeyPref.KEY_REMEMBER_ACCOUNT, true);
        if (!AppUtils.stringNullOrEmpty(userName)) {
            shared.saveString(KeyPref.KEY_USER_NAME, userName);
        }
        if (!AppUtils.stringNullOrEmpty(userPassword)) {
            shared.saveString(KeyPref.KEY_USER_PASSWORD, userPassword);
        }
    }
}
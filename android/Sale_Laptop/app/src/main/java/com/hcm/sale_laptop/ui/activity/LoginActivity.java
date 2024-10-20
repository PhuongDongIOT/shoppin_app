package com.hcm.sale_laptop.ui.activity;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.enums.RoleUser;
import com.hcm.sale_laptop.data.local.prefs.KeyPref;
import com.hcm.sale_laptop.data.local.prefs.SharedPrefManager;
import com.hcm.sale_laptop.databinding.ActivityLoginBinding;
import com.hcm.sale_laptop.ui.viewmodel.LoginActivityViewModel;
import com.hcm.sale_laptop.ui.viewmodel.factory.LoginActivityViewModelFactory;
import com.hcm.sale_laptop.utils.AppUtils;

public class LoginActivity extends BaseActivity<LoginActivityViewModel, ActivityLoginBinding> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        setup();
    }


    @Override
    protected void setupAction() {

        setOnClickListener(mBinding.btnLogin, view -> mViewModel.login(mBinding.editUserName.getText().toString(),
                mBinding.editPassword.getText().toString()));

        setOnClickListener(mBinding.txtForgotPassword, view -> navigateTo(ForgotPasswordActivity.class));
    }

    @Override
    protected void setupUI() {
        final SharedPrefManager shared = SharedPrefManager.getInstance(this);
        final boolean isRememberAccount = shared.getBoolean(KeyPref.KEY_REMEMBER_ACCOUNT, false);
        mBinding.cbRememberAccount.setChecked(isRememberAccount);
        configTextView();
        configEditText(shared);
    }

    @Override
    protected void setupData() {
        mViewModel = new ViewModelProvider(this, new LoginActivityViewModelFactory(getApplication()))
                .get(LoginActivityViewModel.class);
        mViewModel.errorMessage.observe(this, this::showToast);
        mViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });
        mViewModel.loginSuccess().observe(this, model -> {
            final SharedPrefManager shared = SharedPrefManager.getInstance(this);
            if (mBinding.cbRememberAccount.isChecked()) {
                saveData(shared);
            }
            if (model.getRoleUser() == RoleUser.USER) {
                final boolean onboardCompleted = shared.getBoolean(KeyPref.KEY_ONBOARDING_COMPLETED, false);
                final Class<?> targetActivity = onboardCompleted ? MainActivity.class : OnboardingActivity.class;
                navigateTo(targetActivity);
                finishActivity();
            }

//            finishActivity();
        });

    }

    private void saveData(SharedPrefManager shared) {
        final String userName = mBinding.editUserName.getText().toString();
        final String userPassword = mBinding.editPassword.getText().toString();
        shared.saveBoolean(KeyPref.KEY_REMEMBER_ACCOUNT, true);
        if (!AppUtils.stringNullOrEmpty(userName)) {
            shared.saveString(KeyPref.KEY_USER_NAME, userName);
        }
        if (!AppUtils.stringNullOrEmpty(userPassword)) {
            shared.saveString(KeyPref.KEY_USER_PASSWORD, userPassword);
        }
    }

    private void configEditText(SharedPrefManager sharedPrefManager) {
        final Drawable offIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_off_eye_24, null);
        final Drawable onIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_on_eye_24, null);
        configEditTextPassword(mBinding.editPassword, offIcon, onIcon);
        final String userName = sharedPrefManager.getString(KeyPref.KEY_USER_NAME, "");
        final String userPassword = sharedPrefManager.getString(KeyPref.KEY_USER_NAME, "");
        if (!AppUtils.stringNullOrEmpty(userName)) {
            mBinding.editUserName.setText(userName);
        }
        if (!AppUtils.stringNullOrEmpty(userName)) {
            mBinding.editPassword.setText(userPassword);
        }
        mBinding.editUserName.setText("adminad@gmail.com");
        mBinding.editPassword.setText("admin@6666");
    }

    private void configTextView() {
        // config for txtNotYetAccount
        final String notAccount = getString(R.string.you_do_not_have_an_account_yet);
        final String register = getString(R.string.register);
        final SpannableString spannableString = new SpannableString(notAccount);
        setUpSpannableString(spannableString, notAccount, register, R.color.blue_sea, RegisterActivity.class);
        mBinding.txtNotYetAccount.setText(spannableString);
        mBinding.txtNotYetAccount.setMovementMethod(LinkMovementMethod.getInstance());

        // config for txtNotYetAccount
        mBinding.txtForgotPassword.setPaintFlags(mBinding.txtForgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

}
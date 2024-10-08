package com.hcm.sale_laptop.ui.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;

import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.databinding.ActivityForgotPasswordBinding;
import com.hcm.sale_laptop.ui.viewmodel.ForgotPasswordActivityViewModel;
import com.hcm.sale_laptop.ui.viewmodel.ForgotPasswordActivityViewModelFactory;

public class ForgotPasswordActivity extends BaseActivity<ForgotPasswordActivityViewModel> {

    ActivityForgotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    @Override
    protected void setupUI() {
        final String registerOrLogin = getString(R.string.register_or_login);
        final String register = getString(R.string.register);
        final String login = getString(R.string.login);
        final SpannableString spannableString = new SpannableString(registerOrLogin);
        setUpSpannableString(spannableString, registerOrLogin, register, R.color.blue_sea, RegisterActivity.class);
        setUpSpannableString(spannableString, registerOrLogin, login, R.color.blue_sky, LoginActivity.class);
        binding.txtRegisterOrLogin.setText(spannableString);
        binding.txtRegisterOrLogin.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected void setupAction() {

    }

    @Override
    protected void setupData() {
        mViewModel = new ViewModelProvider(this, new ForgotPasswordActivityViewModelFactory(getApplication()))
                .get(ForgotPasswordActivityViewModel.class);
    }

}
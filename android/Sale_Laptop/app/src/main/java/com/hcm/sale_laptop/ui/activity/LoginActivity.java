package com.hcm.sale_laptop.ui.activity;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.databinding.ActivityLoginBinding;
import com.hcm.sale_laptop.ui.viewmodel.LoginActivityViewModel;
import com.hcm.sale_laptop.ui.viewmodel.LoginActivityViewModelFactory;

public class LoginActivity extends BaseActivity<LoginActivityViewModel> {

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }


    @Override
    protected void setupAction() {
        setOnClickListener(binding.btnLogin, view -> {
//            showProgressBar();
//            mViewModel.login(binding.editUserName.getText().toString(),
//                    binding.editPassword.getText().toString());
            navigateTo(OnboardingActivity.class);
            finishActivity();
        });
        setOnClickListener(binding.txtForgotPassword, view -> navigateTo(ForgotPasswordActivity.class));
    }

    @Override
    protected void setupUI() {
        configTextView();
        configEditText();
    }

    @Override
    protected void setupData() {
        mViewModel = new ViewModelProvider(this, new LoginActivityViewModelFactory(getApplication()))
                .get(LoginActivityViewModel.class);
        mViewModel.errorMessage.observe(this, s -> {
            hideProgressBar();
            showToast(s);
        });
    }

    private void configEditText() {
        final Drawable offIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_off_eye_24, null);
        final Drawable onIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_on_eye_24, null);
        configEditTextPassword(binding.editPassword, offIcon, onIcon);
    }

    private void configTextView() {
        // config for txtNotYetAccount
        final String notAccount = getString(R.string.you_do_not_have_an_account_yet);
        final String register = getString(R.string.register);
        final SpannableString spannableString = new SpannableString(notAccount);
        setUpSpannableString(spannableString, notAccount, register, R.color.blue_sea, RegisterActivity.class);
        binding.txtNotYetAccount.setText(spannableString);
        binding.txtNotYetAccount.setMovementMethod(LinkMovementMethod.getInstance());

        // config for txtNotYetAccount
        binding.txtForgotPassword.setPaintFlags(binding.txtForgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

}
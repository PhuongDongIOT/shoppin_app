package com.hcm.sale_laptop.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.databinding.ActivityRegisterBinding;
import com.hcm.sale_laptop.ui.viewmodel.RegisterActivityViewModel;
import com.hcm.sale_laptop.ui.viewmodel.RegisterActivityViewModelFactory;

public class RegisterActivity extends BaseActivity<RegisterActivityViewModel> {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }

    @Override
    protected void setupUI() {
        binding.btnRegister.setActivated(true);
        configTextView();
        final Drawable offIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_off_eye_24, null);
        final Drawable onIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_on_eye_24, null);
        configEditTextPassword(binding.editPassword, offIcon, onIcon);
        configEditTextPassword(binding.editRePassword, offIcon, onIcon);
    }

    @Override
    protected void setupAction() {

    }

    @Override
    protected void setupData() {
        mViewModel = new ViewModelProvider(this, new RegisterActivityViewModelFactory(getApplication()))
                .get(RegisterActivityViewModel.class);

    }

    private void configTextView() {
        // config for txtNotYetAccount
        final String haveAccount = getString(R.string.you_already_have_a_login_account);
        final String login = getString(R.string.login);
        final SpannableString spannableString = new SpannableString(haveAccount);
        setUpSpannableString(spannableString, haveAccount, login, R.color.blue_sky, LoginActivity.class);
        binding.txtHaveAccount.setText(spannableString);
        binding.txtHaveAccount.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
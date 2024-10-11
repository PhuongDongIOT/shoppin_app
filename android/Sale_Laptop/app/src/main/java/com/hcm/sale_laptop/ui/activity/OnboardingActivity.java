package com.hcm.sale_laptop.ui.activity;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.hcm.base.BaseActivity;
import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.data.local.prefs.SharedPrefManager;
import com.hcm.sale_laptop.databinding.ActivityOnboardingBinding;
import com.hcm.sale_laptop.ui.adapter.ViewPagerOnboardingAdapter;

import java.util.Objects;

public class OnboardingActivity extends BaseActivity<OnboardingActivityViewModel> {

    private ActivityOnboardingBinding binding;
    private int mPageIndex = 0;
    private int mItemCount = 0;

    @Override
    protected void setupUI() {
        binding.vp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.vp.setAdapter(new ViewPagerOnboardingAdapter());
        mItemCount = Objects.requireNonNull(binding.vp.getAdapter()).getItemCount();
        mPageIndex = binding.vp.getCurrentItem();
        binding.wormDotsIndicator.attachTo(binding.vp);
    }

    @Override
    protected void setupAction() {
        setOnClickListener(binding.btnNext, view -> {
            mPageIndex += 1;
            if (mPageIndex >= mItemCount) {
                mPageIndex = mItemCount - 1;
                pushToHomeActivity();
                return;
            }
            binding.vp.setCurrentItem(mPageIndex, true);
        });

        setOnClickListener(binding.btnPrevious, view -> {
            mPageIndex -= 1;
            if (mPageIndex < 0) {
                mPageIndex = 0;
                return;
            }
            binding.vp.setCurrentItem(mPageIndex, true);
        });

        setOnClickListener(binding.txtSkip, view -> pushToHomeActivity());
        binding.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mPageIndex = position;
            }
        });
    }

    private void pushToHomeActivity() {
        SharedPrefManager.getInstance(this).saveBoolean(SharedPrefManager.KEY_ONBOARDING_COMPLETED, true);
        navigateTo(HomeActivity.class);
    }

    @Override
    protected void setupData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setup();
    }
}

class OnboardingActivityViewModel extends BaseViewModel {
    public OnboardingActivityViewModel(@NonNull Application application) {
        super(application);
    }
}
package com.hcm.sale_laptop.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.databinding.ActivityMainBinding;
import com.hcm.sale_laptop.ui.fragment.AccountFragment;
import com.hcm.sale_laptop.ui.fragment.HomeFragment;
import com.hcm.sale_laptop.ui.fragment.InfoFragment;
import com.hcm.sale_laptop.ui.fragment.OrdersFragment;
import com.hcm.sale_laptop.ui.viewmodel.HomeActivityViewModel;

public class MainActivity extends BaseActivity<HomeActivityViewModel> {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Load default fragment
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
        setup();
    }

    @Override
    protected void setupUI() {

    }

    @Override
    protected void setupAction() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            final int id = item.getItemId();
            if (id == R.id.nav_home) {
                fragment = new HomeFragment();
            }
            if (id == R.id.nav_info) {
                fragment = new InfoFragment();
            }
            if (id == R.id.nav_orders) {
                fragment = new OrdersFragment();
            }
            if (id == R.id.nav_account) {
                fragment = new AccountFragment();
            }
            loadFragment(fragment);
            return true;
        });
    }

    @Override
    protected void setupData() {

    }

    // Phương thức để load fragment
    private void loadFragment(Fragment fragment) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
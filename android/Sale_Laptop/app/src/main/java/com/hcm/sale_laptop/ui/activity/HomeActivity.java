package com.hcm.sale_laptop.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.hcm.base.BaseActivity;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.databinding.ActivityHomeBinding;
import com.hcm.sale_laptop.ui.fragment.AccountFragment;
import com.hcm.sale_laptop.ui.fragment.HomeFragment;
import com.hcm.sale_laptop.ui.fragment.InfoFragment;
import com.hcm.sale_laptop.ui.fragment.OrdersFragment;
import com.hcm.sale_laptop.ui.viewmodel.HomeActivityViewModel;

public class HomeActivity extends BaseActivity<HomeActivityViewModel> {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
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
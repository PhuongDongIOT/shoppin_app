package com.hcm.sale_laptop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcm.base.BaseFragment;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.BrandModel;
import com.hcm.sale_laptop.databinding.FragmentHomeBinding;
import com.hcm.sale_laptop.ui.adapter.BrandAdapter;
import com.hcm.sale_laptop.ui.viewmodel.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeFragmentViewModel> {

    FragmentHomeBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setupUI() {
        final List<BrandModel> brandModelList = new ArrayList<>();
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        BrandAdapter adapter = new BrandAdapter(brandModelList, requireContext());
        binding.rvBrand.setAdapter(adapter);
    }

    @Override
    protected void setupAction() {


    }

    @Override
    protected void setupData() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }
}
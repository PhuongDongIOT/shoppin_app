package com.hcm.sale_laptop.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.hcm.base.BaseFragment;
import com.hcm.sale_laptop.R;
import com.hcm.sale_laptop.data.model.other.BrandModel;
import com.hcm.sale_laptop.data.model.other.DiscountedProductModel;
import com.hcm.sale_laptop.data.model.other.PopularProductModel;
import com.hcm.sale_laptop.databinding.FragmentHomeBinding;
import com.hcm.sale_laptop.ui.adapter.BrandAdapter;
import com.hcm.sale_laptop.ui.adapter.DiscountedProductAdapter;
import com.hcm.sale_laptop.ui.adapter.PopularProductAdapter;
import com.hcm.sale_laptop.ui.adapter.ViewPagerBannerAdapter;
import com.hcm.sale_laptop.ui.viewmodel.HomeFragmentViewModel;
import com.hcm.sale_laptop.utils.AppLogger;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class HomeFragment extends BaseFragment<HomeFragmentViewModel> {

    FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupUI() {
        setupRVBrand();
        setupRVDiscountedProduct();
        setupRVPopularProduct();
        setupVPBanner();
    }

    private void setupVPBanner() {
        binding.vpBanner.setAdapter(new ViewPagerBannerAdapter(new ArrayList<>(), null));
        binding.wormDotsIndicator.attachTo(binding.vpBanner);
    }

    private void setupRVPopularProduct() {
        final List<PopularProductModel> popularProductModels = new ArrayList<>();
        final PopularProductAdapter popularProductAdapter = new PopularProductAdapter(popularProductModels, this::onClickPopularProduct);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        binding.rvPopularProduct.setLayoutManager(gridLayoutManager);
        binding.rvPopularProduct.setAdapter(popularProductAdapter);
    }

    private void setupRVDiscountedProduct() {
        final List<DiscountedProductModel> discountedProductModels = new ArrayList<>();
        final DiscountedProductAdapter discountedProductAdapter = new DiscountedProductAdapter(discountedProductModels, this::onClickDiscountedProduct);
        binding.rvDiscountedProduct.setAdapter(discountedProductAdapter);
    }

    private void setupRVBrand() {
        final List<BrandModel> brandModelList = new ArrayList<>();
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        brandModelList.add(new BrandModel(R.drawable.ic_logo, "Apple"));
        final BrandAdapter brandAdapter = new BrandAdapter(brandModelList, this::onClickBrand);
        binding.rvBrand.setAdapter(brandAdapter);

    }

    private void onClickPopularProduct(PopularProductModel object) {
        AppLogger.d("onClickPopularProduct : " , object.getProductName());
    }

    private void onClickDiscountedProduct(DiscountedProductModel object) {
        AppLogger.d("onClickPopularProduct : " , object.getProductName());
    }

    private void onClickBrand(BrandModel object) {
        AppLogger.d("onClickPopularProduct : " , object.getBrandName());
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
        return binding.getRoot().getId();
    }

}
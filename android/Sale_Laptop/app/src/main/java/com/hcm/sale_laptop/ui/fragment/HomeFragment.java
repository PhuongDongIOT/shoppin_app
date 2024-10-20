package com.hcm.sale_laptop.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hcm.base.BaseFragment;
import com.hcm.sale_laptop.data.model.other.BrandModel;
import com.hcm.sale_laptop.data.model.other.DiscountedProductModel;
import com.hcm.sale_laptop.data.model.other.ProductModel;
import com.hcm.sale_laptop.databinding.FragmentHomeBinding;
import com.hcm.sale_laptop.ui.adapter.BrandAdapter;
import com.hcm.sale_laptop.ui.adapter.DiscountedProductAdapter;
import com.hcm.sale_laptop.ui.adapter.PopularProductAdapter;
import com.hcm.sale_laptop.ui.adapter.ViewPagerBannerAdapter;
import com.hcm.sale_laptop.ui.viewmodel.HomeFragmentViewModel;
import com.hcm.sale_laptop.ui.viewmodel.factory.HomeFragmentViewModelFactory;
import com.hcm.sale_laptop.utils.AppLogger;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeFragmentViewModel, FragmentHomeBinding> {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup();
    }

    @Override
    protected void setupUI() {
        setupRVBrand();
        setupRVDiscountedProduct();
        setupRVPopularProduct();
        setupVPBanner();
    }

    private void setupVPBanner() {
        mBinding.vpBanner.setAdapter(new ViewPagerBannerAdapter(new ArrayList<>(), null));
        mBinding.wormDotsIndicator.attachTo(mBinding.vpBanner);
    }

    private void setupRVPopularProduct() {
        final List<ProductModel> popularProductModels = new ArrayList<>();
        final PopularProductAdapter popularProductAdapter = new PopularProductAdapter(popularProductModels, this::onClickPopularProduct);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        mBinding.rvPopularProduct.setLayoutManager(gridLayoutManager);
        mBinding.rvPopularProduct.setAdapter(popularProductAdapter);
    }

    private void setupRVDiscountedProduct() {
        final List<DiscountedProductModel> discountedProductModels = new ArrayList<>();
        final DiscountedProductAdapter discountedProductAdapter = new DiscountedProductAdapter(discountedProductModels, this::onClickDiscountedProduct);
        mBinding.rvDiscountedProduct.setAdapter(discountedProductAdapter);
    }

    private void setupRVBrand() {
        final List<BrandModel> brandModelList = new ArrayList<>();
        final BrandAdapter brandAdapter = new BrandAdapter(brandModelList, this::onClickBrand);
        mBinding.rvBrand.setAdapter(brandAdapter);
    }

    private void onClickPopularProduct(ProductModel object) {
        AppLogger.d("onClickPopularProduct : ", object.getTitle());
    }

    private void onClickDiscountedProduct(DiscountedProductModel object) {
        AppLogger.d("onClickPopularProduct : ", object.getProductName());
    }

    private void onClickBrand(BrandModel object) {
        AppLogger.d("onClickPopularProduct : ", object.getName());
    }

    @Override
    protected void setupAction() {

    }

    @Override
    protected void setupData() {
        mViewModel = new ViewModelProvider(this, new HomeFragmentViewModelFactory(requireActivity().getApplication()))
                .get(HomeFragmentViewModel.class);
        mViewModel.fetch();
        mViewModel.errorMessage.observe(this, this::showToast);
        mViewModel.isLoading.observe(this, isLoading -> {
            if (isLoading) {
                showProgressBar();
            } else {
                hideProgressBar();
            }
        });

        mViewModel.getBrandModels().observe(this, brandModels -> {
            final BrandAdapter adapter = (BrandAdapter) mBinding.rvBrand.getAdapter();
            if (adapter != null) {
                adapter.setItems(brandModels);
            }
        });

        mViewModel.getProductModels().observe(this, productModels -> {
            final PopularProductAdapter adapter = (PopularProductAdapter) mBinding.rvPopularProduct.getAdapter();
            if (adapter != null) {
                adapter.setItems(productModels);
            }
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return mBinding.getRoot().getId();
    }

}
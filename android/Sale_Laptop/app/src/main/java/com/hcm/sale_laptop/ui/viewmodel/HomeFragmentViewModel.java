package com.hcm.sale_laptop.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hcm.base.BaseViewModel;
import com.hcm.sale_laptop.data.model.network.response.BrandResponse;
import com.hcm.sale_laptop.data.model.network.response.ProductResponse;
import com.hcm.sale_laptop.data.model.other.BrandModel;
import com.hcm.sale_laptop.data.model.other.CategoryModel;
import com.hcm.sale_laptop.data.model.other.ProductModel;
import com.hcm.sale_laptop.data.model.other.ProductObject;
import com.hcm.sale_laptop.data.repository.HomeRepository;
import com.hcm.sale_laptop.utils.AppUtils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragmentViewModel extends BaseViewModel {

    private final HomeRepository repository;
    private final MutableLiveData<List<BrandModel>> brandModels = new MutableLiveData<>();
    private final MutableLiveData<List<ProductModel>> productModels = new MutableLiveData<>();

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new HomeRepository();
    }

    public void fetch() {
        setLoading(true);
        getDataBrand();
        getDataProducts();
    }

    public LiveData<List<BrandModel>> getBrandModels() {
        return brandModels;
    }

    public LiveData<List<ProductModel>> getProductModels() {
        return productModels;
    }

    private void getDataProducts() {
        final Disposable disposable = repository.getDataProducts()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(dis -> setLoading(true))
                .doOnError(error -> setLoading(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handlerProductResponse, throwable -> setErrorMessage(throwable.getMessage())
                );
        addDisposable(disposable);
    }

    private void getDataBrand() {
        final Disposable disposable = repository.getDataBrand()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(dis -> setLoading(true))
                .doOnError(error -> setLoading(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handlerBrandResponse, throwable -> setErrorMessage(throwable.getMessage())
                );
        addDisposable(disposable);
    }

    private void handlerProductResponse(ProductResponse response) {
        setLoading(false);
        final ProductObject object = response.getData();
        if (!response.isSuccess() || object == null) {
            setErrorMessage("Lỗi load danh sách sản phẩm");
        }

        final List<ProductModel> productModels = object != null ? object.getProductModels() : null;
        if (!AppUtils.checkListHasData(productModels)) {
            setErrorMessage("Lỗi load danh sách sản phẩm");
            return;
        }

        this.productModels.setValue(productModels);
    }

    private void handlerBrandResponse(BrandResponse response) {
        setLoading(false);
        final CategoryModel categoryModel = response.getData();
        if (!response.isSuccess() || categoryModel == null) {
            setErrorMessage("Lỗi load các thương hiệu");
        }

        final List<BrandModel> brandModels = categoryModel != null ? categoryModel.getCategory() : null;
        if (!AppUtils.checkListHasData(brandModels)) {
            setErrorMessage("Lỗi load các thương hiệu");
            return;
        }

        this.brandModels.setValue(brandModels);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

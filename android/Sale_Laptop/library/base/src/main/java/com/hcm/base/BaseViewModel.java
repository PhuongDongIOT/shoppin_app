package com.hcm.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseViewModel<R extends BaseRepository> extends AndroidViewModel {

    // Quản lý các Disposable từ RxJava
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    // LiveData cho trạng thái loading
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(false);
    public LiveData<Boolean> isLoading = _isLoading;

    // LiveData cho thông báo lỗi
    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    // LiveData cho thông báo Thành công
    private final MutableLiveData<String> _successMessage = new MutableLiveData<>();
    public LiveData<String> successMessage = _successMessage;

    protected R mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Phương thức để quản lý Disposable
     *
     * @param disposable Disposable cần được thêm vào CompositeDisposable.
     */
    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    /**
     * Phương thức để đặt trạng thái loading.
     *
     * @param isLoading true nếu đang loading, ngược lại false.
     */
    protected void setLoading(boolean isLoading) {
        _isLoading.setValue(isLoading);
    }

    /**
     * Phương thức để đặt thông báo lỗi.
     *
     * @param errorMessage Thông báo lỗi cần hiển thị.
     */
    protected void setErrorMessage(String errorMessage) {
        _errorMessage.setValue(errorMessage);
    }

    protected void setSuccessMessage(String successMessage) {
        _successMessage.setValue(successMessage);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Giải phóng tất cả các Disposable khi ViewModel bị hủy
        compositeDisposable.clear();
    }

    protected String getStringResource(int resId) {
        return getApplication().getString(resId);
    }
}


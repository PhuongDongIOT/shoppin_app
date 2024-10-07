package com.hcm.base;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BaseRepository {

    // CompositeDisposable để quản lý các request RxJava
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Thêm Disposable vào CompositeDisposable
     *
     * @param disposable Disposable của request
     */
    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    /**
     * Xử lý các request với RxJava.
     *
     * @param single Single trả về từ API
     * @param <T>    Kiểu dữ liệu trả về
     * @return Single đã xử lý schedulers
     */
    protected <T> Single<T> applySchedulers(Single<T> single) {
        return single.subscribeOn(Schedulers.io()) // Request trên IO Thread
                .observeOn(AndroidSchedulers.mainThread()); // Nhận kết quả trên Main Thread
    }

    /**
     * Hủy tất cả các request khi không còn cần thiết
     */
    public void clearDisposables() {
        compositeDisposable.clear();
    }
}


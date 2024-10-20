package com.hcm.base;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BaseRepository {

    protected <T> Single<T> applySingle(Single<T> single) {
        return single
                .subscribeOn(Schedulers.io()) // Request trên IO Thread
                .observeOn(AndroidSchedulers.mainThread()); // Nhận kết quả trên Main Thread
    }

    protected <T> Observable<T> applyObservable(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io()) // Request trên IO Thread
                .observeOn(AndroidSchedulers.mainThread()); // Nhận kết quả trên Main Thread
    }

}


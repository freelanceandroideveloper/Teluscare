package com.teluscare.android.viewmodel;

import com.teluscare.android.model.BaseResponseBean;
import com.teluscare.android.network.NetworkServicesImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SandeepY on 18112019
 **/


public class TeluscareViewModel {

    private Disposable disposable;

    private NetworkServicesImpl services;

    public TeluscareViewModel() {
        services = new NetworkServicesImpl();
    }

    public Disposable login(String strUsername, String strPassword, final Consumer<BaseResponseBean> response, final Consumer<Throwable> error) {
        disposable = services.login(strUsername,strPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<BaseResponseBean>() {
                    @Override
                    public void accept(BaseResponseBean allPostResponseBean) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        error.accept(throwable);
                    }
                });
        return disposable;
    }
}

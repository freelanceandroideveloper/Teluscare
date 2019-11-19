package com.teluscare.android.viewmodel;

import com.teluscare.android.model.BaseResponseBean;
import com.teluscare.android.model.LoginResponseBean;
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

    public Disposable login(String strUsername, String strPassword, String strUserType, final Consumer<LoginResponseBean> response, final Consumer<Throwable> error) {
        disposable = services.login(strUsername,strPassword,strUserType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<LoginResponseBean>() {
                    @Override
                    public void accept(LoginResponseBean loginResponseBean) throws Exception {
                        if(!loginResponseBean.getStatus().equalsIgnoreCase("true")){
                            error.accept(new Throwable(loginResponseBean.getError()));
                            return;
                        }
                        response.accept(loginResponseBean);
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

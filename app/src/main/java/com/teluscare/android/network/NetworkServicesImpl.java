package com.teluscare.android.network;

import com.teluscare.android.model.ForgotPasswordResponseBean;
import com.teluscare.android.model.LoginResponseBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by SandeepY on 18112019
 **/


public class NetworkServicesImpl {

    private NetworkServices networkServices;

    public NetworkServicesImpl(){
        networkServices=RestClient.getClient().create(NetworkServices.class);
    }

    public Observable<LoginResponseBean> login(String strUsername, String strPassword, String userType) {
        return networkServices.login(strUsername,strPassword,userType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ForgotPasswordResponseBean> forgotPassword(String strEmail) {
        return networkServices.forgotPassword(strEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

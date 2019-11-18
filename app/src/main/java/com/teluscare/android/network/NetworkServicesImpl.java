package com.teluscare.android.network;

import com.teluscare.android.model.BaseResponseBean;

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

    public Observable<BaseResponseBean> login(String strUsername, String strPassword) {
        return networkServices.login(strUsername,strPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

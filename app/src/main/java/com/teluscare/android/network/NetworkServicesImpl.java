package com.teluscare.android.network;

import com.teluscare.android.model.CompanyListResponseBean;
import com.teluscare.android.model.IndividualListResponseBean;
import com.teluscare.android.model.LoginResponseBean;
import com.teluscare.android.model.SendOtpResponse;
import com.teluscare.android.model.Userregistraionmodel;

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

    public Observable<SendOtpResponse> Sendotp(String strUsername) {
        return networkServices.Sendotp(strUsername)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<IndividualListResponseBean> getlistindividual(String customertype) {
        return networkServices.customer_typecompany(customertype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<CompanyListResponseBean> getlistcompany(String customertype) {
        return networkServices.customer_typeindividual(customertype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Userregistraionmodel> userregistration(String username,
                                                             String jobtype,
                                                             String firstname,
                                                             String lastname,
                                                             String password,
                                                             String cnfpassword,
                                                             String refercode,
                                                             String customertype,
                                                             String usertype) {
        return networkServices.userregistration(username,jobtype,firstname,
                lastname,password,cnfpassword,refercode,customertype,usertype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}

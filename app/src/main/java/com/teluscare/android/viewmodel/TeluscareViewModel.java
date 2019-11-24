package com.teluscare.android.viewmodel;

import com.teluscare.android.model.CompanyListResponseBean;
import com.teluscare.android.model.IndividualListResponseBean;
import com.teluscare.android.model.LoginResponseBean;
import com.teluscare.android.model.SendOtpResponse;
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

    public Disposable sendotp(String strUsername, final Consumer<SendOtpResponse> response, final Consumer<Throwable> error) {
        disposable = services.Sendotp(strUsername)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<SendOtpResponse>() {
                    @Override
                    public void accept(SendOtpResponse sendOtpResponse) throws Exception {
                        if(!sendOtpResponse.getStatus().equalsIgnoreCase("true")){
                            error.accept(new Throwable(sendOtpResponse.getError()));
                            return;
                        }
                        response.accept(sendOtpResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        error.accept(throwable);
                    }
                });
        return disposable;
    }


    public Disposable getcompanylist(String customertype, final Consumer<CompanyListResponseBean> response,
                                     final Consumer<Throwable> error) {
        disposable = services.getlistcompany(customertype)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CompanyListResponseBean>() {
                    @Override
                    public void accept(CompanyListResponseBean sendCompanydata) throws Exception {
                        if(!sendCompanydata.getStatus().equalsIgnoreCase("true")){
                            error.accept(new Throwable(String.valueOf(sendCompanydata.getError())));
                            return;
                        }
                        response.accept(sendCompanydata);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        error.accept(throwable);
                    }
                });
        return disposable;
    }




    public Disposable getIndividualList(String customertype, final Consumer<IndividualListResponseBean> response,
                                     final Consumer<Throwable> error) {
        disposable = services.getlistindividual(customertype)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<IndividualListResponseBean>() {
                    @Override
                    public void accept(IndividualListResponseBean sendCompanydata) throws Exception {
                        if(!sendCompanydata.getStatus().equalsIgnoreCase("true")){
                            error.accept(new Throwable(String.valueOf(sendCompanydata.getError())));
                            return;
                        }
                        response.accept(sendCompanydata);
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

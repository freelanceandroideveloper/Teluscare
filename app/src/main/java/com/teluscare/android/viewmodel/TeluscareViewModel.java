package com.teluscare.android.viewmodel;

import com.teluscare.android.model.BaseResponseBean;
import com.teluscare.android.model.ForgotPasswordResponseBean;
import com.teluscare.android.model.CompanyListResponseBean;
import com.teluscare.android.model.IndividualListResponseBean;
import com.teluscare.android.model.LoginResponseBean;
import com.teluscare.android.model.SendOtpResponse;
import com.teluscare.android.model.Userregistraionmodel;
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

    public Disposable forgotPassword(String strEmail, final Consumer<ForgotPasswordResponseBean> response, final Consumer<Throwable> error) {
        disposable = services.forgotPassword(strEmail)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ForgotPasswordResponseBean>() {
                    @Override
                    public void accept(ForgotPasswordResponseBean forgotPasswordResponseBean) throws Exception {
                        if(!forgotPasswordResponseBean.getStatus().equalsIgnoreCase("true")){
                            error.accept(new Throwable(forgotPasswordResponseBean.getError()));
                            return;
                        }
                        response.accept(forgotPasswordResponseBean);
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

    public Disposable userregistration(String username,
                                       String jobtype,
                                       String firstname,
                                       String lastname,
                                       String password,
                                       String cnfpassword,
                                       String refercode,
                                       String customertype,
                                       String usertype, final Consumer<Userregistraionmodel> response,
                                        final Consumer<Throwable> error) {
        disposable = services.userregistration(username,jobtype,firstname,
                lastname,password,cnfpassword,refercode,customertype,usertype)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Userregistraionmodel>() {
                    @Override
                    public void accept(Userregistraionmodel sendCompanydata) throws Exception {
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

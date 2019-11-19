package com.teluscare.android.network;

import com.teluscare.android.model.BaseResponseBean;
import com.teluscare.android.model.LoginResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static com.teluscare.android.utility.CommonUtil.HEADER_AUTH_KEY_VALUE;
import static com.teluscare.android.utility.CommonUtil.HEADER_X_API_KEY_VALUE;

/**
 * Created by SandeepY on 18112019
 **/


public interface NetworkServices {

    @FormUrlEncoded
    @Headers({
            HEADER_X_API_KEY_VALUE,
            HEADER_AUTH_KEY_VALUE
    })
    @POST("/demo/API/users/login")
    Observable<LoginResponseBean> login(
            @Field("login_username") String username,
            @Field("login_password") String password,
            @Field("login_usertype") String userType
    );
}

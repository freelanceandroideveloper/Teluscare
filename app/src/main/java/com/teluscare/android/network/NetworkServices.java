package com.teluscare.android.network;

import com.teluscare.android.model.BaseResponseBean;
import com.teluscare.android.model.ForgotPasswordResponseBean;
import com.teluscare.android.model.CompanyListResponseBean;
import com.teluscare.android.model.IndividualListResponseBean;
import com.teluscare.android.model.LoginResponseBean;
import com.teluscare.android.model.SendOtpResponse;
import com.teluscare.android.model.Userregistraionmodel;

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


    @FormUrlEncoded
    @Headers({
            HEADER_X_API_KEY_VALUE,
            HEADER_AUTH_KEY_VALUE
    })

    @POST("/demo/API/users/send_mail_otp")
    Observable<SendOtpResponse> Sendotp(
            @Field("user_name") String username
    );


    @FormUrlEncoded
    @Headers({
            HEADER_X_API_KEY_VALUE,
            HEADER_AUTH_KEY_VALUE
    })
    @POST("/demo/API/utility/get_job_roles")
    Observable<IndividualListResponseBean> customer_typecompany(
            @Field("customer_type") String customer_type
    );

    @FormUrlEncoded
    @Headers({
            HEADER_X_API_KEY_VALUE,
            HEADER_AUTH_KEY_VALUE
    })
    @POST("/demo/API/utility/get_job_roles")
    Observable<CompanyListResponseBean> customer_typeindividual(
            @Field("customer_type") String customer_type
    );


    @FormUrlEncoded
    @Headers({
            HEADER_X_API_KEY_VALUE,
            HEADER_AUTH_KEY_VALUE
    })
    @POST("/demo/API/users/forgotten_password")
    Observable<ForgotPasswordResponseBean> forgotPassword(
            @Field("email") String email
    );


    @FormUrlEncoded
    @Headers({
            HEADER_X_API_KEY_VALUE,
            HEADER_AUTH_KEY_VALUE
    })
    @POST("/demo/API/users/register")
    Observable<Userregistraionmodel> userregistration(
            @Field("user_name") String user_name,
            @Field("job_type")  String job_type,
            @Field("first_name")  String first_name,
            @Field("last_name")  String last_name,
            @Field("password")  String password,
            @Field("confirm_password")  String confirm_password,
            @Field("customer_refer_code")  String customer_refer_code,
            @Field("customer_type")  String customer_type,
            @Field("usertype")  String usertype
    );
}

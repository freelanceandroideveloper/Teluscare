package com.teluscare.android.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.teluscare.android.BuildConfig;
import com.teluscare.android.utility.TeluscareAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by SandeepY on 18112019
 **/


public class RestClient {

    static Retrofit getClient() {
        return RetrofitAPI.retrofit;
    }

    private static class RetrofitAPI {
        private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(BuildConfig.DEBUG ? httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY) : httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE))
                .retryOnConnectionFailure(false)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build();


        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TeluscareAPI.getBaseUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}

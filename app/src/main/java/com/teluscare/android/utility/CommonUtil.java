package com.teluscare.android.utility;

import android.content.SharedPreferences;

/**
 * Created by SandeepY on 18112019
 **/


public class CommonUtil {

    public static final String HEADER_X_API_KEY_VALUE = "X-API-KEY:12345";
    public static final String HEADER_AUTH_KEY_VALUE = "Authorization:Basic ZGRkOjEyMzQ1Ng==";

    public static final String FROM_HOME = "FROM_HOME";
    public static final String USER_NAME = "USER_NAME";
    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    public static void logoutUser(){
        TeluscareSharedPreference sobhaSharedPreference=new TeluscareSharedPreference();
        SharedPreferences sharedPreferences=sobhaSharedPreference.getTeluscareSharedPreference();
        sharedPreferences.edit().clear().apply();
    }
}

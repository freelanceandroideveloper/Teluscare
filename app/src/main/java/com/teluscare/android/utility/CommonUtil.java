package com.teluscare.android.utility;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.teluscare.android.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SandeepY on 18112019
 **/


public class CommonUtil {

    public static final String HEADER_X_API_KEY_VALUE = "X-API-KEY:12345";
    public static final String HEADER_AUTH_KEY_VALUE = "Authorization:Basic ZGRkOjEyMzQ1Ng==";

    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    public static final String NO_NETWORK_ACTION = "no_network_action";

    public static final String FROM_HOME = "FROM_HOME";
    public static final String USER_NAME = "USER_NAME";
    public static final String IS_LOGGED_IN = "IS_LOGGED_IN";

    public static void logoutUser(){
        TeluscareSharedPreference sobhaSharedPreference=new TeluscareSharedPreference();
        SharedPreferences sharedPreferences=sobhaSharedPreference.getTeluscareSharedPreference();
        sharedPreferences.edit().clear().apply();
    }

    public static boolean isValidEmail(String email) {
        try {
            if (isValidMatch(REGEX_EMAIL, email))
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isValidMatch(String pattern, String sequence) {
        if(TextUtils.isEmpty(sequence)){
            return false;
        }else{
            Pattern sPattern = Pattern.compile(pattern);
            Matcher matcher = sPattern.matcher(sequence);
            return matcher.matches();
        }
    }

    public static boolean canConnect(Context mContext) {
        boolean ret = false;
        ConnectivityManager connectMgr = (ConnectivityManager) mContext.getSystemService(Service.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connectMgr.getActiveNetworkInfo();

        if (networkinfo != null && networkinfo.isConnected() && networkinfo.getState() == NetworkInfo.State.CONNECTED)
            ret = true;
        else {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(NO_NETWORK_ACTION));
        }
        Log.v("component coreutils", "can connect : " + ret);
        return ret;
    }
}

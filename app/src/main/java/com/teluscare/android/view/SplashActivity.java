package com.teluscare.android.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Window;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivitySplashBinding;
import com.teluscare.android.utility.TeluscareSharedPreference;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import static com.teluscare.android.utility.CommonUtil.FROM_HOME;
import static com.teluscare.android.utility.CommonUtil.IS_LOGGED_IN;

/**
 * Created by SandeepY on 21112019
 **/


public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding mbinding;
    private Handler mWaitHandler = new Handler();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
    }

    private void initview() {
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_blue));
        TeluscareSharedPreference teluscareSharedPreference = new TeluscareSharedPreference();
        sharedPreferences = teluscareSharedPreference.getTeluscareSharedPreference();
        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //The following code will execute after the 3 seconds.
                try {
                    boolean isLoggedIn = TeluscareSharedPreference.getBooleanValue(sharedPreferences, IS_LOGGED_IN);
                    if (isLoggedIn) {
                        Intent intentDash = new Intent(SplashActivity.this, DashboardActivity.class);
                        intentDash.putExtra(FROM_HOME, false);
                        startActivity(intentDash);
                    } else {
                        Intent intentDash = new Intent(SplashActivity.this, RegistrationActivity.class);
                        startActivity(intentDash);
                    }
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 3000);  // Give a 4 seconds delay.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
        mWaitHandler.removeCallbacksAndMessages(null);
    }


}

package com.teluscare.android.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivitySplashBinding;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

/**
 * Created by SandeepY on 21112019
 **/


public class SplashActivity extends BaseActivity{
    private ActivitySplashBinding  mbinding;
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();

    }

    private void initview() {
        mbinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {

                //The following code will execute after the 5 seconds.

                try {

                    //Go to next page i.e, start the next activity.
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                    //Let's Finish Splash Activity since we don't want to show this when user press back button.
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 5000);  // Give a 5 seconds delay.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
        mWaitHandler.removeCallbacksAndMessages(null);
    }


}

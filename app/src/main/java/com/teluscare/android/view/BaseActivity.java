package com.teluscare.android.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.teluscare.android.R;

/**
 * Created by SandeepY on 19112019
 **/


public class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    public ImageView info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransitionEnter();

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransitionExit();
    }

    /**
     * Overrides the pending Activity transition by performing the "Enter" animation.
     */
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.anim_slide_in_from_right_enter, R.anim.anim_slide_in_from_right_exit);
    }



    /**
     * Overrides the pending Activity transition by performing the "Exit" animation.
     */
    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.anim_slide_in_from_left_enter, R.anim.anim_slide_in_from_left_exit);
    }


    public void setNewToolbarTitle(CharSequence title) {
        try {
            super.setTitle(title);
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            if (mToolbar != null) {
                mToolbar.setTitle(title);
                mToolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
                mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
            }
            mActionBar = getSupportActionBar();
            //mActionBar.setDisplayHomeAsUpEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Check Internet Conncetion
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();
        return isConnected;
    }
}

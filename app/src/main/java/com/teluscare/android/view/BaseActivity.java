package com.teluscare.android.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.teluscare.android.R;

/**
 * Created by SandeepY on 19112019
 **/


public class BaseActivity extends AppCompatActivity {

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
}

package com.teluscare.android.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityResultBinding;

import static com.teluscare.android.utility.CommonUtil.FROM_HOME;

/**
 * Created by SandeepY on 27112019
 **/


public class ResultActivity extends BaseActivity {

    private ActivityResultBinding binding;
    private boolean fromHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        initView();
        setListener();
    }

    private void initView(){
        fromHome = getIntent().getBooleanExtra(FROM_HOME,false);
    }

    private void setListener(){
        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fromHome){
                    Intent intentCleaning = new Intent(ResultActivity.this, LoginActivity.class);
                    intentCleaning.putExtra(FROM_HOME,fromHome);
                    startActivity(intentCleaning);
                }else {
                    Intent intentCleaning = new Intent(ResultActivity.this, CheckoutActivity.class);
                    intentCleaning.putExtra(FROM_HOME,fromHome);
                    startActivity(intentCleaning);
                }
            }
        });
    }
}

package com.teluscare.android.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityCheckoutBinding;

import static com.teluscare.android.utility.CommonUtil.FROM_HOME;

/**
 * Created by SandeepY on 27112019
 **/


public class CheckoutActivity extends BaseActivity{

    private ActivityCheckoutBinding binding;
    private boolean fromHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        initView();
        setListener();
    }

    private void initView(){
        fromHome = getIntent().getBooleanExtra(FROM_HOME,false);
    }

    private void setListener(){
        binding.btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                Intent intentCleaning = new Intent(CheckoutActivity.this, DashboardActivity.class);
                intentCleaning.putExtra(FROM_HOME,false);
                startActivity(intentCleaning);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(fromHome){
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Teluscare");
            adb.setMessage("Current order will be lost");
            adb.setIcon(android.R.drawable.ic_dialog_alert);
            adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            adb.show();
        }else{
            super.onBackPressed();
        }
    }
}

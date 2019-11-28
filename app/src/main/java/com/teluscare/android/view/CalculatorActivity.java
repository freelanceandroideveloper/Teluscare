package com.teluscare.android.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityCalculatorBinding;

import static com.teluscare.android.utility.CommonUtil.FROM_HOME;

/**
 * Created by SandeepY on 27112019
 **/


public class CalculatorActivity extends BaseActivity{

    private ActivityCalculatorBinding binding;
    private boolean fromHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);
        initView();
        setListener();
    }

    private void initView(){
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_orange));

        getSupportActionBar().setTitle("Calculate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fromHome = getIntent().getBooleanExtra(FROM_HOME,false);
    }

    private void setListener(){
        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentResult = new Intent(CalculatorActivity.this, ResultActivity.class);
                intentResult.putExtra(FROM_HOME,fromHome);
                startActivity(intentResult);
            }
        });
    }
}

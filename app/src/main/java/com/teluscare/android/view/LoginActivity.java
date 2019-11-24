package com.teluscare.android.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityLoginBinding;
import com.teluscare.android.model.BaseResponseBean;
import com.teluscare.android.model.LoginResponseBean;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by SandeepY on 19112019
 **/


public class LoginActivity  extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    private TeluscareViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView(){
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = new TeluscareViewModel();
        TeluscareSharedPreference teluscareSharedPreference = new TeluscareSharedPreference();
        sharedPreferences = teluscareSharedPreference.getTeluscareSharedPreference();
        binding.customToggleLogin.tvCompany.setTextColor(Color.parseColor("#D0D1D2"));
        compositeDisposable = new CompositeDisposable();
    }

    private void setListener(){
        binding.rlLogin.setOnClickListener(this);
        binding.customToggleLogin.rlIndividual.setOnClickListener(this);
        binding.customToggleLogin.rlCompany.setOnClickListener(this);
        binding.tvForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rlLogin:
                processLogin();
                break;

            case R.id.rlIndividual:
                binding.customToggleLogin.rlIndividual.setBackground(ContextCompat.getDrawable(LoginActivity.this,R.drawable.rounded_blue_bg));
                binding.customToggleLogin.llMainBg.setBackground(ContextCompat.getDrawable(LoginActivity.this,R.drawable.rounded_grey_bg));
                binding.customToggleLogin.rlCompany.setBackground(ContextCompat.getDrawable(LoginActivity.this,R.drawable.rounded_grey_bg));
                binding.customToggleLogin.tvCompany.setTextColor(Color.parseColor("#D0D1D2"));
                binding.customToggleLogin.tvIndividual.setTextColor(Color.parseColor("#FFFFFF"));
                break;

            case R.id.rlCompany:
                binding.customToggleLogin.llMainBg.setBackground(ContextCompat.getDrawable(LoginActivity.this,R.drawable.rounded_grey_bg));
                binding.customToggleLogin.rlIndividual.setBackground(ContextCompat.getDrawable(LoginActivity.this,R.drawable.rounded_grey_bg));
                binding.customToggleLogin.rlCompany.setBackground(ContextCompat.getDrawable(LoginActivity.this,R.drawable.rounded_blue_bg));
                binding.customToggleLogin.tvIndividual.setTextColor(Color.parseColor("#D0D1D2"));
                binding.customToggleLogin.tvCompany.setTextColor(Color.parseColor("#FFFFFF"));
                break;

            case R.id.tvForgotPassword:
                Intent intentForgotPassword = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intentForgotPassword);
                break;
        }
    }

    private void processLogin(){
        String strEmail = binding.edtEmail.getText().toString();
        String strPassword = binding.edtPassword.getText().toString();

        if(TextUtils.isEmpty(strEmail)){
            Toast.makeText(this, "Email is required!!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(strPassword)){
            Toast.makeText(this, "Password is required!!", Toast.LENGTH_SHORT).show();
        }else{
            callLoginAPI(strEmail,strPassword);
        }
    }

    private void callLoginAPI(String email,String password){
        compositeDisposable.add(viewModel.login(email, password, "1",new Consumer<LoginResponseBean>() {
            @Override
            public void accept(LoginResponseBean responseBean) throws Exception {
                Toast.makeText(LoginActivity.this, responseBean.getStatus(), Toast.LENGTH_SHORT).show();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Toast.makeText(LoginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(null!=compositeDisposable)
            compositeDisposable.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null!=compositeDisposable)
            compositeDisposable.dispose();
    }
}

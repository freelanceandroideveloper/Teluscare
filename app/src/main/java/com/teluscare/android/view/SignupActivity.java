package com.teluscare.android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityLoginBinding;
import com.teluscare.android.databinding.ActivitySignupBinding;
import com.teluscare.android.model.LoginResponseBean;
import com.teluscare.android.model.SendOtpResponse;
import com.teluscare.android.utility.CommonUtil;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

public class SignupActivity extends BaseActivity implements View.OnClickListener {
    private ActivitySignupBinding binding;
    private TeluscareViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private CompositeDisposable compositeDisposable;
    String otpfromserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();

    }

    private void setListener() {
        binding.btnotp.setOnClickListener(this);
        binding.resendbutton.setOnClickListener(this);

        binding.edtEmaiid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String result = s.toString().replaceAll(" ", "").trim();
                if (!s.toString().equals(result)) {
                    binding.edtEmaiid.setText(result);
                    binding.edtEmaiid.setSelection(result.length());
                }
            }
        });
    }

    private void initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_blue));
        viewModel = new TeluscareViewModel();
        TeluscareSharedPreference teluscareSharedPreference = new TeluscareSharedPreference();
        sharedPreferences = teluscareSharedPreference.getTeluscareSharedPreference();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnotp:
                if(binding.btnotp.getText().equals("Send OTP")) {
                    processSignup();
                }else {
                    verifyOtp();
                }
                break;


            case R.id.resendbutton:
                processSignup();
                break;
        }
    }

    private void verifyOtp() {
        if (!TextUtils.isEmpty(otpfromserver)){
            if(binding.edtOtp.getText().toString().equalsIgnoreCase(otpfromserver)){
                Toast.makeText(this,"OTP Verified",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,RegistrationActivity.class);
                intent.putExtra("email",binding.edtEmaiid.getText().toString());
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this,"Wrong OTP Try Again",Toast.LENGTH_LONG).show();

            }
        }

    }

    private void processSignup() {
        String strEmail = binding.edtEmaiid.getText().toString();
        if(TextUtils.isEmpty(strEmail) && !Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()){
            Toast.makeText(this, "Email is Invalid!!", Toast.LENGTH_SHORT).show();
        }else if (!CommonUtil.isValidEmail(strEmail)) {
            Toast.makeText(this, "Email is Invalid!!", Toast.LENGTH_SHORT).show();
        }else{
            if(isConnected(this)){
                CallOtpVerifyApi(strEmail);
            }else{
                Toast.makeText(this,"Check your internet connection",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CallOtpVerifyApi(final String otp) {
        final ProgressDialog progressBar;
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Please wait...");
        progressBar.setTitle("Processing");
        progressBar.setCancelable(false);
        progressBar.show();
        compositeDisposable.add(viewModel.sendotp(otp,new Consumer<SendOtpResponse>() {
            @Override
            public void accept(SendOtpResponse responseBean) throws Exception {
                passdata();
                 otpfromserver = responseBean.getOtp();
                 progressBar.dismiss();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                progressBar.dismiss();
                Toast.makeText(SignupActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public  void passdata(){
      binding.txtinputOtp.setVisibility(View.VISIBLE);
      binding.textResend.setVisibility(View.VISIBLE);
      binding.resendbutton.setVisibility(View.VISIBLE);
      binding.btnotp.setText(getResources().getString(R.string.text_veify));
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




package com.teluscare.android.view;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityForgotPasswordBinding;
import com.teluscare.android.model.ForgotPasswordResponseBean;
import com.teluscare.android.model.LoginResponseBean;
import com.teluscare.android.utility.CommonUtil;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by SandeepY on 24112019
 **/


public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    private ActivityForgotPasswordBinding binding;
    private TeluscareViewModel viewModel;
    private CompositeDisposable compositeDisposable;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
    }

    private void initView() {
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_blue));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        viewModel = new TeluscareViewModel();
        compositeDisposable = new CompositeDisposable();
    }

    private void setListener() {
        binding.rlSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rlSubmit:
                processRequest();
                break;
        }
    }

    private void processRequest() {
        String strEmail = binding.edtForgotPassword.getText().toString();
        if (TextUtils.isEmpty(strEmail)) {
            binding.edtForgotPassword.setError(getResources().getString(R.string.email_required));
            binding.edtForgotPassword.requestFocus();
        } else if (CommonUtil.isValidEmail(strEmail)) {
            binding.edtForgotPassword.setError(getResources().getString(R.string.invalid_email));
            binding.edtForgotPassword.requestFocus();
        } else {
            if (CommonUtil.canConnect(ForgotPasswordActivity.this))
                callForgotPasswordAPI(strEmail);
        }
    }

    private void callForgotPasswordAPI(String email) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getResources().getString(R.string.sending_email));
        progressDialog.setMessage(getResources().getString(R.string.text_please_wait));
        progressDialog.setCancelable(false);
        progressDialog.show();
        compositeDisposable.add(viewModel.forgotPassword(email, new Consumer<ForgotPasswordResponseBean>() {
            @Override
            public void accept(ForgotPasswordResponseBean forgotPasswordResponseBean) throws Exception {
                progressDialog.dismiss();
                Toast.makeText(ForgotPasswordActivity.this, forgotPasswordResponseBean.getData(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                progressDialog.dismiss();
                Toast.makeText(ForgotPasswordActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != compositeDisposable)
            compositeDisposable.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != compositeDisposable)
            compositeDisposable.dispose();
    }
}

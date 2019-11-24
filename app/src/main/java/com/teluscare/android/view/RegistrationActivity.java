package com.teluscare.android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityLoginBinding;
import com.teluscare.android.databinding.ActivityRegistrationBinding;
import com.teluscare.android.utility.SwitchTrackTextDrawable;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class RegistrationActivity  extends BaseActivity implements View.OnClickListener {
    private ActivityRegistrationBinding binding;
    private TeluscareViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        
    }

    private void setListener() {

    }

    private void initView() {

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        viewModel = new TeluscareViewModel();
        TeluscareSharedPreference teluscareSharedPreference = new TeluscareSharedPreference();
        sharedPreferences = teluscareSharedPreference.getTeluscareSharedPreference();
        compositeDisposable = new CompositeDisposable();
        setNewToolbarTitle("");
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                processRegistration();
                break;

            case R.id.rlIndividual:
                binding.rlIndividual.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_blue_bg));
                binding.llMainBg.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                binding.rlCompany.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                break;

            case R.id.rlCompany:
                binding.llMainBg.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                binding.rlIndividual.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                binding.rlCompany.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_blue_bg));
                break;
        }
    }

    private void processRegistration() {
    }
}

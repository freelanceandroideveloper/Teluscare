package com.teluscare.android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityLoginBinding;
import com.teluscare.android.databinding.ActivityRegistrationBinding;
import com.teluscare.android.utility.SwitchTrackTextDrawable;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class RegistrationActivity  extends BaseActivity {
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
        binding.switchtext.setTrackDrawable(new SwitchTrackTextDrawable(this,
                R.string.signup_text,  R.string.signup_text));
    }
}

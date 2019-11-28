package com.teluscare.android.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityDashboardBinding;
import com.teluscare.android.utility.CommonUtil;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

import io.reactivex.disposables.CompositeDisposable;

import static com.teluscare.android.utility.CommonUtil.FROM_HOME;
import static com.teluscare.android.utility.CommonUtil.USER_NAME;

/**
 * Created by SandeepY on 27112019
 **/


public class DashboardActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActivityDashboardBinding binding;
    private TeluscareViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private CompositeDisposable compositeDisposable;
    private DrawerLayout drawerDashboard;
    private boolean fromHome;
    private TextView tvUserName;
    private CardView cvCleaningDash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        initView();
        setListener();
    }

    private void initView(){
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_orange));

        Toolbar toolbar = findViewById(R.id.toolbarDashboard);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerDashboard = findViewById(R.id.drawer_layout_dashboard);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerDashboard, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerDashboard.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_dashboard);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        tvUserName = headerView.findViewById(R.id.tvUsernameDashboard);
        cvCleaningDash = findViewById(R.id.cvCleaningDash);

        viewModel = new TeluscareViewModel();
        TeluscareSharedPreference teluscareSharedPreference = new TeluscareSharedPreference();
        sharedPreferences = teluscareSharedPreference.getTeluscareSharedPreference();
        compositeDisposable = new CompositeDisposable();

        tvUserName.setText(TeluscareSharedPreference.getStringValue(sharedPreferences,USER_NAME));

        fromHome = getIntent().getBooleanExtra(FROM_HOME,false);
        if(fromHome){
            Intent intentDashboard = new Intent(DashboardActivity.this,CheckoutActivity.class);
            intentDashboard.putExtra(FROM_HOME,true);
            startActivity(intentDashboard);
        }
    }

    private void setListener() {
        tvUserName.setOnClickListener(this);
        cvCleaningDash.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.itemLogoutDashboard) {
            CommonUtil.logoutUser();
            drawerDashboard.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvUsername:
                break;

            case R.id.cvCleaningDash:
                Intent intentCleaning = new Intent(DashboardActivity.this, CalculatorActivity.class);
                intentCleaning.putExtra(FROM_HOME,false);
                startActivity(intentCleaning);
                break;
        }
    }
}

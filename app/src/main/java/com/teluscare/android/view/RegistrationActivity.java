package com.teluscare.android.view;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityRegistrationBinding;
import com.teluscare.android.model.CompanyListResponseBean;
import com.teluscare.android.model.CompanyListResponseDataBean;
import com.teluscare.android.model.IndividualListResponseBean;
import com.teluscare.android.model.IndividualListResponseDataBean;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RegistrationActivity  extends BaseActivity implements View.OnClickListener {
    private ActivityRegistrationBinding binding;
    private TeluscareViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private CompositeDisposable compositeDisposable;
    private List<IndividualListResponseDataBean> individuallist;
    private List<CompanyListResponseDataBean> companylist;
    private String keyid;
    private boolean company;
    private boolean individual;
    ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        
    }

    private void setListener() {
        binding.register.setOnClickListener(this);
        binding.rlIndividual.setOnClickListener(this);
        binding.rlCompany.setOnClickListener(this);
        binding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                String selection = (String)parent.getItemAtPosition(position);
                if(individual){
                    for(int i = 0; i <individuallist.size();i++){
                        if(individuallist.get(i).getUsername().equalsIgnoreCase(selection)){
                            keyid = individuallist.get(i).getUser_id();

                        }
                    }
                }else {
                    for(int i = 0; i <companylist.size();i++){
                        if(companylist.get(i).getUsername().equalsIgnoreCase(selection)){
                            keyid = companylist.get(i).getUser_id();

                        }
                    }

                }



            }
        });


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
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Its loading....");
        progressBar.setTitle("Teluscare");
        progressBar.setCancelable(false);
        progressBar.show();
        getcompanydata();
        getindividualdata();

    }

    private void getindividualdata() {
        compositeDisposable.add(viewModel.getIndividualList("2",new Consumer<IndividualListResponseBean>() {
            @Override
            public void accept(IndividualListResponseBean responseBean) throws Exception {
                 individuallist = responseBean.getData();
                setadapterdataindividual();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
              progressBar.dismiss();
            }
        }));
    }

    private void getcompanydata() {
        compositeDisposable.add(viewModel.getcompanylist("1",new Consumer<CompanyListResponseBean>() {
            @Override
            public void accept(CompanyListResponseBean responseBean) throws Exception {
                 companylist = responseBean.getData();

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
             progressBar.dismiss();
            }
        }));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                processRegistration();
                break;

            case R.id.rlIndividual:
                binding.rlIndividual.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_blue_bg));
                binding.llMainBg.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                binding.rlCompany.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                setadapterdataindividual();
                break;

            case R.id.rlCompany:
                binding.llMainBg.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                binding.rlIndividual.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_grey_bg));
                binding.rlCompany.setBackground(ContextCompat.getDrawable(RegistrationActivity.this,R.drawable.rounded_blue_bg));
                setadapterdatacompany();
                break;
        }
    }

    private void setadapterdataindividual() {
        individual=true;
        ArrayList list = new ArrayList();
       for (int i =0;i<individuallist.size();i++){
           list.add(individuallist.get(i).getUsername());
       }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,  list);
        //Getting the instance of AutoCompleteTextView
        binding.autoCompleteTextView.setThreshold(1);//will start working from first character
        binding.autoCompleteTextView.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        binding.autoCompleteTextView.setHint(getResources().getString(R.string.text_select_job));
        progressBar.dismiss();
    }

    private void setadapterdatacompany() {
        company=true;
        ArrayList list = new ArrayList();
        for (int i =0;i<companylist.size();i++){
            list.add(companylist.get(i).getUsername());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, list);
        //Getting the instance of AutoCompleteTextView
        binding.autoCompleteTextView.setThreshold(1);//will start working from first character
        binding.autoCompleteTextView.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        binding.autoCompleteTextView.setHint(getResources().getString(R.string.text_select_company));
        progressBar.dismiss();
    }



    private void processRegistration() {
    if(TextUtils.isEmpty(binding.edtEmail.getText())){
        binding.edtEmail.setError("Please enter emailid");
        binding.edtEmail.requestFocus();
        }else  if(TextUtils.isEmpty(keyid)){
        binding.autoCompleteTextView.setError("Please select value");
        binding.autoCompleteTextView.requestFocus();
    }else  if(TextUtils.isEmpty(binding.edtFullname.getText())){
        binding.edtFullname.setError("Please Enter full Name");
        binding.edtFullname.requestFocus();
    }else  if(TextUtils.isEmpty(binding.edtPassword.getText())){
        binding.edtPassword.setError("Please Enter password");
        binding.edtPassword.requestFocus();
    }else  if(TextUtils.isEmpty(binding.edtCnfpasswd.getText())){
        binding.edtCnfpasswd.setError("Please Enter confirm password");
        binding.edtCnfpasswd.requestFocus();
    }else  if(!binding.edtPassword.getText().toString().equalsIgnoreCase(binding.edtCnfpasswd.getText().toString())){
        binding.edtCnfpasswd.setError("These password dont match");
        binding.edtCnfpasswd.requestFocus();
    }else{
        callregisterapi();
    }


    }

    private void callregisterapi() {

        Intent intent= new Intent(RegistrationActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
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

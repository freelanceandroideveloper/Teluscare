package com.teluscare.android.view;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.teluscare.android.R;
import com.teluscare.android.databinding.ActivityRegistrationBinding;
import com.teluscare.android.model.CompanyListResponseBean;
import com.teluscare.android.model.CompanyListResponseDataBean;
import com.teluscare.android.model.IndividualListResponseBean;
import com.teluscare.android.model.IndividualListResponseDataBean;
import com.teluscare.android.model.Userregistraionmodel;
import com.teluscare.android.utility.TeluscareSharedPreference;
import com.teluscare.android.viewmodel.TeluscareViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
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
        String selection;
        binding.register.setOnClickListener(this);
        binding.customToggleLogin.rlIndividual.setOnClickListener(this);
        binding.customToggleLogin.rlCompany.setOnClickListener(this);
        binding.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (individual) {
                        List<IndividualListResponseDataBean> selection = (List<IndividualListResponseDataBean>) parent.getItemAtPosition(position);
                        keyid = selection.get(position).getJobid();
                        binding.textJob.setText(selection.get(position).getUsername());
                        binding.materialcard.setVisibility(View.GONE);
                        /*for (int i = 0; i < individuallist.size(); i++) {
                            if (individuallist.get(i).getUsername().equalsIgnoreCase(selection)) {
                                keyid = individuallist.get(i).getJobid();
                                binding.textJob.setText(individuallist.get(i).getUsername());
                                binding.materialcard.setVisibility(View.GONE);

                            }
                        }*/
                    } else {
                        List<CompanyListResponseDataBean> selection = (List<CompanyListResponseDataBean>) parent.getItemAtPosition(position);
                        keyid = selection.get(position).getJobid();
                        binding.textJob.setText(selection.get(position).getUsername());
                        binding.materialcard.setVisibility(View.GONE);
                        /*for (int i = 0; i < companylist.size(); i++) {
                            if (companylist.get(i).getUsername().equalsIgnoreCase(selection)) {
                                keyid = companylist.get(i).getJobid();
                                binding.textJob.setText(companylist.get(i).getUsername());
                                binding.materialcard.setVisibility(View.GONE);


                            }
                        }*/

                    }

                }

        });

        binding.textJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.materialcard.getVisibility()== View.VISIBLE){
                    binding.materialcard.setVisibility(View.GONE);
                    binding.textJob.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp),null);

                }else {
                    binding.materialcard.setVisibility(View.VISIBLE);
                    binding.textJob.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp),null);


                }
            }
        });

       /* binding.autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                String selection = (String) parent.getItemAtPosition(position);
                if (individual) {
                    for (int i = 0; i < individuallist.size(); i++) {
                        if (individuallist.get(i).getUsername().equalsIgnoreCase(selection)) {
                            keyid = individuallist.get(i).getJobid();
                        }
                    }
                } else {
                    for (int i = 0; i < companylist.size(); i++) {
                        if (companylist.get(i).getUsername().equalsIgnoreCase(selection)) {
                            keyid = companylist.get(i).getJobid();
                        }
                    }

                }
            }
        });
*/
        binding.edtCnfpasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strConfPass = s.toString();
                String strPass = binding.edtPassword.getText().toString();
                if(!TextUtils.isEmpty(strPass) && !TextUtils.isEmpty(strPass)){
                    if(strConfPass.equalsIgnoreCase(strPass))
                        binding.txtinputCnfpswd.setBoxStrokeColor(Color.parseColor("#009933"));
                    else
                        binding.txtinputCnfpswd.setBoxStrokeColor(Color.parseColor("#ff0000"));
                }
            }
        });
    }

    private void initView() {

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.status_bar_blue));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        viewModel = new TeluscareViewModel();
        TeluscareSharedPreference teluscareSharedPreference = new TeluscareSharedPreference();
        sharedPreferences = teluscareSharedPreference.getTeluscareSharedPreference();
        compositeDisposable = new CompositeDisposable();
        setNewToolbarTitle("");
        if (isConnected(this)) {
            progressBar = new ProgressDialog(this);
            progressBar.setMessage("Please wait...");
            progressBar.setTitle("Proceeding");
            progressBar.setCancelable(false);
            progressBar.show();
            Intent i = getIntent();
            binding.edtEmail.setText(i.getStringExtra("email"));
            binding.edtEmail.setEnabled(false);
            getcompanydata();
            getindividualdata();
        } else {
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void getindividualdata() {
        compositeDisposable.add(viewModel.getIndividualList("2", new Consumer<IndividualListResponseBean>() {
            @Override
            public void accept(IndividualListResponseBean responseBean) throws Exception {
                individuallist = responseBean.getData();
                setadapterdataindividual();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                progressBar.dismiss();
                showerror(throwable.getMessage().toString());
            }
        }));
    }

    private void getcompanydata() {
        compositeDisposable.add(viewModel.getcompanylist("1", new Consumer<CompanyListResponseBean>() {
            @Override
            public void accept(CompanyListResponseBean responseBean) throws Exception {
                companylist = responseBean.getData();
                setadapterdatacompany();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                progressBar.dismiss();
                showerror(throwable.getMessage().toString());
            }
        }));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                processRegistration();
                break;

            case R.id.rlIndividual:
                setadapterdataindividual();
                binding.customToggleLogin.rlIndividual.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_blue_bg));
                binding.customToggleLogin.llMainBg.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_grey_bg));
                binding.customToggleLogin.rlCompany.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_grey_bg));
                binding.customToggleLogin.tvCompany.setTextColor(Color.parseColor("#D0D1D2"));
                binding.customToggleLogin.tvIndividual.setTextColor(Color.parseColor("#FFFFFF"));
                break;

            case R.id.rlCompany:
                setadapterdatacompany();
                binding.customToggleLogin.llMainBg.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_grey_bg));
                binding.customToggleLogin.rlIndividual.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_grey_bg));
                binding.customToggleLogin.rlCompany.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_blue_bg));
                binding.customToggleLogin.tvIndividual.setTextColor(Color.parseColor("#D0D1D2"));
                binding.customToggleLogin.tvCompany.setTextColor(Color.parseColor("#FFFFFF"));
                break;
        }
    }

    private void setadapterdataindividual() {
        individual = true;
        SearchIndividualAdapter employeeAdapter = new SearchIndividualAdapter(this, individuallist);
        binding.mListView.setAdapter(employeeAdapter);

        binding.mListView.setTextFilterEnabled(true);
        setupSearchView();
        binding.textJob.setHint(getResources().getString(R.string.text_select_job));
        progressBar.dismiss();
    }

    private void setadapterdatacompany() {
        company = true;
        SearchCompanyAdapter employeeAdapter = new SearchCompanyAdapter(this, companylist);
        binding.mListView.setAdapter(employeeAdapter);
        binding.mListView.setTextFilterEnabled(true);
        setupSearchView();
        binding.textJob.setHint(getResources().getString(R.string.text_select_company));
        progressBar.dismiss();
    }


    private void processRegistration() {
        if (TextUtils.isEmpty(keyid)) {
            binding.textJob.setError("Please select value");
            binding.textJob.requestFocus();
        } else if (TextUtils.isEmpty(binding.edtFullname.getText())) {
            binding.edtFullname.setError("Please Enter first Name");
            binding.edtFullname.requestFocus();
        } else if (TextUtils.isEmpty(binding.edtFullname.getText())) {
            binding.edtLastname.setError("Please Enter last Name");
            binding.edtLastname.requestFocus();
        } else if (TextUtils.isEmpty(binding.edtPassword.getText())) {
            binding.edtPassword.setError("Please Enter password");
            binding.edtPassword.requestFocus();
        } else if (TextUtils.isEmpty(binding.edtCnfpasswd.getText())) {
            binding.edtCnfpasswd.setError("Please Enter confirm password");
            binding.edtCnfpasswd.requestFocus();
        } else if (!binding.edtPassword.getText().toString().equalsIgnoreCase(binding.edtCnfpasswd.getText().toString())) {
            binding.edtCnfpasswd.setError("These password dont match");
            binding.edtCnfpasswd.requestFocus();
        } else if (!binding.checkbox.isChecked()) {
            Toast.makeText(this, "Please select terms and conditions", Toast.LENGTH_SHORT).show();
        } else {
            if (isConnected(this)) {
                callregisterapi();
            } else {
                Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();

            }

        }


    }

    private void callregisterapi() {
        progressBar.show();
        String email = binding.edtEmail.getText().toString();
       // String job = binding.autoCompleteTextView.getText().toString();
        String firstname = binding.edtFullname.getText().toString();
        String lastname = binding.edtLastname.getText().toString();
        String password = binding.edtPassword.getText().toString();
        String cnfpassword = binding.edtCnfpasswd.getText().toString();
        String refercode = binding.edtRefcode.getText().toString();
        if (TextUtils.isEmpty(refercode)) {
            refercode = "";
        }
        compositeDisposable.add(viewModel.userregistration(email, keyid, firstname,
                lastname, password, cnfpassword, refercode,
                "1", "1", new Consumer<Userregistraionmodel>() {
                    @Override
                    public void accept(Userregistraionmodel responseBean) throws Exception {
                        progressBar.dismiss();
                        if (responseBean.getData().equalsIgnoreCase("User Registered")) {
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            showerror(responseBean.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        progressBar.dismiss();
                        showerror(throwable.getMessage().toString());
                    }
                }));

    }

    private void showerror(String data) {
        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();

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


    private void setupSearchView()
    {
        binding.mSearchView.setIconifiedByDefault(false);
        binding.mSearchView.setOnQueryTextListener(this);
        binding.mSearchView.setSubmitButtonEnabled(true);
        binding.mSearchView.setQueryHint("Search Here");
       // binding.mSearchView.setLayoutParams(new ActionBar.LayoutParams(Gravity.RIGHT));
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            binding.mListView.clearTextFilter();
        } else {
            binding.mListView.setFilterText(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }



}
